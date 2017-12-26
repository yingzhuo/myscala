/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.collection

private[collection] final class RichMap[K, V](val map: Map[K, V]) {

    require(map != null)

    def containsKeyIgnoreCase(s: String)(implicit f: K => String): Boolean = map.keySet.containsIgnoreCase(s)

    def containsValueIgnoreCase(s: String)(implicit f: V => String): Boolean = map.values.containsIgnoreCase(s)

    def containsKeyStringMatches(regex: String)(implicit f: K => String): Boolean = map.keySet.containsStringMatches(regex)

    def containsValueStringMatches(regex: String)(implicit f: V => String): Boolean = map.values.containsStringMatches(regex)

}
