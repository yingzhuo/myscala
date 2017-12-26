/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

private[myscala] class RichChar(val char: Char) {

    Validate.notNull(char)

    @inline def isAlphabetic: Boolean = Character.isAlphabetic(char)

    @inline def isAlphabeticSpace: Boolean = char.isAlphabetic || char == ' '

    @inline def isNumeric: Boolean = Character.isDigit(char)

    @inline def isAlphanumeric: Boolean = char.isNumeric || char.isAlphabetic

    @inline def isAlphanumericSpace: Boolean = char.isAlphanumeric || char == ' '

}
