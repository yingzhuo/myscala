/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import javax.servlet.ServletContext
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import scala.language.implicitConversions

package object web {

    implicit def requestToRichRequest(request: HttpServletRequest): RichHttpServletRequest = new RichHttpServletRequest(request)

    implicit def responseToRichResponse(response: HttpServletResponse): RichHttpServletResponse = new RichHttpServletResponse(response)

    implicit def servletContextToRichServletContext(servletContext: ServletContext): RichServletContext = new RichServletContext(servletContext)

}
