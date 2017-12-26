/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.io.stream

import java.io.OutputStream

object BlackholeOutputStream extends OutputStream {

    override def write(b: Array[Byte], off: Int, len: Int): Unit = {}

    override def write(b: Int): Unit = {}

    override def write(b: Array[Byte]): Unit = {}

}
