/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.op

trait Show[X] {

    def asString(x: X): String

}

object Show {

    implicit final val StringShow: Show[String] = x => x

}

private[op] final class ShowOps[X: Show](val self: X) {

    private val show: Show[X] = implicitly[Show[X]]

    def print(): Unit = self match {
        case null => Console.print("null")
        case _ => Console.print(show.asString(self))
    }

    def println(): Unit = self match {
        case null => Console.println("null")
        case _ => Console.println(show.asString(self))
    }

}
