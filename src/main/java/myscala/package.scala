/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
import java.util.{Calendar, Date, Properties, Iterator => JavaIterator}

import scala.collection.{Iterator => ScalaIterator}
import scala.language.implicitConversions
import scala.util.Try

package object myscala {

    case class ValidationException(msg: String) extends RuntimeException(msg)

    // ---

    implicit def anyToRichAny[T <: scala.Any](any: T): RichAny[T] = new RichAny[T](any)

    implicit def booleanToRichBoolean(b: Boolean): RichBoolean = new RichBoolean(b)

    implicit def throwableToRichThrowable[T <: Throwable](t: T): RichThrowable[T] = new RichThrowable(t)

    implicit def stringToRichString(s: String): RichString = new RichString(s)

    implicit def stringToRichStringNullabble(s: String): RichStringNullable = new RichStringNullable(s)

    implicit def charToRichChar(ch: Char): RichChar = new RichChar(ch)

    implicit def dateToRichDate(d: Date): RichDate = new RichDate(d)

    implicit def calendarToRichCalendar(c: Calendar): RichCalendar = new RichCalendar(c)

    implicit def propertiesToRichProperties(props: Properties): RichProperties = new RichProperties(props)

    implicit def enumClassToRichEnumClass[E <: Enum[E]](clz: Class[E]): RichEnumClass[E] = new RichEnumClass(clz)

    implicit def optionalToRichJavaOptional[T](op: java.util.Optional[T]): RichJavaOptional[T] = new RichJavaOptional(op)

    implicit def optionToRIchOption[T](op: Option[T]): RichOption[T] = new RichOption[T](op)

    implicit def closeableLikeToRichCloseableLike[C <: {def close() : Unit}](c: C): RichCloseableLike[C] = new RichCloseableLike[C](c)

    implicit def threadLocalToRichThreadLocal[T](threadLocal: java.lang.ThreadLocal[T]): RichThreadLocal[T] = new RichThreadLocal[T](threadLocal)

    implicit def tryToRichTry[T](`try`: Try[T]): RichTry[T] = new RichTry[T](`try`)

    implicit def javaIteratorToRichJavaIterator[T](it: JavaIterator[T]): RichJavaIterator[T] = new RichJavaIterator(it)

    implicit def scalaIteratorToRichScalaIterator[T](it: ScalaIterator[T]): RichScalaIterator[T] = new RichScalaIterator(it)

    implicit def orderedToRichOrdered[T](o: Ordered[T]): RichOrdered[T] = new RichOrdered[T](o)

    implicit def orderingToRichOrdering[T](o: Ordering[T]): RichOrdering[T] = new RichOrdering[T](o)

    // ----

    implicit def intToRichDateIntWrapper(i: Int): RichDate.IntWrapper = RichDate.IntWrapper(i)

    implicit def intToRichCalendarIntWrapper(i: Int): RichCalendar.IntWrapper = RichCalendar.IntWrapper(i)

}
