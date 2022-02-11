package fpinscala.answers.ch02

// A comment!
/* Another comment */

/** A documentation comment */
object GettingStarted {
  def abs(n: Int): Int = if (n < 0) -n else n

  def formatAbs(x: Int): String = {
    s"The absolute value of $x is ${abs(x)}"
  }

  def factorial(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int = {
      if (n <= 0) acc
      else go(n - 1, n * acc)
    }

    go(n, 1)
  }

  def fib1(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, cur: Int): Int = {
      if (n == 0) prev
      else loop(n - 1, cur, cur + prev)
    }

    loop(n, 0, 1)
  }

  def fib2(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, f: (Int, Int)): Int = {
      if (n == 0) f._1
      else loop(n - 1, (f._2, f._1 + f._2))
    }

    loop(n, (0, 1))
  }

  def formatResult(name: String, n: Int, f: Int => Int): String = {
    s"Ths $name value of $n is ${f(n)}"
  }

  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
    println(formatResult("absolute", -43, abs))
    println(formatResult("factorial", 5, factorial))
  }

  def findFirst1(ss: Array[String], key: String): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = {
      if (n >= ss.length) -1
      else if (ss(n) == key) n
      else loop(n + 1)
    }

    loop(0)
  }

  def findFirst2[A](as: Array[A])(p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = {
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)
    }

    loop(0)
  }

  def isSorted[A](as: Array[A])(ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean = {
      if (n >= as.length - 1) true
      else if (!ordered(as(n), as(n + 1))) false
      else loop(n + 1)
    }

    loop(0)
  }

  // 2.6
  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

  def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a: A, b: B) => f(a)(b)

  def compose[A, B, C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))
}