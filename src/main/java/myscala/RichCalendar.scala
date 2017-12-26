/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.util.{Calendar, Date}

import myscala.util.{DateRange, Dates}
import myscala.util.Dates.{CEILING, ROUND, TRUNCATE}

private[myscala] final class RichCalendar(val calendar: Calendar) extends Ordered[Calendar] {

    Validate.notNull(calendar)

    override def compare(that: Calendar): Int = calendar.getTime compare that.getTime

    def toDate: Date = calendar.getTime

    def toString(pattern: String): String = calendar.toDate.toString(pattern)

    def addYears(amount: Int): Calendar = add(Calendar.YEAR, amount)

    def addMonths(amount: Int): Calendar = add(Calendar.MONTH, amount)

    def addWeeks(amount: Int): Calendar = add(Calendar.WEEK_OF_YEAR, amount)

    def addDays(amount: Int): Calendar = add(Calendar.DAY_OF_MONTH, amount)

    def addHours(amount: Int): Calendar = add(Calendar.HOUR_OF_DAY, amount)

    def addMinutes(amount: Int): Calendar = add(Calendar.MINUTE, amount)

    def addSeconds(amount: Int): Calendar = add(Calendar.SECOND, amount)

    private def add(calendarField: Int, amount: Int = 1): Calendar = {
        calendar.add(calendarField, amount)
        calendar
    }

    def addMilliseconds(amount: Int): Calendar = add(Calendar.MILLISECOND, amount)

    def setYears(amount: Int): Calendar = set(Calendar.YEAR, amount)

    private def set(calendarField: Int, amount: Int): Calendar = {
        calendar.set(calendarField, amount)
        calendar
    }

    def setMonths(date: Date, amount: Int): Calendar = set(Calendar.MONTH, amount)

    def setDays(amount: Int): Calendar = set(Calendar.DAY_OF_MONTH, amount)

    def setHours(amount: Int): Calendar = set(Calendar.HOUR_OF_DAY, amount)

    def setMinutes(amount: Int): Calendar = set(Calendar.MINUTE, amount)

    def setSeconds(amount: Int): Calendar = set(Calendar.SECOND, amount)

    def setMilliseconds(amount: Int): Calendar = set(Calendar.MILLISECOND, amount)

    def round(field: Int = Calendar.DAY_OF_MONTH): Calendar = {
        val gval = Calendar.getInstance
        gval.setTime(calendar.toDate)
        Dates.modify(gval, field, ROUND)
        gval
    }

    def truncate(field: Int = Calendar.DAY_OF_MONTH): Calendar = {
        val gval = Calendar.getInstance
        gval.setTime(calendar.toDate)
        Dates.modify(gval, field, TRUNCATE)
        gval
    }

    def ceiling(field: Int = Calendar.DAY_OF_MONTH): Calendar = {
        val gval = Calendar.getInstance
        gval.setTime(calendar.toDate)
        Dates.modify(gval, field, CEILING)
        gval
    }

    def isSameDay(that: Calendar): Boolean = {
        if (that == null) return false
        calendar.get(Calendar.ERA) == that.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == that.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == that.get(Calendar.DAY_OF_YEAR)
    }

    @inline def isNotSameDay(that: Calendar): Boolean = !(calendar isSameDay calendar)

    def +(amout: Int): Calendar = calendar.addDays(amout)

    def -(amout: Int): Calendar = calendar.addDays(-amout)

    def until(endExclude: Calendar): DateRange = DateRange(calendar.getTime, endExclude.getTime)

    def to(endInclude: Calendar): DateRange = calendar.until(endInclude + 1)

}

object RichCalendar {

    final case class IntWrapper(i: Int) {
        def +(calendar: Calendar): Calendar = calendar + i
        def -(calendar: Calendar): Calendar = calendar - i
    }

}