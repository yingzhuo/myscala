/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import scala.collection.mutable
import scala.util.{Success, Try}

private[myscala] final class RichEnumClass[E <: Enum[E]](enumClass: Class[E]) {

    Validate.notNull(enumClass)

    def getEnumConstantsAsList: List[E] = enumClass.getEnumConstants.toList

    def getEnumConstantsAsStream: Stream[E] = enumClass.getEnumConstants.toStream

    def getEnumConstantsAsMap: Map[String, E] = {
        val map: mutable.Map[String, E] = mutable.Map[String, E]()
        enumClass.getEnumConstants.foreach { e =>
            map += e.toString -> e
        }
        map.toMap
    }

    def isValidEnum(name: String): Boolean = Try(Enum.valueOf(enumClass, name)) match {
        case Success(_) => true
        case _ => false
    }
}
