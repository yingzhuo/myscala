/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import scala.util.{Failure, Success, Try}

private[myscala] class RichTry[T](val `try`: Try[T]) {

    Validate.notNull(`try`)

    def whenSuccess[U](f: T => U): U = `try` match {
        case Success(x) => f(x)
        case _ => null.asInstanceOf[U]
    }

    def whenFailure[U](f: Failure[_] => U): U = `try` match {
        case x@Failure(_) => f(x)
        case _ => null.asInstanceOf[U]
    }

    def || (right: Try[T]): Try[T] = if (`try`.isSuccess) `try` else right

}
