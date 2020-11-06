package com.knoldus.book

trait Semigroup[A]{
  def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A]{
  def empty: A
}
object Monoid{
  implicit val firstBooleanMonoid :Monoid[Boolean]= new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
    override def empty = true
  }

  implicit val secondBooleanMonoid :Monoid[Boolean]= new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
    override def empty = false
  }

  implicit val thirdBooleanMonoid :Monoid[Boolean]= new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (!x && y) || (x && !y)
    override def empty = false
  }

  implicit val fourthBooleanMonoid :Monoid[Boolean]= new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x || !y)
    override def empty = true
  }

  implicit def setMonoid[A]():Monoid[Set[A]]= new Monoid[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y

    override def empty = Set.empty[A]
  }

  implicit def setSemigroup[A]():Semigroup[Set[A]]= (x: Set[A], y: Set[A]) => x intersect y

  val intSetMonoid : Monoid[Set[Int]]= setMonoid[Int]()
  val strSetSemigroup:Semigroup[Set[String]] =setSemigroup()
//  val strSetMonoid = Monoid[Set[String]]
  intSetMonoid.combine(Set(1, 2), Set(2, 3))
  strSetSemigroup.combine(Set("richa","richiee"),Set("Heli","Heela"))

}