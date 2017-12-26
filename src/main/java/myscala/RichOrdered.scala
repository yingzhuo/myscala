/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

private[myscala] final class RichOrdered[T](val ordered: Ordered[T]) {

    require(ordered != null)

    def reverse: Ordered[T] = myscala.util.Ordereds.reverse(ordered)

}
