/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import myscala.util.{DateRange, Dates}
import myscala.util.Dates.{CEILING, ROUND, TRUNCATE}

private[myscala] class RichDate(val date: Date) extends Ordered[Date] {

    Validate.notNull(date)

    override def compare(that: Date): Int = date compareTo that

    def toCalendar: Calendar = {
        val cal = Calendar.getInstance
        cal.setTime(date)
        cal
    }

    def toString(pattern: String): String = new SimpleDateFormat(pattern).format(date)

    def addYears(amount: Int): Date = add(Calendar.YEAR, amount)

    def addMonths(amount: Int): Date = add(Calendar.MONTH, amount)

    def addWeeks(amount: Int): Date = add(Calendar.WEEK_OF_YEAR, amount)

    def addDays(amount: Int): Date = add(Calendar.DAY_OF_MONTH, amount)

    def addHours(amount: Int): Date = add(Calendar.HOUR_OF_DAY, amount)

    def addMinutes(amount: Int): Date = add(Calendar.MINUTE, amount)

    def addSeconds(amount: Int): Date = add(Calendar.SECOND, amount)

    def addMilliseconds(amount: Int): Date = add(Calendar.MILLISECOND, amount)

    private def add(calendarField: Int, amount: Int = 1): Date = {
        val c = Calendar.getInstance
        c.setTime(date)
        c.add(calendarField, amount)
        c.getTime
    }

    def setYears(amount: Int): Date = set(Calendar.YEAR, amount)

    def setMonths(date: Date, amount: Int): Date = set(Calendar.MONTH, amount)

    def setDays(amount: Int): Date = set(Calendar.DAY_OF_MONTH, amount)

    def setHours(amount: Int): Date = set(Calendar.HOUR_OF_DAY, amount)

    def setMinutes(amount: Int): Date = set(Calendar.MINUTE, amount)

    def setSeconds(amount: Int): Date = set(Calendar.SECOND, amount)

    def setMilliseconds(amount: Int): Date = set(Calendar.MILLISECOND, amount)

    private def set(calendarField: Int, amount: Int): Date = {
        val c = Calendar.getInstance
        c.setLenient(false)
        c.setTime(date)
        c.set(calendarField, amount)
        c.getTime
    }

    def round(field: Int = Calendar.DAY_OF_MONTH): Date = {
        val gval = Calendar.getInstance
        gval.setTime(date)
        Dates.modify(gval, field, ROUND)
        gval.getTime
    }

    def truncate(field: Int = Calendar.DAY_OF_MONTH): Date = {
        val gval = Calendar.getInstance
        gval.setTime(date)
        Dates.modify(gval, field, TRUNCATE)
        gval.getTime
    }

    def ceiling(field: Int = Calendar.DAY_OF_MONTH): Date = {
        val gval = Calendar.getInstance
        gval.setTime(date)
        Dates.modify(gval, field, CEILING)
        gval.getTime
    }

    def isSameDay(that: Date): Boolean = {
        if (that == null) return false
        val cal1 = Calendar.getInstance()
        cal1.setTime(date)
        val cal2 = Calendar.getInstance()
        cal2.setTime(that)
        cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }

    @inline def isNotSameDay(that: Date): Boolean = !(date isSameDay that)

    def +(amout: Int): Date = date.addDays(amout)

    def -(amout: Int): Date = date.addDays(-amout)

    def until(endExclude: Date): DateRange = DateRange(date, endExclude)

    def to(endInclude: Date): DateRange = date.until(endInclude + 1)
}

object RichDate {

    final case class IntWrapper(i: Int) {
        def +(date: Date): Date = date + i
        def -(date: Date): Date = date - i
    }

}
