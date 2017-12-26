/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.io

import java.io.{BufferedOutputStream, OutputStream, OutputStreamWriter, Writer}
import java.nio.charset.Charset

import myscala.{Charsets, Validate}

private[io] final class RichOutputStream(val out: OutputStream) {

    Validate.notNull(out)

    def toBufferedOutputStream(size: Int = 8192): BufferedOutputStream = out match {
        case out: BufferedOutputStream => out
        case _ => new BufferedOutputStream(out, size)
    }

    def toWriter(charset: Charset = Charsets.UTF_8): Writer = new OutputStreamWriter(out, charset)

    def toWriter(charset: String): Writer = new OutputStreamWriter(out, charset)

}
