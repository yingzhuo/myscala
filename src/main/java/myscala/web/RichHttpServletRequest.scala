/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.web

import javax.servlet.http.HttpServletRequest

import scala.collection.JavaConverters._
import myscala._
import myscala.util.PredicatedOption

private[web] final class RichHttpServletRequest(val request: HttpServletRequest) {

    Validate.notNull(request)

    def getBasePath: String = {
        val path = request.getContextPath
        request.getScheme + "://" + request.getServerName + ":" + request.getServerPort + path + "/"
    }

    def getClientIpAddress: String = request match {
        case x: HttpServletRequest =>
            implicit val predicate = { s: String => s.isNotNullOrEmpty && !s.equalsIgnoreCase("unknown") }

            val op = PredicatedOption(x.getHeader("X-Forwarded-For")) orElse
                PredicatedOption(x.getHeader("Proxy-Client-IP")) orElse
                PredicatedOption(x.getHeader("WL-Proxy-Client-IP")) orElse
                PredicatedOption(x.getHeader("HTTP_X_FORWARDED_FOR")) orElse
                PredicatedOption(x.getHeader("HTTP_CLIENT_IP")) orElse
                PredicatedOption(x.getHeader("X-REAL-IP")) orElse
                PredicatedOption(x.getRemoteAddr)

            op.orNull
        case _ =>
            null
    }

    def getClientIpAddressOption: Option[String] = Option(getClientIpAddress)

    def getHeaderNamesList: List[String] = request.getHeaderNames.asScala.toList

    def getHeaderNamesSet: Set[String] = request.getHeaderNames.asScala.toSet

    def hasHeaderName(name: String): Boolean = request.getHeaderNamesSet contains name

    def getHeaderOption(name: String): Option[String] = request.getHeader(name) match {
        case "" => None
        case default => Option(name)
    }

    def getParameterNamesList: List[String] = request.getParameterNames.asScala.toList

    def getParameterNamesSet: Set[String] = request.getParameterNames.asScala.toSet

    def hasParameter(name: String): Boolean = getParameterNamesSet contains name

    def getParameter(name: String): Option[String] = request.getParameter(name) match {
        case "" => None
        case default => Option(name)
    }

    def isAjaxRequest: Boolean = "XMLHttpRequest" == request.getHeader("X-Requested-With") || request.getHeader("Accept").contains("application/json")

    def isPjaxRequest: Boolean = request.getHeader("X-PJAX").isNotBlank

    def isFlashRequest: Boolean = "Shockwave Flash" == request.getHeader("User-Agent") || request.getHeader("X-Flash-Version").isNotBlank

    def isMultipartRequest: Boolean = {
        val `type` = request.getHeader("Content-Type")
        (`type` != null) && `type`.startsWith("multipart/form-data")
    }

    def isGzipSupportedRequest: Boolean = {
        val browserEncodings = request.getHeader("Accept-Encoding")
        (browserEncodings != null) && browserEncodings.contains("gzip")
    }

    def isRobot(request: HttpServletRequest): Boolean = {
        val ua = request.getHeader("user-agent")
        if (ua == null || ua.isBlank) return false

        ua.contains("Baiduspider") ||
            ua.contains("Googlebot") ||
            ua.contains("sogou") ||
            ua.contains("sina") ||
            ua.contains("iaskspider") ||
            ua.contains("ia_archiver") ||
            ua.contains("Sosospider") ||
            ua.contains("YoudaoBot") ||
            ua.contains("yahoo") ||
            ua.contains("yodao") ||
            ua.contains("MSNBot") ||
            ua.contains("spider") ||
            ua.contains("Twiceler") ||
            ua.contains("Sosoimagespider") ||
            ua.contains("naver.com/robots") ||
            ua.contains("Nutch") ||
            ua.contains("spider")
    }
}
