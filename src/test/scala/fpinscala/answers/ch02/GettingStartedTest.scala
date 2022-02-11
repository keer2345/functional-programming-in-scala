package fpinscala.answers.ch02

import org.scalatest.funsuite.AnyFunSuite


class GettingStartedTest extends AnyFunSuite {

  import fpinscala.answers.ch02.GettingStarted._

  test("test abs") {
    assert(42 == abs(-42))
  }

  test("test factorial") {
    assert(1 == factorial(1))
    assert(120 == factorial(5))
    assert(0 === fib1(0))
    assert(1 === fib1(1))
    assert(1 === fib2(2))
    assert(5 === fib2(5))
  }

  test("test formatResult") {
    assert("Ths absolute value of -43 is 43" == formatResult("absolute", -43, abs))
    assert("Ths factorial value of 4 is 24" == formatResult("factorial", 4, factorial))
  }

  test("test findFirst") {
    assert(3 == findFirst1(Array("a", "b", "c", "e", "d"), "e"))
    assertResult(2)(findFirst2(Array(1, 3, 2, 1, 2, 1))(_ == 2))
    assertResult(3)(findFirst2(Array("ab", "bc", "cd", "de", "ef"))(_.startsWith("d")))
  }

  test("test isSorted") {
    assertResult(true)(isSorted(Array(1))(_ < _))
    assertResult(true)(isSorted(Array(1, 3, 5, 7))(_ < _))
    assertResult(false)(isSorted(Array(1, 2, 3, 4))(_ > _))
  }
}

