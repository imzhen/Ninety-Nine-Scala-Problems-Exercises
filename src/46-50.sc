//object S99Logic {
//  def and(a: Boolean, b: Boolean): Boolean = (a, b) match {
//    case (true, true) => true
//    case _ => false
//  }
//  def not(a: Boolean): Boolean = a match {
//    case true => false
//    case _ => true
//  }
//  def or(a: Boolean, b: Boolean): Boolean = (a, b) match {
//    case (_, true) => true
//    case (true, _) => true
//    case _ => false
//  }
//  def equ(a: Boolean, b: Boolean): Boolean = or(and(a, b), and(not(a), not(b)))
//  def xor(a: Boolean, b: Boolean): Boolean = not(equ(a, b))
//  def nor(a: Boolean, b: Boolean): Boolean = not(or(a, b))
//  def nand(a: Boolean, b: Boolean): Boolean = not(and(a, b))
//  def impl(a: Boolean, b: Boolean): Boolean = or(not(a), b)
//
//  def table2(f: (Boolean, Boolean) => Boolean): Unit = {
//    println("A     B     result")
//    List(true, false) foreach {e1 => List(true, false) foreach {e2 =>
//      printf("%-5s %-5s %-5s\n", e1, e2, f(e1, e2))
//    }}
//  }
//}
//
//import S99Logic._
//table2((a: Boolean, b: Boolean) => and(a, or(a, b)))

//object P49 {
//  def gray(n: Int): List[String] = {
//    def grayHelper(n: Int, accu: List[List[String]]): List[List[String]] = n match {
//      case 0 => accu
//      case _ => grayHelper(n-1, accu flatMap {e1 => List("0", "1") map {e2 => e2 :: e1}})
//    }
//    grayHelper(3, List(List())) map {e => e.reduce(_.toString + _.toString)}
//  }
//
//  gray(3)
//}

object P50 {
  trait Tree[A] {
    val count: Int
    def toCode: List[(A, String)] = toCodePrefixed("")
    def toCodePrefixed(prefix: String): List[(A, String)]
  }

  case class InternalNode[A](left: Tree[A], right: Tree[A]) extends Tree[A]{
    val count = left.count + right.count
    def toCodePrefixed(prefix: String): List[(A, String)] =
      left.toCodePrefixed(prefix + "0") ::: right.toCodePrefixed(prefix + "1")
  }

  case class LeafNode[A](element: A, count: Int) extends Tree[A] {
    def toCodePrefixed(prefix: String): List[(A, String)] = List((element, prefix))
  }

  def huffman[A](list: List[(A, Int)]): List[(A, String)] = {
    import collection.immutable.Queue
    def dequeueSmallest(q1: Queue[Tree[A]], q2: Queue[Tree[A]]): (Tree[A], Queue[Tree[A]], Queue[Tree[A]]) = {
      if (q2.isEmpty) (q1.front, q1.dequeue._2, q2)
      else if (q1.isEmpty) (q2.front, q1, q2.dequeue._2)
      else (q1.front, q1.dequeue._2, q2)
    }
    def huffmanHelper(q1: Queue[Tree[A]], q2: Queue[Tree[A]]): List[(A, String)] = {
      if (q1.length + q2.length == 1) (if (q1.isEmpty) q2.front else q1.front).toCode
      else {
        val (v1, q3, q4) = dequeueSmallest(q1, q2)
        val (v2, q5, q6) = dequeueSmallest(q3, q4)
        huffmanHelper(q5, q6.enqueue(InternalNode(v1, v2)))
      }
    }
    huffmanHelper(Queue.empty.enqueue(list sortWith (_._2 < _._2) map {e => LeafNode(e._1, e._2)}), Queue.empty)
  }

  huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)))
}
