/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.reflect

import java.lang.reflect.Constructor

import myscala.Validate

private[reflect] class RichConstructor[T <: scala.AnyRef](val c: Constructor[T]) {

    Validate.notNull(c)

    // TODO

}
