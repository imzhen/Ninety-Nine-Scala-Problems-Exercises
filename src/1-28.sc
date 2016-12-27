import java.util.NoSuchElementException

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

//object P10 {
//  def encode[A](list0: List[A]): List[(Int, A)] = {
//    def pack[A](list: List[A]): List[List[A]] = {
//      def packHelper(list: List[A], listR: List[List[A]]): List[List[A]] = (list, listR) match {
//        case (h :: tail, Nil) => packHelper(tail, List(h) :: Nil)
//        case (h :: tail, r1 :: r2) => packHelper(tail, r1 match {
//          case s :: _ if s == h => (s :: r1) :: r2
//          case s :: _ => List(h) :: r1 :: r2
//        })
//        case (Nil, r) =>  r
//      }
//      packHelper(list, Nil).reverse
//    }
//
//    val packResult = pack(list0)
//    packResult.map(l => (l.length, l.head))
//  }
//
//  encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//}

//object P11 {
//  def encodeModified[A](list0: List[A]): List[Any] = {
//    def pack[A](list: List[A]): List[List[A]] = {
//      def packHelper(list: List[A], listR: List[List[A]]): List[List[A]] = (list, listR) match {
//        case (h :: tail, Nil) => packHelper(tail, List(h) :: Nil)
//        case (h :: tail, r1 :: r2) => packHelper(tail, r1 match {
//          case s :: _ if s == h => (s :: r1) :: r2
//          case s :: _ => List(h) :: r1 :: r2
//        })
//        case (Nil, r) =>  r
//      }
//      packHelper(list, Nil).reverse
//    }
//
//    val packResult = pack(list0)
//    packResult map {
//      case l if l.length == 1 => l.head
//      case l => (l.length, l.head)
//    }
//  }
//
//  encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//}

//object P12 {
//  def decode[A](list: List[(Int, A)]): List[A] = {
//    list flatMap { e => List.fill(e._1)(e._2) }
//  }
//
//  decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
//}

//object P13 {
//  def encodeDirect[A](list: List[A]): List[(Int, A)] = {
//    def encodeDirectHelper(listL: List[A], listR: List[(Int, A)]): List[(Int, A)] = listL match {
//      case h :: tail => {
//        val (lL, lR) = listL span (_ == h)
//        encodeDirectHelper(lR, (lL.length, lL.head) :: listR)
//      }
//      case Nil => listR
//    }
//
//    encodeDirectHelper(list, Nil).reverse
//  }
//
//  encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
//}

//object P14 {
//  def duplicate[A](list: List[A]): List[A] = {
//    list flatMap {a => List.fill(2)(a)}
//  }
//
//  duplicate(List('a, 'b, 'c, 'c, 'd))
//}

//object P15 {
//  def duplicateN[A](time: Int, list: List[A]): List[A] = {
//    list flatMap {a => List.fill(time)(a)}
//  }
//
//  duplicateN(3, List('a, 'b, 'c, 'c, 'd))
//}

//object P16 {
//  def drop[A](n: Int, list: List[A]): List[A] = {
//    var count = 0
//    list.foldLeft(List.empty[A]){
//      (b, a) => {
//        count += 1
//        count match {
//          case i if i % n == 0 => b
//          case _ => a :: b
//        }
//      }}.reverse
//  }
//
//  drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
//
//  def dropFunctional[A](n: Int, list: List[A]): List[A] = {
//    list.zipWithIndex collect {
//      case (v, w) if w % n != n - 1 => v
//    }
//  }
//
//  dropFunctional(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
//}

//object P17 {
//  def split[A](n: Int, list: List[A]): (List[A], List[A]) = {
//    (list.take(n), list.drop(n))
//  }
//
//  split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
//}

//object P18 {
//  def slice[A](m: Int, n: Int, list: List[A]): List[A] = {
//    list.zipWithIndex collect {
//      case (v, w) if w >= m && w < n => v
//    }
//  }
//
//  slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
//}

//object P19 {
//  def rotate[A](n: Int, list: List[A]): List[A] = {
//    val m = if (n > 0) n else list.length + n
//    list.drop(m) ::: list.take(m)
//  }
//
//  rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
//  rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
//}

//object P20 {
//  def removeAt[A](n: Int, list: List[A]): (List[A], A) = list.splitAt(n) match {
//    case (v, Nil) => throw new NoSuchElementException
//    case (v, w :: tail) => (v ::: tail, w)
//  }
//
//  removeAt(1, List('a, 'b, 'c, 'd))
//}

//object P21 {
//  def insertAt[A](element: A, position: Int, list: List[A]): List[A] = {
//    list.splitAt(position) match {
//      case (v, w) => v ::: (element :: w)
//    }
//  }
//
//  insertAt('new, 1, List('a, 'b, 'c, 'd))
//}

//object P22 {
//  def range(m: Int, n: Int): List[Int] = {
//    def rangeHelper(v: Int, w: Int, list: List[Int]): List[Int] = v match {
//      case i if v <= w => rangeHelper(i + 1, w, i :: list)
//      case _ => list
//    }
//    rangeHelper(m, n, List.empty[Int]).reverse
//  }
//
//  range(4, 8)
//
//}

