package com.knoldus.book

import cats.{Eq, Show}
import cats.instances.double._
import cats.syntax.eq._
import cats.syntax.show._

object CatExperiment extends App{
        implicit val catEq:Eq[Cat]=Eq.instance[Cat]((cat1,cat2)=>cat1.name==cat2.name&&cat1.age==cat2.age&&cat1.color==cat2.color)
        implicit val catShow:Show[Cat]=Show.show(cat=>s"${cat.name} is ${cat.age} year old in ${cat.color} color")
//        println(Cat("Helli",8,"white").show)
//        println(123.5.show)
        println(1.2 === 2.7)
        println("Hwnry".show)
        val cat1=Cat("Heathcliff",38,"orange and black")
        val cat2=Cat("Heathcliff",38,"orange and black")
        println(cat1===cat2)
        val optionCat1=Option(cat1)
        val optionCat2=Option.empty[Cat]
        println(optionCat1===optionCat2)
        }
