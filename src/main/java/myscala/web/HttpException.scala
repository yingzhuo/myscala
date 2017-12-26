/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.web

sealed abstract class CodedException(val message: String) extends RuntimeException(message) with Ordered[CodedException] {

    def code: Int

    def toMap: Map[String, Any] = Map("code" -> code, "message" -> message)

    override def compare(that: CodedException): Int = this.code compare that.code

}

object HttpException {

    def apply(HttpStatusTrait: HttpStatus, message: String): HttpException = apply(HttpStatusTrait.value, message)

    def apply(code: Int, message: String): HttpException = code match {
        // 2xx
        case 204 => NoContentException(message)
        //4xx
        case 400 => BadRequestException(message)
        case 401 => UnauthorizedException(message)
        case 402 => PaymentRequiredException(message)
        case 403 => ForbiddenException(message)
        case 404 => NotFoundException(message)
        case 405 => MethodNotAllowedException(message)
        case 406 => NotAcceptableException(message)
        case 407 => ProxyAuthenticationRequiredException(message)
        case 408 => RequestTimeoutException(message)
        case 409 => ConflictException(message)
        case 410 => GoneException(message)
        case 411 => LengthRequiredException(message)
        case 412 => PreconditionFailedException(message)
        case 413 => PayloadTooLargeException(message)
        case 414 => UriTooLongException(message)
        case 415 => UnsupportedMediaTypeException(message)
        case 416 => RequestedRangeNotSatisfiableException(message)
        case 417 => ExpectationFailedException(message)
        case 418 => IAmATeapotException(message)
        case 419 => InsufficientSpaceOnResourceException(message)
        case 420 => MethodFailureException(message)
        case 421 => DestinationLockedException(message)
        case 422 => UnprocessableEntityException(message)
        case 423 => LockedException(message)
        case 424 => FailedDependencyException(message)
        case 426 => UpgradeRequiredException(message)
        case 428 => PreconditionRequiredException(message)
        case 429 => TooManyRequestsException(message)
        case 431 => RequestHeaderFieldsTooLargeException(message)
        case 451 => UnavailableForLegalReasonsException(message)
        // 5xx
        case 500 => InternalServerErrorException(message)
        case 501 => NotImplementedException(message)
        case 502 => BadGatewayException(message)
        case 503 => ServiceUnavailableException(message)
        case 504 => GatewayTimeoutException(message)
        case 505 => HttpVersionNotSupportedException(message)
        case 506 => VariantAlsoNegotiatesException(message)
        case 507 => InsufficientStorageException(message)
        case 508 => LoopDetectedException(message)
        case 509 => BandwidthLimitExceededException(message)
        case 510 => NotExtendedException(message)
        case 511 => NetworkAuthenticationRequiredException(message)
        case _ => throw new IllegalArgumentException(s"For input: $code")
    }

    object Informational {
        def unapply(e: HttpException): Boolean = e.code >= 100 && e.code <= 199
    }

    object Success {
        def unapply(e: HttpException): Boolean = e.code >= 200 && e.code <= 299
    }

    object Redirection {
        def unapply(e: HttpException): Boolean = e.code >= 300 && e.code <= 399
    }

    object ClientError {
        def unapply(e: HttpException): Boolean = e.code >= 400 && e.code <= 499
    }

    object ServerError {
        def unapply(e: HttpException): Boolean = e.code >= 500 && e.code <= 599
    }
}

sealed abstract class HttpException(override val code: Int, override val message: String) extends CodedException(message)

/*2xx*/

/*204*/ final case class NoContentException(override val message: String = null) extends HttpException(HttpStatus.NO_CONTENT.value, message)

/*4xx*/

/*400*/ final case class BadRequestException(override val message: String = null) extends HttpException(HttpStatus.BAD_REQUEST.value, message)

/*401*/ final case class UnauthorizedException(override val message: String = null) extends HttpException(HttpStatus.UNAUTHORIZED.value, message)

/*402*/ final case class PaymentRequiredException(override val message: String = null) extends HttpException(HttpStatus.PAYMENT_REQUIRED.value, message)

/*403*/ final case class ForbiddenException(override val message: String = null) extends HttpException(HttpStatus.FORBIDDEN.value, message)

/*404*/ final case class NotFoundException(override val message: String = null) extends HttpException(HttpStatus.NOT_FOUND.value, message)

/*405*/ final case class MethodNotAllowedException(override val message: String = null) extends HttpException(HttpStatus.METHOD_NOT_ALLOWED.value, message)

/*406*/ final case class NotAcceptableException(override val message: String = null) extends HttpException(HttpStatus.NOT_ACCEPTABLE.value, message)

/*407*/ final case class ProxyAuthenticationRequiredException(override val message: String = null) extends HttpException(HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value, message)

