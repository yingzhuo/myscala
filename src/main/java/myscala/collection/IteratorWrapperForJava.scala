/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.collection

import java.util.{Iterator => JavaIterator}

import scala.{Iterator => ScalaIterator}

private[myscala] case class IteratorWrapperForJava[T](it: JavaIterator[T]) extends ScalaIterator[T] {

    override def hasNext: Boolean = it.hasNext

    override def next(): T = it.next()
}
