/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.collection

import scala.util.{Success, Try}
import myscala.Named

trait NamedCollection[X] extends Iterable[(String, X)] {

    def iterator: Iterator[(String, X)]

    def findByName(name: String): X

    def findByNameAsOption(name: String): Option[X]

    def names(): Iterable[String]

    def namesSet(): Set[String]

    def namesIterator(): Iterator[String]

    def containsName(name: String): Boolean
}

object NamedCollection {

    def apply[X](map: Map[String, X]): NamedCollection[X] = new SimpleNamedCollection[X](if (map != null) map else Map())

    def apply[X](nameds: Named[X]*): NamedCollection[X] = new SimpleNamedCollection[X]((for (n <- nameds) yield (n.name, n.value)).toMap)

    private class SimpleNamedCollection[X](private val map: Map[String, X]) extends NamedCollection[X] {

        override def iterator: Iterator[(String, X)] = map.iterator

        override def findByName(name: String): X = findByNameAsOption(name) match {
            case Some(x) => x
            case _ => null.asInstanceOf
        }

        override def findByNameAsOption(name: String): Option[X] = Try(map(name)) match {
            case x@Success(_) => x.toOption
            case _ => None
        }

        override def names(): Iterable[String] = map.keys

        override def namesSet(): Set[String] = map.keySet

        override def namesIterator(): Iterator[String] = map.keysIterator

        override def containsName(name: String): Boolean = map contains name
    }

}