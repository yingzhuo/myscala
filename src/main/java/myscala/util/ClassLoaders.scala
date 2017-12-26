/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import scala.util.{Success, Try}
import myscala._

object ClassLoaders {

    private class ClassLoaders$_$$$$ private[this]

    @volatile final lazy val DefaultClassLoaderOption: Option[ClassLoader] = Option(DefaultClassLoader)

    @volatile final lazy val DefaultClassLoader: ClassLoader = {
        val tried = Try(Thread.currentThread.getContextClassLoader) || Try(classOf[ClassLoaders$_$$$$].getClassLoader) || Try(ClassLoader.getSystemClassLoader)
        tried match {
            case Success(x) => x
            case _ => null
        }
    }

}
