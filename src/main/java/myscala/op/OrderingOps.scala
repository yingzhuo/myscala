/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.op

private[op] final class OrderingOps[X: Ordering](self: X) {

    require(self != null)

    private val ordering: Ordering[X] = implicitly[Ordering[X]]

    def ?|?(that: X): OrderingRelation = ordering.compare(self, that) match {
        case c if c < 0 => OrderingRelation.LT
        case c if c > 0 => OrderingRelation.GT
        case _ => OrderingRelation.EQ
    }

    def <(that: X): Boolean = ordering.compare(self, that) < 0

    def >(that: X): Boolean = ordering.compare(self, that) > 0

    def <=(that: X): Boolean = ordering.compare(self, that) <= 0

    def >=(that: X): Boolean = ordering.compare(self, that) >= 0

    def ===(that: X): Boolean = ordering.compare(self, that) == 0

    def !==(that: X): Boolean = ordering.compare(self, that) != 0

    def min(that: X): X = ordering.min(self, that)

    def max(that: X): X = ordering.max(self, that)

}

sealed trait OrderingRelation extends Serializable

object OrderingRelation {
    case object EQ extends OrderingRelation
    case object LT extends OrderingRelation
    case object GT extends OrderingRelation
}
