package fpinscala.answers.ch03

/**
 * https://github.com/fpinscala/fpinscala/blob/second-edition/src/main/scala/fpinscala/answers/datastructures/List.scala
 * https://github.com/facaiy/book_notes/blob/master/Manning_Functional_Programming_in_Scala/src/main/scala/io/github/facaiy/fp/scala/c3/List.scala
 */


/**
 * sealed 关键字主要有2个作用：
 *
 * - 其修饰的trait，class只能在当前文件里面被继承
 * - 用sealed修饰这样做的目的是告诉scala编译器在检查模式匹配的时候，让scala知道这些case的所有情况，
 * scala就能够在编译的时候进行检查，看你写的代码是否有没有漏掉什么没case到，减少编程的错误。
 */
sealed trait List[+A] // List data type, parameteried on a type, A.

/**
 * case class 和 case object 区别：
 * 类中有参和无参，当类有参数的时候，用case class ，当类没有参数的时候那么用case object。
 */
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list

/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  // `List` companion object. Contains functions for creating and working with lists.
  def apply[Int](as: Int*): List[Int] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // A function that uses pattern matching to add up a list of integers
  def sum(ints: List[Int]): Int = ints match {
    // The sum of the empty list is 0.
    case Nil => 0
    // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0, _) => 0
    case Cons(x, xs) => x * product(xs)
  }

  // EXERCISE 3.2
  /*
  Although we could return `Nil` when the input list is empty, we choose to throw an exception instead. This is
  a somewhat subjective choice. In our experience, taking the tail of an empty list is often a bug, and silently
  returning a value just means this bug will be discovered later, further from the place where it was introduced.
  It's generally good practice when pattern matching to use `_` for any variables you don't intend to use on the
  right hand side of a pattern. This makes it clear the value isn't relevant.
  */
  def tail[A](list: List[A]): List[A] = list match {
    case Nil => throw new IndexOutOfBoundsException()
    case Cons(_, xs) => xs
  }

  // EXERCISE 3.3
  /*
  If a function body consists solely of a match expression, we'll often put the match on the same line as the
  function signature, rather than introducing another level of nesting.
  */
  def setHead[A](list: List[A], head: A): List[A] = list match {
    case Nil => Cons(head, Nil)
    case Cons(x, xs) => Cons(head, xs)
  }

  // EXERCISE 3.4
  /*
 Again, it's somewhat subjective whether to throw an exception when asked to drop more elements than the list
 contains. The usual default for `drop` is not to throw an exception, since it's typically used in cases where this
 is not indicative of a programming error. If you pay attention to how you use `drop`, it's often in cases where the
 length of the input list is unknown, and the number of elements to be dropped is being computed from something else.
 If `drop` threw an exception, we'd have to first compute or check the length and only drop up to that many elements.
 */
  def drop[A](list: List[A], n: Int): List[A] = list match {
    case Nil => Nil
    case Cons(x, xs) =>
      if (n <= 0) Cons(x, xs)
      else drop(xs, n - 1)
  }

  // EXERCISE 3.5
  /*
  Somewhat overkill, but to illustrate the feature we're using a _pattern guard_, to only match a `Cons` whose head
  satisfies our predicate, `f`. The syntax is to add `if <cond>` after the pattern, before the `=>`, where `<cond>` can
  use any of the variables introduced by the pattern.
  */
  def dropWhile[A](list: List[A])(f: A => Boolean): List[A] = list match {
    case Cons(x, xs) if (f(x)) => dropWhile(xs)(f)
    case _ => list
  }

  def dropWhile2[A](list: List[A])(f: A => Boolean): List[A] = list match {
    case Cons(x, xs) => if (f(x)) dropWhile2(xs)(f) else Cons(x, dropWhile2(xs)(f))
    case _ => list
  }

  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(x, xs) => Cons(x, append(xs, a2))
  }

  // EXERCISE 3.6
  /*
  Note that we're copying the entire list up until the last element. Besides being inefficient, the natural recursive
  solution will use a stack frame for each element of the list, which can lead to stack overflows for
  large lists (can you see why?). With lists, it's common to use a temporary, mutable buffer internal to the
  function (with lazy lists or streams, which we discuss in chapter 5, we don't normally do this). So long as the
  buffer is allocated internal to the function, the mutation is not observable and RT is preserved.
  Another common convention is to accumulate the output list in reverse order, then reverse it at the end, which
  doesn't require even local mutation. We'll write a reverse function later in this chapter.
  */
  def init[A](list: List[A]): List[A] = list match {
    case Nil => throw new IndexOutOfBoundsException()
    case Cons(_, Nil) => Nil
    case Cons(x, xs) => Cons(x, init(xs))
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sum2(ns: List[Int]): Int = foldRight(ns, 0)(_ + _)

  // EXERCISE 3.7
  def product2(ns: List[Double]): Double = foldRight(ns, 1.0)(_ * _)

  // EXERCISE 3.9
  def length[A](list: List[A]): Int =
    foldRight(list, 0)((_, acc) => acc + 1)

  // EXERCISE 3.10
  @annotation.tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  // EXERCISE 3.11
  def sum3(list: List[Int]) = foldLeft(list, 0)(_ + _)

  def product3(list: List[Double]) = foldLeft(list, 1.0)(_ * _)

  // EXERCISE 3.12
  def reverse[A](list: List[A]): List[A] =
    foldLeft(list, List[A]())((acc, h) => Cons(h, acc))


  // EXERCISE 3.13
  /*
 The implementation of `foldRight` in terms of `reverse` and `foldLeft` is a common trick for avoiding stack overflows
 when implementing a strict `foldRight` function as we've done in this chapter. (We'll revisit this in a later chapter,
 when we discuss laziness).
 The other implementations build up a chain of functions which, when called, results in the operations being performed
 with the correct associativity. We are calling `foldRight` with the `B` type being instantiated to `B => B`, then
 calling the built up function with the `z` argument. Try expanding the definitions by substituting equals for equals
 using a simple example, like `foldLeft(List(1,2,3), 0)(_ + _)` if this isn't clear. Note these implementations are
 more of theoretical interest - they aren't stack-safe and won't work for large lists.
 */
  def foldRightViaFoldLeft[A, B](list: List[A], z: B)(f: (A, B) => B): B =
  foldLeft(reverse(list), z)((b, a) => f(a, b))

  def foldLeftViaFoldRight[A, B](list: List[A], z: B)(f: (B, A) => B): B =
    foldRight(list, (b: B) => b)((a, g) => b => g(f(b, a)))(z)

  // EXERCISE 3.14
  def appendViaFoldRight[A](list1: List[A], list2: List[A]): List[A] =
    foldRight(list1, list2)(Cons(_, _))

  // EXERCISE 3.15
  def concatenate[A](list: List[List[A]]): List[A] =
    foldLeft(list, Nil: List[A])((z, x) => append(x, z))

  // EXERCISE 3.16
  def incrementEach(list: List[Int]): List[Int] =
    foldRight(list, Nil: List[Int])((i, acc) => Cons(i + 1, acc))

  // EXERCISE 3.17
  def doubleToString(list: List[Double]): List[String] =
    foldRight(list, Nil: List[String])((d, acc) => Cons(d.toString, acc))

  // EXERCISE 3.18
  def map[A, B](list: List[A])(f: A => B): List[B] =
    foldRight(list, Nil: List[B])((h, t) => Cons(f(h), t))

  // EXERCISE 3.19
  def filter[A](list: List[A])(f: A => Boolean): List[A] =
    foldRight(list, Nil: List[A])((h, t) => if (f(h)) Cons(h, t) else t)

  // EXERCISE 3.20
  def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] =
    concatenate(map(list)(f))

  // EXERCISE 3.21
  def filterViaFlatMap[A](list: List[A])(f: A => Boolean): List[A] =
    flatMap(list)(a => if (f(a)) List(a) else Nil)
}