/*408*/ final case class RequestTimeoutException(override val message: String = null) extends HttpException(HttpStatus.REQUEST_TIMEOUT.value, message)

/*409*/ final case class ConflictException(override val message: String = null) extends HttpException(HttpStatus.CONFLICT.value, message)

/*410*/ final case class GoneException(override val message: String = null) extends HttpException(HttpStatus.GONE.value, message)

/*411*/ final case class LengthRequiredException(override val message: String = null) extends HttpException(HttpStatus.LENGTH_REQUIRED.value, message)

/*412*/ final case class PreconditionFailedException(override val message: String = null) extends HttpException(HttpStatus.PRECONDITION_FAILED.value, message)

/*413*/ final case class PayloadTooLargeException(override val message: String = null) extends HttpException(HttpStatus.PAYLOAD_TOO_LARGE.value, message)

/*414*/ final case class UriTooLongException(override val message: String = null) extends HttpException(HttpStatus.URI_TOO_LONG.value, message)

/*415*/ final case class UnsupportedMediaTypeException(override val message: String = null) extends HttpException(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value, message)

/*416*/ final case class RequestedRangeNotSatisfiableException(override val message: String = null) extends HttpException(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value, message)

/*417*/ final case class ExpectationFailedException(override val message: String = null) extends HttpException(HttpStatus.EXPECTATION_FAILED.value, message)

/*418*/ final case class IAmATeapotException(override val message: String = null) extends HttpException(HttpStatus.I_AM_A_TEAPOT.value, message)

/*419*/ final case class InsufficientSpaceOnResourceException(override val message: String = null) extends HttpException(HttpStatus.INSUFFICIENT_SPACE_ON_RESOURCE.value, message)

/*420*/ final case class MethodFailureException(override val message: String = null) extends HttpException(HttpStatus.METHOD_FAILURE.value, message)

/*421*/ final case class DestinationLockedException(override val message: String = null) extends HttpException(HttpStatus.DESTINATION_LOCKED.value, message)

/*422*/ final case class UnprocessableEntityException(override val message: String = null) extends HttpException(HttpStatus.UNPROCESSABLE_ENTITY.value, message)

/*423*/ final case class LockedException(override val message: String = null) extends HttpException(HttpStatus.LOCKED.value, message)

/*424*/ final case class FailedDependencyException(override val message: String = null) extends HttpException(HttpStatus.FAILED_DEPENDENCY.value, message)

/*426*/ final case class UpgradeRequiredException(override val message: String = null) extends HttpException(HttpStatus.UPGRADE_REQUIRED.value, message)

/*428*/ final case class PreconditionRequiredException(override val message: String = null) extends HttpException(HttpStatus.PRECONDITION_REQUIRED.value, message)

/*429*/ final case class TooManyRequestsException(override val message: String = null) extends HttpException(HttpStatus.TOO_MANY_REQUESTS.value, message)

/*431*/ final case class RequestHeaderFieldsTooLargeException(override val message: String = null) extends HttpException(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE.value, message)

/*451*/ final case class UnavailableForLegalReasonsException(override val message: String = null) extends HttpException(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value, message)

/*5xx*/

/*500*/ final case class InternalServerErrorException(override val message: String = null) extends HttpException(HttpStatus.INTERNAL_SERVER_ERROR.value, message)

/*501*/ final case class NotImplementedException(override val message: String = null) extends HttpException(HttpStatus.NOT_IMPLEMENTED.value, message)

/*502*/ final case class BadGatewayException(override val message: String = null) extends HttpException(HttpStatus.BAD_GATEWAY.value, message)

/*503*/ final case class ServiceUnavailableException(override val message: String = null) extends HttpException(HttpStatus.SERVICE_UNAVAILABLE.value, message)

/*504*/ final case class GatewayTimeoutException(override val message: String = null) extends HttpException(HttpStatus.GATEWAY_TIMEOUT.value, message)

/*505*/ final case class HttpVersionNotSupportedException(override val message: String = null) extends HttpException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value, message)

/*506*/ final case class VariantAlsoNegotiatesException(override val message: String = null) extends HttpException(HttpStatus.VARIANT_ALSO_NEGOTIATES.value, message)

/*507*/ final case class InsufficientStorageException(override val message: String = null) extends HttpException(HttpStatus.INSUFFICIENT_STORAGE.value, message)

/*508*/ final case class LoopDetectedException(override val message: String = null) extends HttpException(HttpStatus.LOOP_DETECTED.value, message)

/*509*/ final case class BandwidthLimitExceededException(override val message: String = null) extends HttpException(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value, message)

/*510*/ final case class NotExtendedException(override val message: String = null) extends HttpException(HttpStatus.NOT_EXTENDED.value, message)

/*511*/ final case class NetworkAuthenticationRequiredException(override val message: String = null) extends HttpException(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value, message)
