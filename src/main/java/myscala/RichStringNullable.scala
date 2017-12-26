/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

private[myscala] final class RichStringNullable(val s: String) {

    // empty / blank / null
    def isNullOrEmpty: Boolean = s == null || s.isEmpty

    def isNotNullOrEmpty: Boolean = !s.isNullOrEmpty

    def isNullOrBlank: Boolean = s == null || s.isBlank

    def isNotNullOrBlank: Boolean = !s.isNullOrBlank

    def isBlank: Boolean = s == null || s.forall(_.isWhitespace)

    def isNotBlank: Boolean = !s.isBlank

}
