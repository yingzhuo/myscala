/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.io.stream

import java.io.Writer

object BlackholeWriter extends Writer {

    override def append(c: Char): Writer = this

    override def append(csq: CharSequence, start: Int, end: Int): Writer = this

    override def append(csq: CharSequence): Writer = this

    override def write(idx: Int): Unit = {}

    override def write(chr: Array[Char]): Unit = {}

    override def write(chr: Array[Char], st: Int, end: Int): Unit = {}

    override def write(str: String): Unit = {}

    override def write(str: String, st: Int, end: Int): Unit = {}

    override def flush(): Unit = {}

    override def close(): Unit = {}
}
