/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.io

import java.io._
import java.nio.charset.Charset

import scala.io.{Codec, Source}
import myscala.Charsets
import myscala.util.Systems

import myscala._

private[io] final class RichFile(val file: File) {

    Validate.notNull(file)

    def openOutputStream(append: Boolean = false): FileOutputStream = file match {
        case Files.NotExists() => throw new FileNotFoundException(s"File $file does not exist")
        case Files.Directory() => throw new IOException(s"File $file exists but is a directory")
        case Files.CannotWrite() => throw new IOException(s"File $file cannot be write")
        case _ => new FileOutputStream(file, append)
    }

    def openReader(charset: Charset): Reader = file.openInputStream().toReader(charset)

    def touchIfNotExists(): File = file match {
        case Files.NotExists() => file.touch()
        case _ => file
    }

    def touch(): File = file match {
        case ff@Files.NotExists() =>
            val out = new FileOutputStream(file)
            out.closeQuietly()
            ff
        case _ =>
            val success = file.setLastModified(System.currentTimeMillis())
            if (!success) throw new IOException(s"Unable to set the last modification time for $file") else file
    }

    def toString(charset: Charset = Charsets.UTF_8): String = getLines(charset).mkString("\n")

    def getLines(charset: Charset = Charsets.UTF_8): Iterator[String] = {
        val is: InputStream = file.openInputStream()

        try {
            Source.fromFile(file)(Codec(charset)).getLines()
        }
        finally {
            is.closeQuietly()
        }
    }

    def openInputStream(): FileInputStream = file match {
        case Files.NotExists() => throw new FileNotFoundException(s"File $file does not exist")
        case Files.Directory() => throw new IOException(s"File $file exists but is a directory")
        case Files.CannotRead() => throw new IOException(s"File $file cannot be read")
        case _ => new FileInputStream(file)
    }

    def isSymlink: Boolean = {
        if (Systems.isWindows) return false
        var fileInCanonicalDir: File = null
        if (file.getParent == null) {
            fileInCanonicalDir = file
        }
        else {
            val canonicalDir: File = file.getParentFile.getCanonicalFile
            fileInCanonicalDir = new File(canonicalDir, file.getName)
        }
        if (fileInCanonicalDir.getCanonicalFile == fileInCanonicalDir.getAbsoluteFile) isBrokenSymlink(file) else true
    }

    private def isBrokenSymlink(file: File): Boolean = {
        if (file.exists) return false
        val canon = file.getCanonicalFile
        val parentDir = canon.getParentFile
        if (parentDir == null || !parentDir.exists) return false
        val fileInDir = parentDir.listFiles(new FileFilter() {
            def accept(aFile: File): Boolean = aFile == canon
        })
        fileInDir != null && fileInDir.nonEmpty
    }

    def cleanDirectory(): Boolean = file match {
        case null => false
        case Files.Directory() =>
            file.listFiles().foreach { ff =>
                if (ff.isDirectory) {
                    ff.cleanDirectory()
                    ff.delete()
                }

                if (ff.isFile) {
                    ff.delete()
                }
            }
            true
        case _ => false
    }

    def deleteDirectory(): Boolean = file match {
        case null => false
        case ff@Files.Directory() =>
            ff.cleanDirectory()
            ff.delete()
        case _ => false
    }

    def deleteForce(): Boolean = file match {
        case null => false
        case ff@Files.File() => ff.delete()
        case ff@Files.Directory() => ff.deleteDirectory()
        case _ => false
    }

    def listFilesAsStream(p: File => Boolean = null): Stream[File] = {
        val predicateToUse: File => Boolean = if (p != null) p else { _ => true }
        file.list().map(new File(file, _)).filter(predicateToUse).toStream
    }

}