//object P23 {
//  def removeAt[A](n: Int, list: List[A]): (List[A], A) = list.splitAt(n) match {
//    case (v, Nil) => throw new NoSuchElementException
//    case (v, w :: tail) => (v ::: tail, w)
//  }
//
//  def randomSelect[A](n: Int, list: List[A]): List[A] = {
//    def randomSelectHelper(k: Int, list: List[A], accumulator: List[A]): List[A] = k match {
//      case 0 => accumulator
//      case _ => {
//        val r = util.Random
//        val (list_new, m) = removeAt(r.nextInt(list.length), list)
//        randomSelectHelper(k - 1, list_new, m :: accumulator)
//      }
//    }
//
//    randomSelectHelper(n, list, List.empty[A])
//  }
//
//  randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h))
//}

//object P24 {
//  def removeAt[A](n: Int, list: List[A]): (List[A], A) = list.splitAt(n) match {
//    case (v, Nil) => throw new NoSuchElementException
//    case (v, w :: tail) => (v ::: tail, w)
//  }
//
//  def randomSelect[A](n: Int, list: List[A]): List[A] = {
//    def randomSelectHelper(k: Int, list: List[A], accumulator: List[A]): List[A] = k match {
//      case 0 => accumulator
//      case _ => {
//        val r = util.Random
//        val (list_new, m) = removeAt(r.nextInt(list.length), list)
//        randomSelectHelper(k - 1, list_new, m :: accumulator)
//      }
//    }
//
//    randomSelectHelper(n, list, List.empty[A])
//  }
//
//  def lotto(m: Int, n: Int): List[Int] = {
//    randomSelect(m, List.range(1, n+1))
//  }
//
//  lotto(6, 49)
//}

//object P25 {
//  def removeAt[A](n: Int, list: List[A]): (List[A], A) = list.splitAt(n) match {
//    case (v, Nil) => throw new NoSuchElementException
//    case (v, w :: tail) => (v ::: tail, w)
//  }
//
//  def randomSelect[A](n: Int, list: List[A]): List[A] = {
//    def randomSelectHelper(k: Int, list: List[A], accumulator: List[A]): List[A] = k match {
//      case 0 => accumulator
//      case _ => {
//        val r = util.Random
//        val (list_new, m) = removeAt(r.nextInt(list.length), list)
//        randomSelectHelper(k - 1, list_new, m :: accumulator)
//      }
//    }
//
//    randomSelectHelper(n, list, List.empty[A])
//  }
//
//  def randomPermute[A](list: List[A]): List[A] = randomSelect(list.length, list)
//
//  randomPermute(List('a, 'b, 'c, 'd, 'e, 'f))
//}

//object P26 {
//  def combinations[A](n: Int, list: List[A]): List[List[A]] = n match {
//    case 0 => List(Nil)
//    case _ => flatMapSublist(list) {ls =>
//      combinations(n-1, ls.tail) map {ls.head :: _}
//    }
//  }
//
//  def flatMapSublist[A,B](list: List[A])(f: List[A] => List[B]): List[B] = list match {
//    case Nil => Nil
//    case sublist @ (_ :: tail) => f(sublist) ::: flatMapSublist(tail)(f)
//  }
//
//  combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
//}

//object P27 {
//  def combinations[A](n: Int, list: List[A]): List[List[A]] = n match {
//    case 0 => List(Nil)
//    case _ => flatMapSublist(list) {ls =>
//      combinations(n-1, ls.tail) map {ls.head :: _}
//    }
//  }
//
//  def flatMapSublist[A,B](list: List[A])(f: List[A] => List[B]): List[B] = list match {
//    case Nil => Nil
//    case sublist @ (_ :: tail) => f(sublist) ::: flatMapSublist(tail)(f)
//  }
//
//  def group[A](ns: List[Int], list: List[A]): List[List[List[A]]] = ns match {
//    case Nil => List(Nil)
//    case n :: m => combinations(n, list) flatMap {
//      k => group(m, list diff k) map {k :: _}
//    }
//  }
//
//  group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
//}

//object P28 {
//  def lsort[A](list: List[List[A]]): List[List[A]] = {
//    list sortWith (_.length < _.length)
//  }
//
//  lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
//
//  def encode[A](list0: List[A]): List[(Int, A)] = {
//    def pack(list: List[A]): List[List[A]] = {
//      def packHelper(list: List[A], listR: List[List[A]]): List[List[A]] = (list, listR) match {
//        case (h :: tail, Nil) => packHelper(tail, List(h) :: Nil)
//        case (h :: tail, r1 :: r2) => packHelper(tail, r1 match {
//          case s :: _ if s == h => (s :: r1) :: r2
//          case s :: _ => List(h) :: r1 :: r2
//        })
//        case (Nil, r) =>  r
//      }
//      packHelper(list, Nil).reverse
//    }
//
//    val packResult = pack(list0)
//    packResult.map(l => (l.length, l.head))
//  }
//
//  def lsortFreq[A](list: List[List[A]]): List[List[A]] = {
//    val freq = Map(encode(list map (_.length) sortWith (_ < _)) map (_.swap): _*)
//    list sortWith ((e1, e2) => freq(e1.length) < freq(e2.length))
//  }
//
//  lsortFreq(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
//}
