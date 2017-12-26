/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.op

private[op] final class OptionableOps[X: Optionable](val self: X) {

    private val optionable: Optionable[X] = implicitly[Optionable[X]]

    def some: Option[X] = optionable.some(self)

    def none: Option[Nothing] = optionable.none(self)

    def ||(that: Option[X]): Option[X] = if (some.isDefined) some else that

}

trait Optionable[X] {

    def some(x: X): Option[X]

    def none(x: X): Option[Nothing] = None
}

object Optionable {

    implicit final val AnyOptionable: Optionable[scala.Any] = (x: Any) => Option(x)
    implicit final val BooleanOptionable: Optionable[Boolean] = (x: Boolean) => Option(x)
    implicit final val ByteOptionable: Optionable[Byte] = (x: Byte) => Option(x)
    implicit final val CharOptionable: Optionable[Char] = (x: Char) => Option(x)
    implicit final val ShortOptionable: Optionable[Short] = (x: Short) => Option(x)
    implicit final val IntOptionable: Optionable[Int] = (x: Int) => Option(x)
    implicit final val LongOptionable: Optionable[Long] = (x: Long) => Option(x)
    implicit final val FloatOptionable: Optionable[Float] = (x: Float) => Option(x)
    implicit final val DoubleOptionable: Optionable[Double] = (x: Double) => Option(x)
    implicit final val StringOptionable: Optionable[String] = (x: String) => Option(x)

}
