/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.web

sealed trait HttpStatus {

    final val isInformational: Boolean = value >= 100 || value <= 199
    final val isSuccess: Boolean = value >= 200 || value <= 299
    final val isRedirection: Boolean = value >= 300 || value <= 399
    final val isClientError: Boolean = value >= 400 || value <= 499
    final val isServerError: Boolean = value >= 500 || value <= 599

    final override def toString: String = s"($value)$reasonPhrase"

    def value: Int

    def reasonPhrase: String

}

object HttpStatus {

    private[this] val AllHttpStatuses = Map[Int, HttpStatus](
        100 -> CONTINUE,
        101 -> SWITCHING_PROTOCOLS,
        102 -> PROCESSING,
        103 -> CHECKPOINT,
        200 -> OK,
        201 -> CREATED,
        202 -> ACCEPTED,
        203 -> NON_AUTHORITATIVE_INFORMATION,
        204 -> NO_CONTENT,
        205 -> RESET_CONTENT,
        206 -> PARTIAL_CONTENT,
        207 -> MULTI_STATUS,
        208 -> ALREADY_REPORTED,
        226 -> IM_USED,
        300 -> MULTIPLE_CHOICES,
        301 -> MOVED_PERMANENTLY,
        302 -> FOUND,
        303 -> SEE_OTHER,
        304 -> NOT_MODIFIED,
        305 -> USE_PROXY,
        307 -> TEMPORARY_REDIRECT,
        308 -> PERMANENT_REDIRECT,
        400 -> BAD_REQUEST,
        401 -> UNAUTHORIZED,
        402 -> PAYMENT_REQUIRED,
        403 -> FORBIDDEN,
        404 -> NOT_FOUND,
        405 -> METHOD_NOT_ALLOWED,
        406 -> NOT_ACCEPTABLE,
        407 -> PROXY_AUTHENTICATION_REQUIRED,
        408 -> REQUEST_TIMEOUT,
        409 -> CONFLICT,
        410 -> GONE,
        411 -> LENGTH_REQUIRED,
        412 -> PRECONDITION_FAILED,
        413 -> PAYLOAD_TOO_LARGE,
        414 -> URI_TOO_LONG,
        415 -> UNSUPPORTED_MEDIA_TYPE,
        416 -> REQUESTED_RANGE_NOT_SATISFIABLE,
        417 -> EXPECTATION_FAILED,
        418 -> I_AM_A_TEAPOT,
        419 -> INSUFFICIENT_SPACE_ON_RESOURCE,
        420 -> METHOD_NOT_ALLOWED,
        421 -> DESTINATION_LOCKED,
        422 -> UNPROCESSABLE_ENTITY,
        423 -> LOCKED,
        424 -> FAILED_DEPENDENCY,
        426 -> UPGRADE_REQUIRED,
        428 -> PRECONDITION_REQUIRED,
        429 -> TOO_MANY_REQUESTS,
        431 -> REQUEST_HEADER_FIELDS_TOO_LARGE,
        451 -> UNAVAILABLE_FOR_LEGAL_REASONS,
        500 -> INTERNAL_SERVER_ERROR,
        501 -> NOT_IMPLEMENTED,
        502 -> BAD_GATEWAY,
        503 -> SERVICE_UNAVAILABLE,
        504 -> GATEWAY_TIMEOUT,
        505 -> HTTP_VERSION_NOT_SUPPORTED,
        506 -> VARIANT_ALSO_NEGOTIATES,
        507 -> INSUFFICIENT_STORAGE,
        508 -> LOOP_DETECTED,
        509 -> BANDWIDTH_LIMIT_EXCEEDED,
        510 -> NOT_EXTENDED,
        511 -> NETWORK_AUTHENTICATION_REQUIRED
    )

    private[this] val AllValues: Set[Int] = AllHttpStatuses.keySet

    def valueOf(value: Int): HttpStatus = apply(value)

    def apply(value: Int): HttpStatus = value match {
        case x: Int if AllValues.contains(x) => AllHttpStatuses(value)
        case _ => throw new IllegalArgumentException(s"For Input: $value")
    }

    // 1xx Informational

    case object CONTINUE extends HttpStatus {
        val value = 100
        val reasonPhrase = "Continue"
    }

    case object SWITCHING_PROTOCOLS extends HttpStatus {
        val value: Int = 101
        val reasonPhrase: String = "Switching Protocols"
    }

    case object PROCESSING extends HttpStatus {
        val value: Int = 102
        val reasonPhrase: String = "Processing"
    }

    case object CHECKPOINT extends HttpStatus {
        val value: Int = 103
        val reasonPhrase: String = "Checkpoint"
    }

