/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

object JDKs {

    final val JAVA_VERSION: String = scala.util.Properties.javaVersion

    final val IS_JAVA_9: Boolean = JAVA_VERSION.startsWith("1.9.")
    final val IS_JAVA_8: Boolean = JAVA_VERSION.startsWith("1.8.")
    final val IS_JAVA_7: Boolean = JAVA_VERSION.startsWith("1.7.")
    final val IS_JAVA_6: Boolean = JAVA_VERSION.startsWith("1.6.")
    final val IS_JAVA_5: Boolean = JAVA_VERSION.startsWith("1.5.")

    final val IS_AT_LEAST_JAVA_9: Boolean = IS_JAVA_9
    final val IS_AT_LEAST_JAVA_8: Boolean = IS_JAVA_8 || IS_AT_LEAST_JAVA_9
    final val IS_AT_LEAST_JAVA_7: Boolean  = IS_JAVA_7 || IS_AT_LEAST_JAVA_8
    final val IS_AT_LEAST_JAVA_6: Boolean = IS_JAVA_6 || IS_AT_LEAST_JAVA_7
    final val IS_AT_LEAST_JAVA_5: Boolean = IS_JAVA_5 || IS_AT_LEAST_JAVA_6

}
