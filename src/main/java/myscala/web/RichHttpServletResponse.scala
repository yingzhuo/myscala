/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.web

import java.nio.charset.Charset
import javax.servlet.http.HttpServletResponse

import myscala.{Charsets, Validate}

private[web] final class RichHttpServletResponse(val response: HttpServletResponse) {

    Validate.notNull(response)

    def setBufferOff(): Unit = {
        // Http 1.0 header
        response.setHeader("Buffer", "false")
        response.setHeader("Pragma", "no-cache")
        response.setDateHeader("Expires", 1L)
        // Http 1.1 header
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0")
    }

    def setFileDownloadHeader(fileName: String, charset: Charset = Charsets.UTF_8, contentType: String = "application/x-download"): Unit = {
        response.setContentType(contentType)
        val encodedfileName = new String(fileName.getBytes, charset)
        response.setHeader("Content-Disposition", s"attachment; filename=$encodedfileName")
    }

}
