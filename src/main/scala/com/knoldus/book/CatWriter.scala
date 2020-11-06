//package com.knoldus.book
//
//import cats.data.Writer
//import cats.implicits.{catsSyntaxApplicativeId, catsSyntaxWriterId}
//
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.duration.DurationInt
//import scala.concurrent.{Await, Future}
//
//object CatWriter extends App{
//        def slowly[A](body:A=>A)={
//        try body finally Thread.sleep(100)}
////  def factorial(n: Int): Int = {
////    val ans = slowly(if(n == 0) 1 else n * factorial(n - 1))
////    println(s"fact $n $ans")
////    ans
////  }
//        type Logged[A]=Writer[Vector[String],A]
//        def factorial(n:Int):Logged[Int]=
//        for{
//        ans<-if(n==0){
//        1.pure[Logged]
//        }else{
//        slowly(factorial(n-1).map(_*n))
//        }
//        _<-Vector(s"fact $n $ans").tell
//        }yield ans
//        val Vector((logA,ansA),(logB,ansB))=
//        Await.result(Future.sequence(Vector(
//        Future(factorial(3).run),
//        Future(factorial(5).run)
//        )),5.seconds)
//        println(Vector((logA,ansA),(logB,ansB)))
//        }
