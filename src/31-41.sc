class S99Int(val start: Int) {
  import S99Int._
  def isPrime: Boolean = (start > 1) && (prime takeWhile (_ <= Math.sqrt(start)) forall (start % _ != 0))
  def isCoprimeTo(n: Int): Boolean = gcd(start, n) == 1
  def totient = (1 to start) count isCoprimeTo _
  def primeFactors: List[Int] = {
    def primeFactorsHelper(n: Int, ps: Stream[Int], accu: List[Int]): List[Int] = n match {
      case i if i.isPrime => i :: accu
      case i if i % ps.head == 0 => primeFactorsHelper(i / ps.head, ps, ps.head :: accu)
      case _ => primeFactorsHelper(n, ps.tail, accu)
    }
    primeFactorsHelper(start, prime, List.empty[Int]).reverse
  }
  def primeFactorsMultiplicity: Map[Int, Int] = {
    def factorCount(n: Int, dividend: Int): (Int, Int) = (n, dividend) match {
      case (i, j) if i % j != 0 => (0, i)
      case (i, j) => factorCount(i / j, j) match {case (v, w) => (v+1, w)}
    }
    def primeFactorsMultiplicityHelper(n: Int, ps: Stream[Int], accu: Map[Int, Int]): Map[Int, Int] = n match {
      case 1 => accu
      case i if i % ps.head == 0 =>
        val (count, dividend) = factorCount(i, ps.head)
        primeFactorsMultiplicityHelper(dividend, ps.tail, accu ++ Map(ps.head -> count))
      case _ => primeFactorsMultiplicityHelper(n, ps.tail, accu)
    }
    primeFactorsMultiplicityHelper(start, prime, Map.empty[Int, Int])
  }
  def goldbach: (Int, Int) = {
    prime takeWhile (_ < start) find (e => (start - e).isPrime) match {
      case None => throw new IllegalArgumentException
      case Some(p) => (p, start - p)
    }
  }
}

object S99Int {
  implicit def intToS99Int(x: Int): S99Int = new S99Int(x)
  val prime = Stream.cons(2, Stream.from(3, 2) filter (_.isPrime))
  def gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)
  def phi(m: Int) = m.primeFactorsMultiplicity map (e => (e._1 - 1) * Math.pow(e._1, e._2 - 1)) product
  def listPrimesInRange(r: Range): List[Int] = prime takeWhile (_ <= r.last) dropWhile (_ >= r.start) toList
  def printGoldbachListLimited(r: Range, limit: Int) = {
    r filter (e => e > 2 && e % 2 == 0) map (e => (e, e.goldbach)) collect {
      case (m, (k, v)) if k >= limit => (m, k, v)} foreach {e => println(e._1 + " = " + e._2 + " + " + e._3)}
  }
}

import S99Int._

7.isPrime
S99Int.gcd(63, 36)
35.isCoprimeTo(65)
120.totient
315.primeFactors
315.primeFactorsMultiplicity
phi(120)
listPrimesInRange(7 to 31)
28.goldbach
printGoldbachListLimited(1 to 2000, 50)