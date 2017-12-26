/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import scala.util.Properties

import myscala._

object Systems {

    @volatile lazy val isWindows: Boolean = Properties.isWin
    @volatile lazy val isWin: Boolean = Properties.isWin
    @volatile lazy val isMac: Boolean = Properties.isMac
    @volatile lazy val isLinux: Boolean = Properties.isLinux

    /* --------------------------------------------------------------------------------------- */

    @volatile lazy val javaClassPath: String = Properties.propOrEmpty("java.class.path")
    @volatile lazy val javaHome: String = Properties.propOrEmpty("java.home")
    @volatile lazy val javaVendor: String = Properties.propOrEmpty("java.vendor")
    @volatile lazy val javaVersion: String = Properties.propOrEmpty("java.version")
    @volatile lazy val javaVmInfo: String = Properties.propOrEmpty("java.vm.info")
    @volatile lazy val javaVmName: String = Properties.propOrEmpty("java.vm.name")
    @volatile lazy val javaVmVendor: String = Properties.propOrEmpty("java.vm.vendor")
    @volatile lazy val javaVmVersion: String = Properties.propOrEmpty("java.vm.version")
    @volatile lazy val javaSpecVersion: String = Properties.propOrEmpty("java.specification.version")
    @volatile lazy val javaSpecVendor: String = Properties.propOrEmpty("java.specification.vendor")
    @volatile lazy val javaSpecName: String = Properties.propOrEmpty("java.specification.name")
    @volatile lazy val osName: String = Properties.propOrEmpty("os.name")
    @volatile lazy val scalaHome: String = Properties.propOrEmpty("scala.home")
    @volatile lazy val tmpDir: String = Properties.propOrEmpty("java.io.tmpdir")
    @volatile lazy val userDir: String = Properties.propOrEmpty("user.dir")
    @volatile lazy val userHome: String = Properties.propOrEmpty("user.home")
    @volatile lazy val userName: String = Properties.propOrEmpty("user.name")

    /* --------------------------------------------------------------------------------------- */

    // java
    @volatile lazy val getJavaVersion: String = Properties.javaVersion
    @volatile lazy val isJava5: Boolean = getJavaVersion.startsWith("1.5.")
    @volatile lazy val isJava6: Boolean = getJavaVersion.startsWith("1.6.")
    @volatile lazy val isJava7: Boolean = getJavaVersion.startsWith("1.7.")
    @volatile lazy val isJava8: Boolean = getJavaVersion.startsWith("1.8.")
    @volatile lazy val isJava9: Boolean = getJavaVersion.startsWith("1.9.")
    @volatile lazy val isAtLeastJava5: Boolean = isJava5 || isAtLeastJava6
    @volatile lazy val isAtLeastJava6: Boolean = isJava6 || isAtLeastJava7
    @volatile lazy val isAtLeastJava7: Boolean = isJava7 || isAtLeastJava8
    @volatile lazy val isAtLeastJava8: Boolean = isJava8 || isAtLeastJava9
    @volatile lazy val isAtLeastJava9: Boolean = isJava9
    // scala
    @volatile lazy val scalaVersion: String = Properties.scalaPropOrElse("version.number", "unknown")
    @volatile lazy val isScala2: Boolean = scalaVersion.startsWith("2.")
    @volatile lazy val isScala2_12: Boolean = scalaVersion.startsWith("2.12.")
    @volatile lazy val isScala2_11: Boolean = scalaVersion.startsWith("2.11.")
    @volatile lazy val isScala2_10: Boolean = scalaVersion.startsWith("2.10.")
    @volatile lazy val isScala2_9: Boolean = scalaVersion.startsWith("2.9.")
    @volatile lazy val isScala2_8: Boolean = scalaVersion.startsWith("2.8.")
    @volatile lazy val isAtLeastScala2_12: Boolean = isScala2_12 || isAtLeastScala2_11
    @volatile lazy val isAtLeastScala2_11: Boolean = isScala2_11 || isAtLeastScala2_10
    @volatile lazy val isAtLeastScala2_10: Boolean = isScala2_10 || isAtLeastScala2_9
    @volatile lazy val isAtLeastScala2_9: Boolean = isScala2_9 || isAtLeastScala2_8
    @volatile lazy val isAtLeastScala2_8: Boolean = isScala2_8

    object JavaVersion {
        def unapply(version: String): Option[(String, String)] = version match {
            case null => None
            case x: String if x.isNotBlank =>
                val parts = x.split("_")
                if (parts.length == 2) Option((parts(0), parts(1))) else None
            case _ => None
        }
    }

    object ScalaVersion {
        def unapply(version: String): Option[(String, String, String)] = version match {
            case null => None
            case x: String if x.isNotBlank =>
                val parts = x.split("\\.")
                if (parts.length == 3) Option((parts(0), parts(1), parts(2))) else None
            case _ => None
        }
    }

}
