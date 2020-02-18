package lectures

object Generics extends App {

   class MyList[A] {
     // use the type A
     // B is a supertype of A
     def add[B>:A](element: B): MyList[B] = ???
   }
  class MyMap[Key, Value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes List[Cat] extexnds List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  //animalList.add(new Dog) ??? HARD QUESTzION

  // 2. NO = INVARIANCE
  class InvariantList[A] // without +
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, NO! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  //bounded type
  class Cage [A <: Animal](animal: A) // <: Animals and subclass of Animal 
  val cage = new Cage(new Dog)

  // expand MyList to be generic

}



