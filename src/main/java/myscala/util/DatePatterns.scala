/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import java.text.{ParseException, SimpleDateFormat}
import java.util.Date

import scala.util.{Success, Try}

object DatePatterns {

    final val STD_DATE_PATTERN: String = "yyyy-MM-dd"
    final val STD_TIME_PATTERN: String = "HH:mm:ss"
    final val STD_DATETIME_PATTERN: String = "yyyy-MM-dd HH:mm:ss"

    final val RFC822_DATETIME_PATTERN: String = "EEE, dd MMM yyyy HH:mm:ss 'GMT'"
    final val W3C_DATETIME_PATTERN: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    final val RFC822_PATTENRS: Array[String] = Array(
        "EEE, dd MMM yy HH:mm:ss z",
        "EEE, dd MMM yy HH:mm z",
        "dd MMM yy HH:mm:ss z",
        "dd MMM yy HH:mm z"
    )

    final val W3CDATETIME_PATTERNS: Array[String] = Array(
        "yyyy-MM-dd'T'HH:mm:ss.SSSz",
        "yyyy-MM-dd't'HH:mm:ss.SSSz",
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "yyyy-MM-dd't'HH:mm:ss.SSS'z'",
        "yyyy-MM-dd'T'HH:mm:ssz",
        "yyyy-MM-dd't'HH:mm:ssz",
        "yyyy-MM-dd'T'HH:mm:ss'Z'",
        "yyyy-MM-dd't'HH:mm:ss'z'",
        "yyyy-MM-dd'T'HH:mmz",
        "yyyy-MM'T'HH:mmz",
        "yyyy'T'HH:mmz",
        "yyyy-MM-dd't'HH:mmz",
        "yyyy-MM-dd'T'HH:mm'Z'",
        "yyyy-MM-dd't'HH:mm'z'",
        "yyyy-MM-dd", "yyyy-MM",
        "yyyy")

    final val STD_PATTERNS: Array[String] = Array(
        "yyyy-MM-dd HH:mm:ss,SSS",
        "yyyy-MM-dd HH:mm:ss.SSS",
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "yyyy-MM-dd",
        "yyyy/MM/dd HH:mm:ss,SSS",
        "yyyy/MM/dd HH:mm:ss.SSS",
        "yyyy/MM/dd HH:mm:ss",
        "yyyy/MM/dd HH:mm",
        "yyyy/MM/dd",
        "yyyyMMddHHmmss",
        "yyyyMMdd",
        "hh:mm:ss,SSS",
        "hh:mm:ss.SSS",
        "hh:mm:ss")

    @volatile lazy private val ALL = RFC822_PATTENRS ++ W3CDATETIME_PATTERNS ++ STD_PATTERNS

    def parse(test: String): Date = {

        for (pattern <- ALL) {
            val formatter = new SimpleDateFormat(pattern)
            Try(formatter.parse(test)) match {
                case Success(x) => return x
                case _ =>
            }
        }

        throw new ParseException(s"For input string: $test", 0)
    }
}
