/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.io

import java.io._
import java.nio.charset.Charset

import myscala.{Charsets, Validate}

private[io] final class RichInputStream(val in: InputStream) {

    Validate.notNull(in)

    def toBufferedInputStream(size: Int = 8192): BufferedInputStream = in match {
        case in: BufferedInputStream => in
        case _ => new BufferedInputStream(in, size)
    }

    def toReader(charset: Charset = Charsets.UTF_8): Reader = new InputStreamReader(in, charset)

    def toReader(charset: String): Reader = new InputStreamReader(in, charset)

    def toByteArray: Array[Byte] = Stream.continually(in.read).takeWhile(_ != -1).map(_.toByte).toArray

    def copyTo(outputStream: OutputStream): Unit = {
        Stream.continually(in.read).takeWhile(_ != -1).foreach(outputStream.write)
    }

}
