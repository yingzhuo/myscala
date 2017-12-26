/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.math

import scala.annotation.tailrec

object Maths {

    def negative[T](t: T)(implicit numeric: Numeric[T]): T = numeric.negate(t)

    def max[T](ts: T*)(implicit f: T => Ordered[T]): T = {
        def doMax(t1: T, t2: T): T = if (t1 >= t2) t1 else t2

        if (ts == null || ts.isEmpty) throw new IllegalArgumentException("Invalid parameters.")
        if (ts.size == 1) return ts.head
        ts.reduceLeft(doMax)
    }

    def min[T](ts: T*)(implicit f: T => Ordered[T]): T = {
        def doMin(t1: T, t2: T): T = if (t1 <= t2) t1 else t2

        if (ts == null || ts.isEmpty) throw new IllegalArgumentException("Invalid parameters.")
        if (ts.size == 1) return ts.head
        ts.reduceLeft(doMin)
    }

    def gcd(x: Short, y: Short): Short = gcd(x.toInt, y.toInt).toShort

    @tailrec def gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)

    @tailrec def gcd(x: Long, y: Long): Long = if (y == 0) x else gcd(y, x % y)

    @tailrec def gcd(x: BigInt, y: BigInt): BigInt = if (y == 0) x else gcd(y, x % y)
}
