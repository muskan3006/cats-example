package  com.knoldus.book

import PrintableInstances.catPrintable
import PrintableSyntax.PrintableOps

final case class Cat(name: String, age: Int, color: String)

trait Printable[A] {
def format(a:A):String
}
object PrintableInstances {
  implicit val stringInstance: Printable[String] = (a: String) => a
  implicit val intInstance: Printable[Int] = (a: Int) => a.toString
  implicit val catPrintable: Printable[Cat] = (cat: Cat) => {
    val name = Printable.format(cat.name)
    val age = Printable.format(cat.age)
    val color = Printable.format(cat.color)
    s"$name is a $age year-old $color cat."
  }
}
object Printable{
  def format[A](a:A)(implicit instance:Printable[A]): String ={
    instance.format(a)
  }
  def print[A](a:A)(implicit instance:Printable[A]):Unit={
    println(format(a))
  }
}

object PrintableSyntax{
  implicit class PrintableOps[A](a:A){
    def format(implicit instance:Printable[A]): String ={
      instance.format(a)
    }
    def print(implicit instance:Printable[A]):Unit={
      println(format(instance))
    }
  }
}

object Main extends App{
  Cat("Rolii",4,"Black").print
}