package com.knoldus.book
import cats.implicits.catsSyntaxSemigroup
import cats.Monoid
import cats.instances.all

case class Order(totalCost: Double, quantity: Double)

object MonoidsSemigroupCats extends App{
//def add(items: List[Int]): Int ={
//  items.foldLeft(Monoid[Int].empty)((res,ele)=> res |+| ele)
//}
  implicit val orderMonoid = new Monoid[Order]{
  override def empty: Order = Order(0,0)

  override def combine(x: Order, y: Order): Order = Order(x.totalCost+y.totalCost ,x.quantity+y.quantity)
}
  def add[A](items:List[A])(implicit monoid: Monoid[A])= items.foldLeft(Monoid[A].empty)((res, ele)=> res |+| ele)

 println( add[Int](List(1,2,3)))
  println(add[Option[Int]](List(Some(1),Some(2),None)))
  println(add[Order](List(Order(3,5),Order(6,7))))

}
