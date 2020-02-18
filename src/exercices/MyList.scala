package exercices

/**
 * Created By Antoni M
 */
abstract class MyList[+A] {
  /*
      head = 1st element of the list
      tail = reminder of the list
      isEmpty - boolean = is this list empty
      add(int) => new list with
      toString => a string representation of the list
   */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >:A](element: B): MyList[B]
  def printElements:String
  override def toString: String = "[ " + printElements + " ]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A,MyList[B]]):MyList[B]
  def filter(predicate:MyPredicate[A] ):MyList[A]

  def ++[B >: A](list:MyList[B]):MyList[B]
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >:Nothing](element: B): MyList[B] = new Cons(element,Empty)
  def printElements: String = ""
  def map[B](transformer: MyTransformer[Nothing,B]):MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing,MyList[B]]):MyList[B] = Empty
  def filter(predicate:MyPredicate[Nothing] ):MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]):MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(head), tail.map(transformer))
  }

  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(head)) new Cons(h, tail.filter(predicate))
    else t.filter(predicate)
  }

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(head) ++ tail.flatMap(transformer)
}
trait MyPredicate[-T]{
  def test(subj:T): Boolean
}
trait MyTransformer[-A,B]{
  def transform(elem: A):B
}

object ListTest extends App{
  val listOfIntegers : MyList[Int] = new Cons(1, new Cons(2,new Cons (3, Empty)))
  val anotherlistOfIntegers : MyList[Int] = new Cons(4, new Cons(5,new Cons (6, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println( listOfIntegers.map(new MyTransformer[Int,Int] {
    override def transform(elem: Int): Int = elem*2
  })).toString

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(subj: Int): Boolean = subj % 2 == 0
  })).toString

  println(anotherlistOfIntegers.flatMap(new MyTransformer[Int,MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem,new Cons(elem + 1, Empty))
  })).toString
}