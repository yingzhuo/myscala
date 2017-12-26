/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.io

import java.io.File

import scala.collection.mutable.ListBuffer

trait DirectoryWalker {

    def start: File

    def regiesterDirectoryHandler[U <: Any](handler: (File, Int) => U): DirectoryWalker

    def regiesterDirectoryPredicate(predicate: File => Boolean): DirectoryWalker

    def regiesterFileHandler[U <: Any](handler: (File, Int) => U): DirectoryWalker

    def regiesterFilePredicate(predicate: File => Boolean): DirectoryWalker

    def walk(): DirectoryWalker

}

object DirectoryWalker {

    def apply(start: String): DirectoryWalker = apply(new File(start))

    def apply(start: File): DirectoryWalker = new SimpleDirectoryWalker(start)

    def getSubDirectories(start: String): Stream[File] = getSubDirectories(new File(start))

    def getSubDirectories(start: File): Stream[File] = {
        if (start == null) return null
        if (!start.exists()) return Stream.empty
        if (!start.isDirectory) return Stream.empty

        val list = ListBuffer[File]()

        DirectoryWalker(start)
            .regiesterDirectoryHandler((dir, _) => if (!dir.equals(start)) list += dir)
            .walk()

        list.toStream
    }

    def getSubFiles(start: String): Stream[File] = getSubFiles(new File(start))

    def getSubFiles(start: File): Stream[File] = {
        if (start == null) return null
        if (!start.exists()) return Stream.empty
        if (!start.isDirectory) return Stream.empty

        val list = ListBuffer[File]()

        DirectoryWalker(start)
            .regiesterFileHandler((file, _) => if (!file.equals(start)) list += file)
            .walk()

        list.toStream
    }

    def getSubs(start: String): Stream[File] = getSubs(new File(start))

    def getSubs(start: File): Stream[File] = {
        if (start == null) return null
        if (!start.exists()) return Stream.empty
        if (!start.isDirectory) return Stream.empty

        val list = ListBuffer[File]()

        DirectoryWalker(start)
            .regiesterDirectoryHandler((dir, _) => if (!dir.equals(start)) list += dir)
            .regiesterFileHandler((file, _) => if (!file.equals(start)) list += file)
            .walk()

        list.toStream
    }

    private class SimpleDirectoryWalker(val start: File) extends DirectoryWalker {

        private[this] var directoryPredicate: File => Boolean = { _ => true }
        private[this] var directoryHandler: (File, Int) => Unit = { (_, _) => }
        private[this] var filePredicate: File => Boolean = { _ => true }
        private[this] var fileHandler: (File, Int) => Unit = { (_, _) => }

        override def regiesterDirectoryHandler[U](handler: (File, Int) => U): DirectoryWalker = {
            if (handler != null) {
                directoryHandler = { (dir, depth) =>
                    handler(dir, depth)
                }
            }
            this
        }

        override def regiesterDirectoryPredicate(predicate: (File) => Boolean): DirectoryWalker = {
            if (predicate != null) {
                this.directoryPredicate = predicate
            }
            this
        }

        override def regiesterFileHandler[U](handler: (File, Int) => U): DirectoryWalker = {
            if (handler != null) {
                fileHandler = { (file, depth) =>
                    handler(file, depth)
                }
            }
            this
        }

        override def regiesterFilePredicate(predicate: (File) => Boolean): DirectoryWalker = {
            if (predicate != null) {
                filePredicate = predicate
            }
            this
        }

        override def walk(): DirectoryWalker = {
            start match {
                case null =>
                case Files.NotExists() =>
                case file@Files.File() =>
                    if (filePredicate(file)) {
                        fileHandler(file, 1)
                    }
                case dir@Files.Directory() =>
                    doWalk(dir, 1)
            }
            this
        }

        private def doWalk(dir: File, depth: Int): Unit = {
            if (directoryPredicate(dir)) {
                directoryHandler(dir, 0)
                dir.listFilesAsStream().foreach { f =>
                    if (f.isFile && filePredicate(f)) {
                        fileHandler(f, depth)
                    }

                    if (f.isDirectory) {
                        doWalk(f, depth + 1)
                    }
                }
            }
        }
    }

}
