/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.{Base64, Calendar, Date}

import scala.io.Codec
import scala.util.{Success, Try}
import myscala.util.{AESUtils, DatePatterns, DateRange, Strings}

private[myscala] class RichString(val s: String) {

    Validate.notNull(s)

    // 操作符重载
    def ~==(that: String): Boolean =
        if (s == null && that == null) true else s equalsIgnoreCase that

    def !~==(that: String): Boolean = !(s ~== that)

    // abbr
    def abbr(maxWidth: Int, abbrevMarker: String = "...", offset: Int = 0): String = RichStringUtils.abbreviate(s, abbrevMarker, offset, maxWidth)

    // numeric / alphabetic / space
    def isNumeric: Boolean = s.forall(_.isNumeric)

    def isAlphabetic: Boolean = s.forall(_.isAlphabetic)

    def isAlphabeticSpace: Boolean = s.forall(_.isAlphabeticSpace)

    def isAlphanumeric: Boolean = s.forall(_.isAlphanumeric)

    def isAlphanumericSpace: Boolean = s.forall(_.isAlphanumericSpace)

    def containsNumeric: Boolean = s.exists(_.isNumeric)

    def containsAlphabetic: Boolean = s.exists(_.isAlphabetic)

    def containsAlphabeticSpace: Boolean = s.exists(_.isAlphabeticSpace)

    def containsAlphanumeric: Boolean = s.exists(_.isAlphanumeric)

    def containsAlphanumericSpace: Boolean = s.exists(_.isAlphanumericSpace)

    // strip
    def stripIn(chars: String): String = if (chars.isEmpty) s else s.filterNot(chars.contains(_))

    def stripWhitespace: String = s.filterNot(_.isWhitespace)

    def stripStart(strip: String): String = if (s.startsWith(strip)) s.substring(strip.length) else s

    def stripEnd(strip: String): String = if (s.endsWith(strip)) s.take(s.length - strip.length) else s

    def stripStartIgnoreCase(strip: String): String = if (s.startsWithIgnoreCase(strip)) s.substring(strip.length) else s

    def stripEndIgnoreCase(strip: String): String = if (s.endsWithIgnoreCase(strip)) s.substring(strip.length) else s

    def stripEmoji: String = s.replaceAll("[^\u0000-\uFFFF]", Strings.EMPTY)

    // startWith / endWith
    def startsWithIgnoreCase(start: String): Boolean = {
        if (start == null) {
            throw new NullPointerException
        }

        if (start.length > s.length)
            false
        else {
            val sub = s.take(start.length)
            sub.equalsIgnoreCase(start)
        }
    }

    def endsWithIgnoreCase(end: String): Boolean = {
        if (end == null) {
            throw new NullPointerException
        }

        if (end.length > s.length)
            false
        else {
            val sub = s.takeRight(end.length)
            sub.equalsIgnoreCase(end)
        }
    }

    // toCharset / toCodec
    def toCharset: Charset = Charset.forName(s)

    def toCharsetOrElse(default: => Charset): Charset = Try(s.toCharset) match {
        case Success(cs) => cs
        case _ => default
    }

    def toCodec: Codec = Codec(s)

    def toCodecOrElse(default: => Codec): Codec = Try(s.toCodec) match {
        case Success(cs) => cs
        case _ => default
    }

    // toDate
    def toDate(pattern: String): Date = new SimpleDateFormat(pattern).parse(s)

    def toDateOrElse(pattern: String, default: => Date): Date = Try(s.toDate(pattern)) match {
        case Success(d) => d
        case _ => default
    }

    def toDate: Date = DatePatterns.parse(s)

    def toDateOrElse(default: => Date): Date = Try(s.toDate) match {
        case Success(d) => d
        case _ => default
    }

    // toCalendar
    def toCalendar(pattern: String): Calendar = s.toDate(pattern).toCalendar

    def toCalendarOrElse(pattern: String, default: => Calendar): Calendar = Try(s.toCalendar(pattern)) match {
        case Success(c) => c
        case _ => default
    }

    def toCalendar: Calendar = s.toDate.toCalendar

    def toCalendarOrElse(default: => Calendar): Calendar = Try(s.toCalendar) match {
        case Success(c) => c
        case _ => default
    }

    // toBoolean
    def toBooleanOrElse(default: => Boolean): Boolean = Try(s.toBoolean) match {
        case Success(x) => x
        case _ => default
    }

    // toByte
    def toByteOrElse(default: => Byte): Byte = Try(s.toByte) match {
        case Success(x) => x
        case _ => default
    }

    // toShort
    def toShortOrElse(default: => Short): Short = Try(s.toShort) match {
        case Success(x) => x
        case _ => default
    }

    // toInt
    def toIntOrElse(default: => Int): Int = Try(s.toInt) match {
        case Success(x) => x
        case _ => default
    }

    // toLong
    def toLongOrElse(default: => Long): Long = Try(s.toLong) match {
        case Success(x) => x
        case _ => default
    }

    // toFloat
    def toFloatOrElse(default: => Float): Float = Try(s.toFloat) match {
        case Success(x) => x
        case _ => default
    }

    // toDouble
    def toDoubleOrElse(default: => Double): Double = Try(s.toDouble) match {
        case Success(x) => x
        case _ => default
    }

    // toBigInt
    def toBigInt: BigInt = BigInt(s)

    def toBigIntOrElse(default: => BigInt): BigInt = Try(s.toBigInt) match {
        case Success(x) => x
        case _ => default
    }

    // toBigDecimal
    def toBigDecimal: BigDecimal = BigDecimal(s)

    def toBigDecimalOrElse(default: => BigDecimal): BigDecimal = Try(s.toBigDecimal) match {
        case Success(x) => x
        case _ => default
    }

    // toEnum
    def toEnum[E <: Enum[E]](enumType: Class[E]): E = Enum.valueOf(enumType, s)

    def toEnumOrElse[E <: Enum[E]](enumType: Class[E], default: => E): E = Try(s.toEnum(enumType)) match {
        case Success(x) => x
        case _ => default
    }

    // toClass
    def toClass[_]: Class[_] = Class.forName(s)

    def toClassOrElse(default: => Class[_]): Class[_] = Try(s.toClass) match {
        case Success(x) => x
        case _ => default
    }

    // DateRange

    def to(endInclude: String)(implicit f: String => Date): DateRange = f(s) to f(endInclude)

    def until(endExclude: String)(implicit f: String => Date): DateRange = f(s) until f(endExclude)

    // md5
    def md5(charset: Charset = Charsets.UTF_8): String = {
        val m = MessageDigest.getInstance("MD5")
        val b = s.getBytes(charset)
        m.update(b, 0, b.length)
        new BigInteger(1, m.digest()).toString(16)
    }

    // sha-1
    def sha1(charset: Charset = Charsets.UTF_8): String = {
        val m = MessageDigest.getInstance("SHA1")
        val b = s.getBytes(charset)
        m.update(b, 0, b.length)
        new BigInteger(1, m.digest()).toString(16)
    }

    // sha-512
    def sha512(charset: Charset = Charsets.UTF_8): String = {
        val md = MessageDigest.getInstance("SHA-512")
        val bytes = md.digest(s.getBytes(charset))
        val sb = new StringBuilder
        for (i <- 0 until bytes.length) {
            sb.append(Integer.toString((bytes(i) & 0xff) + 0x100, 16).substring(1))
        }
        sb.toString
    }

    // base64
    def base64Encode(charset: Charset = Charsets.UTF_8): String = new String(Base64.getEncoder.encode(s.getBytes(charset)))

    def base64Decode(charset: Charset = Charsets.UTF_8): String = new String(Base64.getDecoder.decode(s.getBytes(charset)))

    def base64UrlEncode(charset: Charset = Charsets.UTF_8): String = new String(Base64.getUrlEncoder.encode(s.getBytes(charset)))

    def base64UrlDecode(charset: Charset = Charsets.UTF_8): String = new String(Base64.getUrlDecoder.decode(s.getBytes(charset)))

    // aes
    def aesEncrypt(secret: String): String = AESUtils.encrypt(s, secret)

    def aesDecrypt(secret: String): String = AESUtils.decrypt(s, secret)
}
