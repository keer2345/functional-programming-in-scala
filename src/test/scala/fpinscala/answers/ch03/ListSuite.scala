package fpinscala.answers.ch03

import org.scalatest.funsuite.AnyFunSuite
import fpinscala.answers.ch03.{List => CustomList}

class ListSuite extends AnyFunSuite {
  test("sum") {
    val list = CustomList(1, 3, 4, 5)
    assert(13 === CustomList.sum(list))
  }

  test("product") {
    val list = CustomList(1.0, 3.0, 4.0, 5.0)
    assert(60.0 === CustomList.product(list))
  }

  test("tail") {
    val list = CustomList("a", "b", 1, 3.0)
    assert(CustomList("b", 1, 3.0) === CustomList.tail(list))
  }

  test("setHead") {
    val list = CustomList("a", "b", 1, 3.0)
    assert(CustomList(0, "b", 1, 3.0) === CustomList.setHead(list, 0))
  }

  test("drop") {
    val list = CustomList(1, 3, 4, 5)
    assert(CustomList(4, 5) === CustomList.drop(list, 2))
  }

  test("dropWhile") {
    val list = CustomList(1, 2, 6, 3, 5)
    assert(CustomList(6, 3, 5) === CustomList.dropWhile(list)(_ < 4))
    assert(CustomList(6, 5) === CustomList.dropWhile2(list)(_ < 4))
  }

  test("append") {
    val a1 = CustomList(1, 3, 4)
    val a2 = CustomList(6, 8, 9)
    assert(CustomList(1, 3, 4, 6, 8, 9) === CustomList.append(a1, a2))
  }

  test("init") {
    val list = CustomList(1, 2, 6, 3, 5)
    assert(CustomList(1, 2, 6, 3) === CustomList.init(list))
  }

  test("foldRight - sum2") {
    val list = CustomList(1, 2, 6, 3, 5)
    assert(17 === CustomList.sum2(list))
  }

  test("foldRight - product2") {
    val list = CustomList(1.0, 2.0, 6.0, 3.0, 5.0)
    assert(180 === CustomList.product2(list))
  }
  test("foldRight - length") {
    val list = CustomList(1.0, 2.0, 6.0, 3.0, 5.0)
    assert(5 === CustomList.length(list))
  }

  test("foldLeft - sum3") {
    val list = CustomList(1, 2, 6, 3, 5)
    assert(17 === CustomList.sum3(list))
  }

  test("foldLeft - product3") {
    val list = CustomList(1.0, 2.0, 6.0, 3.0, 5.0)
    assert(180 === CustomList.product3(list))
  }
  test("foldLeft - reverse") {
    val list = CustomList(1.0, 2.0, 6.0, 3.0, 5.0)
    assert(CustomList(5.0, 3.0, 6.0, 2.0, 1.0) === CustomList.reverse(list))
  }
  test("append via foldRight") {
    val list1 = CustomList(1, 3, 5)
    val list2 = CustomList(2, 6, 7)
    assert(CustomList(1, 3, 5, 2, 6, 7) === CustomList.appendViaFoldRight(list1, list2))
  }

  test("concatenate") {
    val list0 = CustomList(6, 7, 8, 9)
    val list1 = CustomList(3, 4, 5)
    val list2 = CustomList(1, 2)
    val exp = CustomList(Range(1, 10): _*)
    assert(CustomList.concatenate(CustomList(list0, list1, list2)) === exp)
  }

  test("incrementEach") {
    val list = CustomList(1, 2, 3, 4, 5)
    assert(CustomList.incrementEach(list) === CustomList(2, 3, 4, 5, 6))
  }
  test("doubleToString") {
    val list = CustomList(1.1, 2.2, 3.3, 4.4, 5.5)
    assert(CustomList.doubleToString(list) === CustomList("1.1", "2.2", "3.3", "4.4", "5.5"))
  }

  test("map") {
    val list = CustomList(1, 3, 5, 2)
    val exp = CustomList(2, 4, 6, 3)

    assert(CustomList.map(list)(_ + 1) === exp)
  }

  test("filter") {
    val list = CustomList(Range(1, 10): _*)
    val exp = CustomList(Range(2, 10, 2): _*)
    assert(CustomList.filter(list)(_ % 2 == 0) === exp)
  }
}
