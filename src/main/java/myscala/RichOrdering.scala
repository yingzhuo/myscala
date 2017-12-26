/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

private[myscala] final class RichOrdering[T](val ordering: Ordering[T]) {

    require(ordering != null)

    def reverse: Ordering[T] = myscala.util.Orderings.reverse(ordering)

}
