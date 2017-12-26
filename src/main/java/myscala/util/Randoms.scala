/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import java.util.UUID

import scala.util.Random

object Randoms {

    final private val NUMERIC = "0123456789"
    final private val ALPHABETIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    final private val ALPHABETIC_SPACE = ALPHABETIC + " "
    final private val ALPHANUMERIC = ALPHABETIC + NUMERIC
    final private val ALPHANUMERIC_SPACE = ALPHANUMERIC + " "

    def nextInt(n: Int): Int = Random.nextInt(n)

    def nextInt(minInclude: Int, maxExclude: Int): Int = nextInt(maxExclude - minInclude) + minInclude

    def nextInt(): Int = Random.nextInt()

    def nextLong(): Long = Random.nextLong()

    def nextFloat(): Float = Random.nextFloat()

    def nextBoolean(): Boolean = Random.nextBoolean()

    def nextBytes(bytes: Array[Byte]): Unit = Random.nextBytes(bytes)

    /* --------------------------------------------------------------------------------------------------------- */

    def nextPrintableString(n: Int): String = (1 to n).map(_ => nextPrintable).mkString

    def nextPrintable: Char = Random.nextPrintableChar()

    def nextNumericString(n: Int): String = (1 to n).map(_ => nextNumeric).mkString

    def nextNumeric: Char = nextChar(NUMERIC)

    def nextAlphabeticString(n: Int): String = (1 to n).map(_ => nextAlphabetic).mkString

    def nextAlphabetic: Char = nextChar(ALPHABETIC)

    def nextAlphabeticSpaceString(n: Int): String = (1 to n).map(_ => nextAlphabeticSpace).mkString

    def nextAlphabeticSpace: Char = nextChar(ALPHABETIC_SPACE)

    def nextChar(chars: String): Char = chars match {
        case null => throw new NullPointerException
        case x if x.length == 1 => chars(0)
        case _ => chars(nextInt(chars.length))
    }

    def nextAlphanumericString(n: Int): String = (1 to n).map(_ => nextAlphanumeric).mkString

    def nextAlphanumeric: Char = nextChar(ALPHANUMERIC)

    def nextAlphanumericSpaceString(n: Int): String = (1 to n).map(_ => nextAlphanumericSpace).mkString

    def nextAlphanumericSpace: Char = nextChar(ALPHANUMERIC_SPACE)

    /* --------------------------------------------------------------------------------------------------------- */

    def uuid(length32: Boolean = true): String = if (length32) uuid32() else uuid36()

    def uuid32(): String = uuid36().replaceAll("-", Strings.EMPTY)

    def uuid36(): String = UUID.randomUUID().toString
}
