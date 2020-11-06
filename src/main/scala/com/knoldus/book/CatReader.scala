package com.knoldus.book

import cats.data.Reader

case class Db(
               usernames: Map[Int, String],
               passwords: Map[String, String]
             )
class CatReader {
type DbReader[A] = Reader[Db,A]
  def findUsername(userId: Int): DbReader[Option[String]] ={
    Reader(user => user.usernames.get(userId) )
  }
  def checkPassword(
                     username: String,
                     password: String): DbReader[Boolean] ={
    Reader(db => db.passwords(username) contains(password))
  }

  def checkLogin(
                  userId: Int,
                  password: String): DbReader[Boolean] ={
    findUsername(userId).flatMap(username => username.map(x => checkPassword(x,password)).head)
  }
}

object CatReader extends App{
  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )
  val passwords = Map(
    "dade" -> "zerocool",
    "kate" -> "acidburn",
    "margo" -> "secret")

  val db = Db(users,passwords)
  println((new CatReader).checkLogin(2,"acidburn").run(db))
}
