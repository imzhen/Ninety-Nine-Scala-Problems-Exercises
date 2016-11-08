//object P1 {
//  def lastRecursive[A](list: List[A]): A = list match {
//    case h :: Nil => h
//    case _ :: tail => lastRecursive(tail)
//    case _ => throw new NoSuchElementException
//  }
//
//  lastRecursive(List(1, 2, 3))
//}

//object P2 {
//  def penultimate[A](list: List[A]): A = list match {
//    case h :: _ :: Nil => h
//    case _ :: tail => penultimate(tail)
//    case _ => throw new NoSuchElementException
//  }
//
//  penultimate(List(1, 2, 3, 4, 5))
//}

//object P3 {
//  def nth[A](num: Int, list: List[A]): A = list match {
//    case h :: tail if num == 0 => h
//    case h :: tail => nth(num - 1, tail)
//    case _ => throw new NoSuchElementException
//  }
//
//  nth(2, List(1, 1, 2, 3, 5, 8))
//}

//object P4 {
//  def length[A](list: List[A]): Int = {
//    def lengthHelper[A](num: Int, list: List[A]): Int = (num, list) match {
//      case (value, Nil) => value
//      case (value, _ :: tail) => lengthHelper(value+1, tail)
//      case (_, _) => throw new NoSuchElementException
//    }
//    lengthHelper(0, list)
//  }
//
//  length(List(1, 1, 2, 3, 5, 8))
//}

//object P5 {
//  def reverse[A](list: List[A]): List[A] = {
//    def reverseHelper[A](list: List[A], listR: List[A]): List[A] = (list, listR) match {
//      case (Nil, r) => r
//      case (l :: tail, r) => reverseHelper(tail, l :: r)
//    }
//    reverseHelper(list, Nil)
//  }
//
//  reverse(List(1, 1, 2, 3, 5, 8))
//}

//object P7 {
//  def flatten(list: List[Any]): List[Any] = list flatMap {
//    case ls: List[_] => flatten(ls)
//    case h => List(h)
//  }
//
//  flatten(List(List(1, 1), 2, List(3, List(5, 8))))
//}

//object P8 {
//  def compress[A](list: List[A]): List[A] = {
//    def compressHelper(list: List[A], listR: List[A]): List[A] = (list, listR) match {
//      case (h :: tail, r) => compressHelper(tail.dropWhile(_ == h), h :: r)
//      case (Nil, r) => r
//    }
//    compressHelper(list, Nil).reverse
//  }
//
//  compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//}

//object P9 {
//  def pack[A](list: List[A]): List[List[A]] = {
//    def packHelper(list: List[A], listR: List[List[A]]): List[List[A]] = (list, listR) match {
//      case (h :: tail, Nil) => packHelper(tail, List(h) :: Nil)
//      case (h :: tail, r1 :: r2) => packHelper(tail, r1 match {
//        case s :: _ if s == h => (s :: r1) :: r2
//        case s :: _ => List(h) :: r1 :: r2
//      })
//      case (Nil, r) =>  r
//    }
//    packHelper(list, Nil).reverse
//  }
//
//  pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//}
