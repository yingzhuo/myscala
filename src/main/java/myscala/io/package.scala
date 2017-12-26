/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import scala.language.implicitConversions

package object io {

    implicit def fileToRichFile(file: java.io.File): RichFile = new RichFile(file)

    implicit def inputStreamToRichInputStream(in: java.io.InputStream): RichInputStream = new RichInputStream(in)

    implicit def outputStreamToRichOutputStream(out: java.io.OutputStream): RichOutputStream = new RichOutputStream(out)

    implicit def readerToRichReader(reader: java.io.Reader): RichReader = new RichReader(reader)

    implicit def writerToRichWriter(writer: java.io.Writer): RichWriter = new RichWriter(writer)
}
