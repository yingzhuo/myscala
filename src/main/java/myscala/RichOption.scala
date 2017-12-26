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

private[myscala] class RichOption[T](val op: Option[T]) {

    Validate.notNull(op)

    def asJava: JavaOptional[T] = if (op.isDefined) JavaOptional.of(op.get) else JavaOptional.empty()

    @inline def ||(right: => Option[T]): Option[T] = op orElse right

    def getOrNull: T = op.getOrElse(null.asInstanceOf)

}
