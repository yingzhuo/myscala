/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import myscala.util.ThreadLocal

private[myscala] class RichThreadLocal[T](val threadLocal: java.lang.ThreadLocal[T]) {

    Validate.notNull(threadLocal)

    def asScala: ThreadLocal[T] = ThreadLocal(threadLocal.get())

}
