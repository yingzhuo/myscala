/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import scala.language.reflectiveCalls
import scala.util.Try

private[myscala] class RichCloseableLike[C <: {def close() : Unit}](val closeableLike: C) {

    Validate.notNull(closeableLike)

    @inline def closeQuietly(): Unit = if (closeableLike != null) Try(closeableLike.close())

}
