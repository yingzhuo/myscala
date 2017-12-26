/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.util.{Optional => JavaOptional}

private[myscala] class RichJavaOptional[T](val op: JavaOptional[T]) {

    Validate.notNull(op)

    def asScala: Option[T] = if (op.isPresent) Some(op.get()) else None

}
