package com.knoldus.book

import cats.Eval

object CatsEval extends App{
        val greeting=Eval.
        now{
        println("Step 1");"Hello"
        }.
        map{str=>println("Step 2");s"$str world"}
        println(greeting.value)

        def factorial(n:BigInt):Eval[BigInt]=
        if(n==1){
        Eval.now(n)
        }else{
        Eval.defer(factorial(n-1).map(_*n))
        }

        def foldRightEval[A,B](as:List[A],acc:Eval[B])
        (fn:(A,Eval[B])=>Eval[B]):Eval[B]=
        as match{
        case head::tail=>
        Eval.defer(fn(head,foldRightEval(tail,acc)(fn)))
        case Nil=>
        acc
        }

        def foldRight[A,B](as:List[A],acc:B)(fn:(A,B)=>B):Eval[B]=
        foldRightEval[A,B](as,Eval.now(acc)){(a,b)=>
        b.map(fn(a,_))
        }
        }
