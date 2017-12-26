/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.util.{Iterator => JavaIterator}

import scala.{Iterator => ScalaIterator}
import myscala.collection.{IteratorWrapperForJava, IteratorWrapperForScala}

private[myscala] class RichScalaIterator[T](val iterator: ScalaIterator[T]) {

    Validate.notNull(iterator)

    def asJava: JavaIterator[T] = iterator match {
        case x: IteratorWrapperForJava[T] => x.it
        case x => IteratorWrapperForScala[T](x)
    }

}