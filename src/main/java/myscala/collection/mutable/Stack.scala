/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.collection.mutable

import scala.util.Try

trait Stack[E] {

    def +=(e: E): this.type = {
        push(e)
        this
    }

    def push(e: E): Unit

    def pop(): E

    def popOption(): Option[E] = Try(pop()).toOption

    def peek(): E

    def peekOption(): Option[E] = Try(peek()).toOption

    def isEmpty: Boolean

    def size: Int

    def clean(): Unit

    def map[T](f: E => T): Stack[T]

    def collect[T](partialFunction: PartialFunction[E, T]): Stack[T]

    def forall(p: E => Boolean): Boolean

    def exists(p: E => Boolean): Boolean
}

object Stack {

    def apply[E](es: E*): Stack[E] = ListStack(es: _*)

}