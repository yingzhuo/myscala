/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.io

import java.io.{BufferedReader, Reader}

import myscala.Validate

private[io] final class RichReader(val reader: Reader) {

    Validate.notNull(reader)

    def toBufferedReader(size: Int = 8192): BufferedReader = reader match {
        case r: BufferedReader => r
        case _ => new BufferedReader(reader, size)
    }

    def toCharArray: Array[Char] = Stream.continually(reader.read).takeWhile(_ != -1).map(_.toChar).toArray

}
