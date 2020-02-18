package lectures

object CaseClasses extends App{

  case class Person(name: String, age: Int)

    //   1. class parameters are fields
    val jim = new Person("Jim",34)
    println(jim.name)

    // 2. sensible toString
    println(jim.toString)
}
