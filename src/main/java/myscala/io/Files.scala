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

object Files {

    object File {
        def unapply(f: File): Boolean = f.isFile
    }

    object Directory {
        def unapply(f: File): Boolean = f.isDirectory
    }

    object EmptyDirectory {
        def unapply(f: File): Boolean = f.isDirectory && f.list().isEmpty
    }

    object NonEmptyDirectory {
        def unapply(f: File): Boolean = f.isDirectory && !f.list().isEmpty
    }

    object Exists {
        def unapply(f: File): Boolean = f.exists()
    }

    object NotExists {
        def unapply(f: File): Boolean = !f.exists()
    }

    object Hidden {
        def unapply(f: File): Boolean = f.isHidden
    }

    object NotHidden {
        def unapply(f: File): Boolean = !f.isHidden
    }

    object CanRead {
        def unapply(f: File): Boolean = f.canRead
    }

    object CannotRead {
        def unapply(f: File): Boolean = !f.canRead
    }

    object CanWrite {
        def unapply(f: File): Boolean = f.canWrite
    }

    object CannotWrite {
        def unapply(f: File): Boolean = !f.canWrite
    }

    object Absolute {
        def unapply(f: File): Boolean = f.isAbsolute
    }

    object NonAbsolute {
        def unapply(f: File): Boolean = !f.isAbsolute
    }

    object Symlink {
        def unapply(f: File): Boolean = f.isSymlink
    }

    object NonSymlink {
        def unapply(f: File): Boolean = !f.isSymlink
    }

}
