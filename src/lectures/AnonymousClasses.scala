package lectures

object AnonymousClasses extends App {
  abstract trait Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahah")
  }

  println(funnyAnimal.getClass)

  class Person(name: String){
    def sayHi: Unit = println(s"hi, my name is $name, haw can I help?")
  }

  val jim = new Person("Jim"){
    override def sayHi: Unit = super.sayHi
  }


}
