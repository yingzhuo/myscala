/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import java.util.Calendar

private[myscala] object Dates {

    private val SEMI_MONTH: Int = 1001

    private val fields = Array(Array(Calendar.MILLISECOND), Array(Calendar.SECOND), Array(Calendar.MINUTE), Array(Calendar.HOUR_OF_DAY, Calendar.HOUR), Array(Calendar.DATE, Calendar.DAY_OF_MONTH, Calendar.AM_PM) /* Calendar.DAY_OF_YEAR, Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK_IN_MONTH */ , Array(Calendar.MONTH, SEMI_MONTH), Array(Calendar.YEAR), Array(Calendar.ERA))

    sealed trait ModifyType

    case object TRUNCATE extends ModifyType

    case object ROUND extends ModifyType

    case object CEILING extends ModifyType

    def modify(`val`: Calendar, field: Int, modType: ModifyType): Unit = {
        if (`val`.get(Calendar.YEAR) > 280000000) throw new ArithmeticException("Calendar value too large for accurate calculations")
        if (field == Calendar.MILLISECOND) return
        // ----------------- Fix for LANG-59 ---------------------- START ---------------
        // see http://issues.apache.org/jira/browse/LANG-59
        //
        // Manually truncate milliseconds, seconds and minutes, rather than using
        // Calendar methods.
        val date = `val`.getTime
        var time = date.getTime
        var done = false
        // truncate milliseconds
        val millisecs = `val`.get(Calendar.MILLISECOND)
        if ((TRUNCATE eq modType) || millisecs < 500) time = time - millisecs
        if (field == Calendar.SECOND) done = true
        // truncate seconds
        val seconds = `val`.get(Calendar.SECOND)
        if (!done && ((TRUNCATE eq modType) || seconds < 30)) time = time - (seconds * 1000L)
        if (field == Calendar.MINUTE) done = true
        // truncate minutes
        val minutes = `val`.get(Calendar.MINUTE)
        if (!done && ((TRUNCATE eq modType) || minutes < 30)) time = time - (minutes * 60000L)
        // reset time
        if (date.getTime != time) {
            date.setTime(time)
            `val`.setTime(date)
        }
        // ----------------- Fix for LANG-59 ----------------------- END ----------------
        var roundUp = false
        for (aField <- fields) {
            for (element <- aField) {
                if (element == field) { //This is our field... we stop looping
                    if ((modType eq CEILING) || (modType eq ROUND) && roundUp) if (field == SEMI_MONTH) { //This is a special case that's hard to generalize
                        //If the date is 1, we round up to 16, otherwise
                        //  we subtract 15 days and add 1 month
                        if (`val`.get(Calendar.DATE) == 1) `val`.add(Calendar.DATE, 15)
                        else {
                            `val`.add(Calendar.DATE, -15)
                            `val`.add(Calendar.MONTH, 1)
                        }
                        // ----------------- Fix for LANG-440 ---------------------- START ---------------
                    }
                    else if (field == Calendar.AM_PM) { // This is a special case
                        // If the time is 0, we round up to 12, otherwise
                        //  we subtract 12 hours and add 1 day
                        if (`val`.get(Calendar.HOUR_OF_DAY) == 0) `val`.add(Calendar.HOUR_OF_DAY, 12)
                        else {
                            `val`.add(Calendar.HOUR_OF_DAY, -12)
                            `val`.add(Calendar.DATE, 1)
                        }
                        // ----------------- Fix for LANG-440 ---------------------- END ---------------
                    }
                    else { //We need at add one to this field since the
                        //  last number causes us to round up
                        `val`.add(aField(0), 1)
                    }
                    return
                }
            }
            //We have various fields that are not easy roundings
            var offset = 0
            var offsetSet = false
            //These are special types of fields that require different rounding rules
            field match {
                case SEMI_MONTH =>
                    if (aField(0) == Calendar.DATE) { //If we're going to drop the DATE field's value,
                        //  we want to do this our own way.
                        //We need to subtrace 1 since the date has a minimum of 1
                        offset = `val`.get(Calendar.DATE) - 1
                        //If we're above 15 days adjustment, that means we're in the
                        //  bottom half of the month and should stay accordingly.
                        if (offset >= 15) offset -= 15
                        //Record whether we're in the top or bottom half of that range
                        roundUp = offset > 7
                        offsetSet = true
                    }
                case Calendar.AM_PM =>
                    if (aField(0) == Calendar.HOUR_OF_DAY) { //If we're going to drop the HOUR field's value,
                        offset = `val`.get(Calendar.HOUR_OF_DAY)
                        if (offset >= 12) offset -= 12
                        roundUp = offset >= 6
                        offsetSet = true
                    }
                case _ =>
            }
            if (!offsetSet) {
                val min = `val`.getActualMinimum(aField(0))
                val max = `val`.getActualMaximum(aField(0))
                //Calculate the offset from the minimum allowed value
                offset = `val`.get(aField(0)) - min
                //Set roundUp if this is more than half way between the minimum and maximum
                roundUp = offset > ((max - min) / 2)
            }
            //We need to remove this field
            if (offset != 0) `val`.set(aField(0), `val`.get(aField(0)) - offset)
        }
        throw new IllegalArgumentException("The field " + field + " is not supported")
    }
}
