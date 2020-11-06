package com.knoldus.book
import cats.Id
import cats.syntax.either._
trait Monad[F[_]] {
  def pure[A](a: A): F[A]
  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
  def map[A, B](value: F[A])(func: A => B): F[B] =
    flatMap(value)(a =>pure(func(a)))
}
object CatsMonads extends App {
 //functions for Id
  def pure[A](value: A) =
    value
  def map[A, B](initial: Id[A])(func: A => B) =
    func(initial)
  def flatMap[A, B](initial: Id[A])(func: A => Id[B]) =
    func(initial)

  def countPositive(nums: List[Int]) =
    nums.foldLeft(0.asRight[String]) { (accumulator, num) =>
      if(num > 0) accumulator.map(_ + 1) else Left("Negative. Stopping!")
    }

  val either1 = Right(10)
  val either2= Left(32)
  println(either2)
 val one = 1.asRight

}
