/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.util.{Calendar, Date}

import scala.language.implicitConversions
import myscala.util.DatePatterns

/* 请慎用 */
object Implicits {

    implicit def stringToDate(s: String): Date = DatePatterns.parse(s)

    implicit def stringToCalendar(s: String): Calendar = stringToDate(s).toCalendar

}
