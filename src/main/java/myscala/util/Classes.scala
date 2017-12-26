/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

class Classes {

    object Package {

        def unapplySeq(clz: Class[_]): Option[Seq[String]] = clz match {
            case null => None
            case _ => Option(clz.getPackage.getName.split(raw"\."))
        }

        def unapplySeq(`object`: scala.Any): Option[Seq[String]] = `object` match {
            case null => None
            case _ => unapplySeq(`object`.getClass)
        }
    }

}
