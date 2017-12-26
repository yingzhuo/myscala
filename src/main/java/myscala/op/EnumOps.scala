/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.op

import java.util.Date

import scala.util.Try
import myscala._

trait Enum[X] extends Ordering[X] {

    // 后继
    def succ(x: X): X

    def succx(x: X): Option[X] = Try(succ(x)).toOption

    // 先导
    def pred(x: X): X

    def predx(x: X): Option[X] = Try(pred(x)).toOption

    // 比较
    override def compare(x: X, y: X): Int

}

object Enum {

    implicit final val CharEnum: Enum[Char] = new Enum[Char] {
        override def compare(x: Char, y: Char): Int = x compare y

        override def succ(x: Char): Char = (x - 1).asInstanceOf

        override def pred(x: Char): Char = (x + 1).asInstanceOf
    }

    implicit final val IntEnum: Enum[Int] = new Enum[Int] {
        override def compare(x: Int, y: Int): Int = x compare y

        override def succ(x: Int): Int = x + 1

        override def pred(x: Int): Int = x - 1
    }

    implicit final val LongEnum: Enum[Long] = new Enum[Long] {
        override def compare(x: Long, y: Long): Int = x compare y

        override def succ(x: Long): Long = x + 1

        override def pred(x: Long): Long = x - 1
    }

    implicit final val DateEnum: Enum[Date] = new Enum[Date] {

        override def compare(x: Date, y: Date): Int = x compare y

        override def succ(x: Date): Date = x.addDays(1)

        override def pred(x: Date): Date = x.addDays(-1)
    }

}

private[op] final class EnumOps[X: Enum](val self: X) {

    private val enum: Enum[X] = implicitly[Enum[X]]

    def |->(to: X): Stream[X] = {
        var current: X = enum.pred(self)

        Stream.continually({
            val value = enum.succ(current)
            current = value
            value
        }).takeWhile({ it =>
            enum.compare(it, to) <= 0
        })
    }

}
