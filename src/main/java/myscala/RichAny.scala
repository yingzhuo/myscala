/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

private[myscala] final class RichAny[T <: scala.Any](val any: T) {

    @inline def ?!(that: => T): T = any orDefault that

    def orDefault(that: => T): T = any match {
        case null => that
        case _ => any
    }

    def some: Option[T] = if (any != null) Some(any) else throw new NullPointerException

    def option: Option[T] = Option(any)

    def none: Option[T] = None

}
