/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.web

import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest

import myscala._

object BasicAuthentication {

    final private val HeaderValueRegex = "Basic (.+)".r

    def apply(username: String, password: String): String = "Basic " + s"$username:$password".base64UrlEncode()

    def unapply(request: ServletRequest): Option[(String, String)] = request match {
        case null => None
        case x: HttpServletRequest => unapply(x.getHeader("Authorization"))
        case _ => None
    }

    def unapply(headerValue: String): Option[(String, String)] = headerValue match {
        case null => None
        case HeaderValueRegex(encodedString) =>
            val up = encodedString.base64UrlDecode()
            val parts = up.split(":")
            if (parts.length == 2) Option((parts(0), parts(1))) else None
        case _ =>
            None
    }

}