    // 2xx Success

    case object OK extends HttpStatus {
        val value: Int = 200
        val reasonPhrase: String = "OK"
    }

    case object CREATED extends HttpStatus {
        val value: Int = 201
        val reasonPhrase: String = "Created"
    }

    case object ACCEPTED extends HttpStatus {
        val value: Int = 202
        val reasonPhrase: String = "Accepted"
    }

    case object NON_AUTHORITATIVE_INFORMATION extends HttpStatus {
        val value: Int = 203
        val reasonPhrase: String = "Non-Authoritative Information"
    }

    case object NO_CONTENT extends HttpStatus {
        val value: Int = 204
        val reasonPhrase: String = "No Content"
    }

    case object RESET_CONTENT extends HttpStatus {
        val value: Int = 205
        val reasonPhrase: String = "Reset Content"
    }

    case object PARTIAL_CONTENT extends HttpStatus {
        val value: Int = 206
        val reasonPhrase: String = "Partial Content"
    }

    case object MULTI_STATUS extends HttpStatus {
        val value: Int = 207
        val reasonPhrase: String = "Multi-Status"
    }

    case object ALREADY_REPORTED extends HttpStatus {
        val value: Int = 208
        val reasonPhrase: String = "Already Reported"
    }

    case object IM_USED extends HttpStatus {
        val value: Int = 226
        val reasonPhrase: String = "IM Used"
    }

    // 3xx Redirection

    case object MULTIPLE_CHOICES extends HttpStatus {
        val value: Int = 300
        val reasonPhrase: String = "Multiple Choices"
    }

    case object MOVED_PERMANENTLY extends HttpStatus {
        val value: Int = 301
        val reasonPhrase: String = "Moved Permanently"
    }

    case object FOUND extends HttpStatus {
        val value: Int = 302
        val reasonPhrase: String = "Found"
    }

    case object SEE_OTHER extends HttpStatus {
        val value: Int = 303
        val reasonPhrase: String = "See Other"
    }

    case object NOT_MODIFIED extends HttpStatus {
        val value: Int = 304
        val reasonPhrase: String = "Not Modified"
    }

    case object USE_PROXY extends HttpStatus {
        val value: Int = 305
        val reasonPhrase: String = "User Proxy"
    }

    case object TEMPORARY_REDIRECT extends HttpStatus {
        val value: Int = 307
        val reasonPhrase: String = "Temporary Redirect"
    }

    case object PERMANENT_REDIRECT extends HttpStatus {
        val value: Int = 308
        val reasonPhrase: String = "Permanent Redirect"
    }

    // 4xx Client Error

    case object BAD_REQUEST extends HttpStatus {
        val value: Int = 400
        val reasonPhrase: String = "Bad Request"
    }

    case object UNAUTHORIZED extends HttpStatus {
        val value: Int = 401
        val reasonPhrase: String = "Unauthorized"
    }

    case object PAYMENT_REQUIRED extends HttpStatus {
        val value: Int = 402
        val reasonPhrase: String = "Payment Required"
    }

    case object FORBIDDEN extends HttpStatus {
        val value: Int = 403
        val reasonPhrase: String = "Forbidden"
    }

    case object NOT_FOUND extends HttpStatus {
        val value: Int = 404
        val reasonPhrase: String = "Not Found"
    }

    case object METHOD_NOT_ALLOWED extends HttpStatus {
        val value: Int = 405
        val reasonPhrase: String = "Method Not Allowed"
    }

    case object NOT_ACCEPTABLE extends HttpStatus {
        val value: Int = 406
        val reasonPhrase: String = "Not Acceptable"
    }

    case object PROXY_AUTHENTICATION_REQUIRED extends HttpStatus {
        val value: Int = 407
        val reasonPhrase: String = "Proxy Authentication Required"
    }

    case object REQUEST_TIMEOUT extends HttpStatus {
        val value: Int = 408
        val reasonPhrase: String = "Request Timeout"
    }

    case object CONFLICT extends HttpStatus {
        val value: Int = 409
        val reasonPhrase: String = "Conflict"
    }

    case object GONE extends HttpStatus {
        val value: Int = 410
        val reasonPhrase: String = "Gone"
    }

    case object LENGTH_REQUIRED extends HttpStatus {
        val value: Int = 411
        val reasonPhrase: String = "Length Required"
    }

    case object PRECONDITION_FAILED extends HttpStatus {
        val value: Int = 412
        val reasonPhrase: String = "Precondition Failed"
    }

    case object PAYLOAD_TOO_LARGE extends HttpStatus {
        val value: Int = 413
        val reasonPhrase: String = "Payload Too Large"
    }

