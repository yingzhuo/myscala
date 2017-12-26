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

private[myscala] class RichJavaIterator[T](val iterator: JavaIterator[T]) {

    Validate.notNull(iterator)

    def asScala: ScalaIterator[T] = iterator match {
        case x: IteratorWrapperForScala[T] => x.it
        case x => IteratorWrapperForJava[T](x)
    }

}
