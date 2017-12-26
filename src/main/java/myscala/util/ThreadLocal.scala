/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import java.lang.{ThreadLocal => JavaThreadLocal}

sealed trait ThreadLocal[E] extends Iterable[E] {

    def get: E

    def set(value: => E): Unit

    final def apply(): E = get

    final def apply(value: => E): Unit = set(value)

    def remove(): Unit

    def clean(): Unit

    def map[F](trans: E => F): ThreadLocal[F]

    final def getOrElse(default: => E): E = get match {
        case null => default
        case x => x
    }

    final def getOrNull(): E = getOrElse(null.asInstanceOf)

    @inline final def isDefined: Boolean = get != null

    final override def iterator: Iterator[E] = if (isEmpty) Iterator.empty else Iterator.single(get)

    final def toOption: Option[E] = if (isEmpty) None else Some(get)

}

object ThreadLocal {

    def apply[E](): ThreadLocal[E] = new SimpleThreadLocal[E](null.asInstanceOf[E])

    def apply[E](eleSupplier: => E): ThreadLocal[E] = new SimpleThreadLocal[E](eleSupplier)

    private class SimpleThreadLocal[E](val ele: E) extends ThreadLocal[E] {

        private val holder: JavaThreadLocal[E] = JavaThreadLocal.withInitial(() => ele)

        override def get: E = holder.get()

        override def set(valueSupplier: => E): Unit = holder.set(valueSupplier)

        override def remove(): Unit = holder.remove()

        override def clean(): Unit = holder.remove()

        override def map[F](trans: (E) => F): ThreadLocal[F] = this match {
            case _ if isEmpty => new SimpleThreadLocal[F](null.asInstanceOf)
            case x => new SimpleThreadLocal[F](trans(x()))
        }

        override def toString: String = s"ThreadLocal(${this ()})"
    }

}