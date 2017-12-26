/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import myscala.{ValidationException => VE}

object Validate {

    def notNull[T](v: => T, msg: => String = null): T = if (v == null) throw new NullPointerException else v

    def notEmptyString(v: String, msg: => String = null): String = {
        notNull(v, msg)
        v match {
            case x if x.isEmpty => throw VE(msg)
            case _ => v
        }
    }

    def notBlankString(v: => String, msg: => String = null): String = {
        notNull(v, msg).toString match {
            case x if x.isBlank => throw VE(msg)
            case _ => v
        }
    }

    def notEmpty[T](iterable: => Iterable[T], msg: => String = null): Unit = {
        notNull(iterable, msg)

        if (iterable.isEmpty) throw VE(msg)

        iterable.foreach {
            case null => throw VE(msg)
            case _ =>
        }
    }

    def noNullElement[T](iterable: => Iterable[T], msg: => String = null): Unit = {
        notNull(iterable, msg)
        iterable.foreach {
            case null => throw VE(msg)
            case _ =>
        }
    }

}
