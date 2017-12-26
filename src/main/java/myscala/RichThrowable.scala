/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import scala.annotation.tailrec

private[myscala] final class RichThrowable[E <: Throwable](val e: E) {

    Validate.notNull(e)

    def getMessageOrElse(s: => String): String = if (e.getMessage == null) e.getMessage else s

    def getRootCause: Throwable = {
        @tailrec def doGetRootCause(e: Throwable): Throwable = if (e.getCause == null) e else doGetRootCause(e.getCause)

        if (e.getCause == null) null else doGetRootCause(e)
    }

    def getThrowableCount: Int = getThrowableList.size

    def getThrowableList: List[Throwable] = {
        var cause: Throwable = e

        Stream.continually {
            cause = cause.getCause
            cause
        }.takeWhile(_ != null).toList
    }

}
