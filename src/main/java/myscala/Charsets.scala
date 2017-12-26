/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.nio.charset.{Charset, StandardCharsets}

import scala.util.{Success, Try}

object Charsets {

    final val US_ASCII: Charset = StandardCharsets.US_ASCII
    final val UTF_8: Charset = StandardCharsets.UTF_8
    final val UTF_16: Charset = StandardCharsets.UTF_16
    final val UTF_16BE: Charset = StandardCharsets.UTF_16BE
    final val UTF_16LE: Charset = StandardCharsets.UTF_16LE
    final val ISO_8859_1: Charset = StandardCharsets.ISO_8859_1
    final val GBK: Charset = Charset.forName("GBK")
    final val GB2312: Charset = Charset.forName("GB2312")
    final val GB18030: Charset = Charset.forName("GB18030")

    def isSupported(name: String): Boolean = name match {
        case null => false
        case nm => Try(Charset.forName(nm)) match {
            case Success(_) => true
            case _ => false
        }
    }
}
