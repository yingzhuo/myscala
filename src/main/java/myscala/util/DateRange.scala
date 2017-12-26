/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import java.util.{Calendar, Date}

import myscala._

object DateRange {

    final val MILLISECOND: Int = Calendar.MILLISECOND
    final val SECOND: Int = Calendar.SECOND
    final val MINUTE: Int = Calendar.MINUTE
    final val HOUR_OF_DAY: Int = Calendar.HOUR_OF_DAY
    final val DAY_OF_MONTH: Int = Calendar.DAY_OF_MONTH
    final val WEEK_OF_YEAR: Int = Calendar.WEEK_OF_YEAR
    final val MONTH: Int = Calendar.MONTH
    final val YEAR: Int = Calendar.YEAR

    def apply(startInclude: String, endExclude: String, stepType: Int, step: Int)(implicit f: String => Date): DateRange = {
        apply(f(startInclude), f(endExclude), stepType, step)
    }

    def apply(startInclude: Date, endExclude: Date, stepType: Int = DateRange.DAY_OF_MONTH, step: Int = 1): DateRange =
        new StreamDateRange(startInclude, endExclude, stepType, step)

    def unapplySeq(dateRange: DateRange): Option[Seq[Date]] = dateRange match {
        case null => None
        case x => Option(x.iterator.toList)
    }

    private class StreamDateRange(val startInclude: Date, val endExclude: Date, val stepType: Int, val step: Int) extends DateRange {

        require(
            stepType match {
                case MILLISECOND | SECOND | MINUTE | HOUR_OF_DAY => true
                case DAY_OF_MONTH | WEEK_OF_YEAR | MONTH | YEAR => true
                case _ => false
            }
        )

        private var current: Date = startInclude

        private val stream: Stream[Date] = Stream.continually(_next).takeWhile(_ < endExclude)

        private def plus(date: Date, stepType: Int, step: Int): Date = stepType match {
            case MILLISECOND => date.addMilliseconds(step)
            case SECOND => date.addSeconds(step)
            case MINUTE => date.addMinutes(step)
            case HOUR_OF_DAY => date.addHours(step)
            case DAY_OF_MONTH => date.addDays(step)
            case WEEK_OF_YEAR => date.addWeeks(step)
            case MONTH => date.addMonths(step)
            case YEAR => date.addYears(step)
        }

        private def _next: Date = {
            val a = current
            current = plus(current, stepType, step)
            a
        }

        override def iterator: Iterator[Date] = stream.iterator

        override def toStream: Stream[Date] = stream
    }

}

sealed trait DateRange extends Iterable[Date] {

    def startInclude: Date

    def endExclude: Date

    def stepType: Int

    def step: Int

    def toStream: Stream[Date]

    override def foreach[U](f: (Date) => U): Unit = iterator.foreach {
        x => f(x)
    }

    override def forall(p: Date => Boolean): Boolean = {
        iterator.foreach { x =>
            if (!p(x)) return false
        }
        true
    }

}
