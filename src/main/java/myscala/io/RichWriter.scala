/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.io

import java.io.{BufferedWriter, Writer}

import myscala.Validate

private[io] final class RichWriter(val writer: Writer) {

    Validate.notNull(writer)

    def toBufferedReader(size: Int = 8192): BufferedWriter = writer match {
        case w: BufferedWriter => w
        case _ => new BufferedWriter(writer, size)
    }

}