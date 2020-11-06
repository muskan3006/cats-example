package com.knoldus.book

import cats.data.EitherT
import cats.implicits.catsStdInstancesForFuture

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

object TransformingMonads{
        type Response[A]=EitherT[Future,String,A]
        val powerLevels=Map(
        "Jazz"->6,
        "Bumblebee"->8,
        "Hot Rod"->10
        )

        def tacticalReport(ally1:String,ally2:String):String={
        val stack=canSpecialMove(ally1,ally2).value
        Await.result(stack,1.second)match{
        case Left(msg)=>
        s"Comms error: $msg"
        case Right(true)=>
        s"$ally1 and $ally2 are ready to roll out!"
        case Right(false)=>
        s"$ally1 and $ally2 need a recharge."
        }
        }

        def canSpecialMove(ally1:String,ally2:String):Response[Boolean]={
        val a=getPowerLevel(ally1)
        val b=getPowerLevel(ally2)
        a.flatMap(x=>b.map(y=>x+y>15))
        }

        def getPowerLevel(autobot:String):Response[Int]={
        powerLevels.get(autobot)match{
        case Some(value)=>EitherT.right(Future(value))
        case None=>EitherT.left(Future("Died"))
        }
        }
        }
