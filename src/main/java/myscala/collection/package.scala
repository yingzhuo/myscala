/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import scala.language.implicitConversions

package object collection {

    implicit def iterableToRichIterable[T](iterable: Iterable[T]): RichIterable[T] = new RichIterable[T](iterable)

    implicit def mapToRichMap[K, V](map: Map[K, V]): RichMap[K, V] = new RichMap[K, V](map)

}