    case object URI_TOO_LONG extends HttpStatus {
        val value: Int = 414
        val reasonPhrase: String = "URI Too Long"
    }

    case object UNSUPPORTED_MEDIA_TYPE extends HttpStatus {
        val value: Int = 415
        val reasonPhrase: String = "Unsupported Media Type"
    }

    case object REQUESTED_RANGE_NOT_SATISFIABLE extends HttpStatus {
        val value: Int = 416
        val reasonPhrase: String = "Requested Range Not Satisfiable"
    }

    case object EXPECTATION_FAILED extends HttpStatus {
        val value: Int = 417
        val reasonPhrase: String = "Expectation Failed"
    }

    case object I_AM_A_TEAPOT extends HttpStatus {
        val value: Int = 418
        val reasonPhrase: String = "I'm a teapot"
    }

    case object INSUFFICIENT_SPACE_ON_RESOURCE extends HttpStatus {
        val value: Int = 419
        val reasonPhrase: String = "Insufficient Space On Resource"
    }

    case object METHOD_FAILURE extends HttpStatus {
        val value: Int = 420
        val reasonPhrase: String = "Method Failure"
    }

    case object DESTINATION_LOCKED extends HttpStatus {
        val value: Int = 421
        val reasonPhrase: String = "Destination Locked"
    }

    case object UNPROCESSABLE_ENTITY extends HttpStatus {
        val value: Int = 422
        val reasonPhrase: String = "Unprocessable Entity"
    }

    case object LOCKED extends HttpStatus {
        val value: Int = 423
        val reasonPhrase: String = "Locked"
    }

    case object FAILED_DEPENDENCY extends HttpStatus {
        val value: Int = 424
        val reasonPhrase: String = "Failed Dependency"
    }

    case object UPGRADE_REQUIRED extends HttpStatus {
        val value: Int = 426
        val reasonPhrase: String = "Upgrade Required"
    }

    case object PRECONDITION_REQUIRED extends HttpStatus {
        val value: Int = 428
        val reasonPhrase: String = "Precondition Required"
    }

    case object TOO_MANY_REQUESTS extends HttpStatus {
        val value: Int = 429
        val reasonPhrase: String = "Too Many Requests"
    }

    case object REQUEST_HEADER_FIELDS_TOO_LARGE extends HttpStatus {
        val value: Int = 431
        val reasonPhrase: String = "Request Header Fields Too Large"
    }

    case object UNAVAILABLE_FOR_LEGAL_REASONS extends HttpStatus {
        val value: Int = 451
        val reasonPhrase: String = "Unavailable For Legal Reasons"
    }

    // 5xx Server Error

    case object INTERNAL_SERVER_ERROR extends HttpStatus {
        val value: Int = 500
        val reasonPhrase: String = "Internal Server Error"
    }

    case object NOT_IMPLEMENTED extends HttpStatus {
        val value: Int = 501
        val reasonPhrase: String = "Not Implemented"
    }

    case object BAD_GATEWAY extends HttpStatus {
        val value: Int = 502
        val reasonPhrase: String = "Bad Gateway"
    }

    case object SERVICE_UNAVAILABLE extends HttpStatus {
        val value: Int = 503
        val reasonPhrase: String = "Service Unavailable"
    }

    case object GATEWAY_TIMEOUT extends HttpStatus {
        val value: Int = 504
        val reasonPhrase: String = "Gateway Timeout"
    }

    case object HTTP_VERSION_NOT_SUPPORTED extends HttpStatus {
        val value: Int = 505
        val reasonPhrase: String = "HTTP Version Not Supported"
    }

    case object VARIANT_ALSO_NEGOTIATES extends HttpStatus {
        val value: Int = 506
        val reasonPhrase: String = "Variant Also Negotiates"
    }

    case object INSUFFICIENT_STORAGE extends HttpStatus {
        val value: Int = 507
        val reasonPhrase: String = "Insufficient Storage"
    }

    case object LOOP_DETECTED extends HttpStatus {
        val value: Int = 508
        val reasonPhrase: String = "Loop Detected"
    }

    case object BANDWIDTH_LIMIT_EXCEEDED extends HttpStatus {
        val value: Int = 509
        val reasonPhrase: String = "Bandwidth Limit Exceeded"
    }

    case object NOT_EXTENDED extends HttpStatus {
        val value: Int = 510
        val reasonPhrase: String = "Not Extended"
    }

    case object NETWORK_AUTHENTICATION_REQUIRED extends HttpStatus {
        val value: Int = 511
        val reasonPhrase: String = "Network Authentication Required"
    }

}
