/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.util

import scala.collection.mutable
import myscala._

object Keywords {

    @volatile private lazy val JAVA_KEYWORDS: Set[String] = {
        val keywords = mutable.TreeSet[String]()
        keywords += "abstract"
        keywords += "continue"
        keywords += "for"
        keywords += "new"
        keywords += "switch"
        keywords += "assert"
        keywords += "default"
        keywords += "if"
        keywords += "package"
        keywords += "synchronized"
        keywords += "boolean"
        keywords += "do"
        keywords += "goto"
        keywords += "private"
        keywords += "this"
        keywords += "break"
        keywords += "double"
        keywords += "implements"
        keywords += "protected"
        keywords += "throw"
        keywords += "byte"
        keywords += "else"
        keywords += "import"
        keywords += "public"
        keywords += "throws"
        keywords += "case"
        keywords += "enum"
        keywords += "instanceof"
        keywords += "return"
        keywords += "transient"
        keywords += "catch"
        keywords += "extends"
        keywords += "int"
        keywords += "short"
        keywords += "try"
        keywords += "char"
        keywords += "final"
        keywords += "interface"
        keywords += "static"
        keywords += "void"
        keywords += "class"
        keywords += "finally"
        keywords += "long"
        keywords += "strictfp"
        keywords += "volatile"
        keywords += "const"
        keywords += "float"
        keywords += "native"
        keywords += "super"
        keywords += "while"
        keywords += "null"
        keywords += "true"
        keywords += "false"
        keywords.toSet
    }

    @volatile private lazy val SCALA_KEYWORDS: Set[String] = {
        val keywords = mutable.TreeSet[String]()
        keywords += "package"
        keywords += "import"
        keywords += "class"
        keywords += "object"
        keywords += "trait"
        keywords += "extends"
        keywords += "with"
        keywords += "type"
        keywords += "forSome"
        keywords += "private"
        keywords += "protected"
        keywords += "abstract"
        keywords += "sealed"
        keywords += "final"
        keywords += "implicit"
        keywords += "lazy"
        keywords += "override"
        keywords += "try"
        keywords += "catch"
        keywords += "finally"
        keywords += "throw"
        keywords += "if"
        keywords += "else"
        keywords += "match"
        keywords += "case"
        keywords += "do"
        keywords += "while"
        keywords += "for"
        keywords += "return"
        keywords += "yield"
        keywords += "def"
        keywords += "val"
        keywords += "var"
        keywords += "this"
        keywords += "super"
        keywords += "new"
        keywords += "true"
        keywords += "false"
        keywords += "null"
        keywords.toSet
    }

    def isJavaOrScalaKeyword(keyword: String): Boolean = isJavaKeyword(keyword) || isScalaKeyword(keyword)

    def isJavaKeyword(keyword: String): Boolean = keyword match {
        case x if x.isNullOrBlank => false
        case _ => JAVA_KEYWORDS.contains(keyword)
    }

    def isScalaKeyword(keyword: String): Boolean = keyword match {
        case x if x.isNullOrBlank => false
        case _ => SCALA_KEYWORDS.contains(keyword)
    }

}
