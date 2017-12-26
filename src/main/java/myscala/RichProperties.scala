/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.util.Properties

import scala.collection.JavaConverters._
import scala.collection.mutable

private[myscala] final class RichProperties(val props: Properties) extends Iterable[(String, String)] {

    require(props != null)

    def toMutableMap: mutable.Map[String, String] = props.asScala

    def toMap: Map[String, String] = toMutableMap.toMap

    override def iterator: Iterator[(String, String)] = toMap.iterator

}
