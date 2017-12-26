/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.collection.mutable

object ListStack {

    def apply[E](es: E*): ListStack[E] = {
        val stack = new ListStack[E](Nil)
        es.foreach(stack.push)
        stack
    }

}

class ListStack[E] private(list: List[E]) extends Stack[E] {

    private var _list: List[E] = list

    override def push(e: E): Unit = {
        _list = e :: _list
    }

    override def pop(): E = {
        if (isEmpty) throw new NoSuchElementException

        val head = _list.head
        _list = _list.drop(1)
        head
    }

    override def peek(): E = if (isEmpty) throw new NoSuchElementException else _list.head

    override def isEmpty: Boolean = _list.isEmpty

    override def size: Int = _list.size

    override def clean(): Unit = _list = Nil

    override def map[T](f: E => T): Stack[T] = Stack(_list.map(f): _*)

    override def collect[T](pf: PartialFunction[E, T]): Stack[T] = Stack(_list.collect(pf): _*)

    override def forall(p: (E) => Boolean): Boolean = _list.forall(p)

    override def exists(p: (E) => Boolean): Boolean = _list.exists(p)

}
