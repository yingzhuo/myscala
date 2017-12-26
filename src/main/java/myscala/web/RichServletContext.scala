/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.web

import java.io.{File, IOException}
import java.net.URLDecoder
import javax.servlet.ServletContext

import myscala.Validate

private[web] final class RichServletContext(val sc: ServletContext) {

    Validate.notNull(sc)

    def webroot: String = {
        var dir = sc.getRealPath("/")
        if (dir == null) try {
            val url = sc.getResource("/")
            if (url != null && "file" == url.getProtocol) dir = URLDecoder.decode(url.getFile, "UTF-8")
            else throw new IllegalStateException("Can't get webroot dir, url = " + url)
        } catch {
            case e: IOException =>
                throw new IllegalStateException(e)
        }
        dir
    }

    def webrootFile: File = new File(webroot)

}
