package com.knoldus.book

import cats.Monad

sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

object CustomMonad {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)
  def leaf[A](value: A): Tree[A] =
    Leaf(value)
val treeMonad: Monad[Tree] = new Monad[Tree] {
  override def pure[A](a: A): Tree[A] = leaf(a)


  override def flatMap[A, B](value: Tree[A])(func: A => Tree[B]): Tree[B] = {
    value match {
      case Branch(left, right) =>Branch(flatMap(left)(func),flatMap(right)(func))
      case Leaf(value) =>func(value)
    }
  }

  def tailRecM[A, B](a: A)
                    (func: A => Tree[Either[A, B]]): Tree[B] ={
    flatMap(func(a)) {
      case Left(value) =>
        tailRecM(value)(func)
      case Right(value) =>
        Leaf(value)
    }
  }
}
}
