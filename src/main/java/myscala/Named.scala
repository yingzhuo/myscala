/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

trait Named[X] extends Ordered[Named[X]] {

    def name: String

    def value: X

    def toTuple: (String, X) = (name, value)

}

object Named {

    def apply[X](_name: String, _x: X): Named[X] = {
        require(_name != null)
        require(_x != null)

        new SimpleNamed[X](_name, _x)
    }

    def unapply[X](arg: Named[X]): Option[(String, X)] = Some((arg.name, arg.value))

    private class SimpleNamed[X](val name: String, val value: X) extends Named[X] {
        override def compare(that: Named[X]): Int = this.name compareTo that.name
    }

}
