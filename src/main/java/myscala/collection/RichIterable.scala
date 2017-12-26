/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.collection

private[collection] final class RichIterable[T](val iterable: Iterable[T]) {

    require(iterable != null)

    def containsIgnoreCase(s: String)(implicit f: T => String): Boolean = iterable.exists(_ equalsIgnoreCase s)

    def containsStringMatches(regex: String)(implicit f: T => String): Boolean = iterable.exists(_ matches regex)

}
