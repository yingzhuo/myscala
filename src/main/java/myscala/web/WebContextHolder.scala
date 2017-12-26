/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.web

import javax.servlet.ServletContext
import javax.servlet.http._

import myscala.util.ThreadLocal

object WebContextHolder {

    final val RequestHolder: ThreadLocal[HttpServletRequest] = ThreadLocal()
    final val ResponseHolder: ThreadLocal[HttpServletResponse] = ThreadLocal()
    final val SessionHolder: ThreadLocal[HttpSession] = ThreadLocal()
    final val ServletContextHolder: ThreadLocal[ServletContext] = ThreadLocal()

}
