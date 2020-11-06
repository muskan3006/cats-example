
import java.time.{Instant, LocalDateTime, LocalTime, ZoneId}
import java.util.Date

import cats.implicits.toFoldableOps
import cats.implicits._
import cats.{Foldable, Monad, Monoid, Semigroup, implicits}
val a: Seq[AnyVal] = Seq(1,true)
val c: Seq[IterableOnce[Int] with Equals] = Seq(Seq(1,2,3),Option(1))
val b: Seq[Equals] = Seq(Seq(1,2,3),(4,56))


println("----------------**********************--------------------------")

Semigroup[Int].combine(1, 2)
Semigroup[List[Int]].combine(List(4,5,6),List(1,2,3))
Semigroup[Option[Int]].combine(Option(1), Option(2))
Semigroup[Option[Int]].combine(Option(1), None)
Semigroup[Int => Int].combine(_ + 1, _ * 10).apply(6)


val aMap = Map("foo" -> Map("bar" -> 5))
val anotherMap = Map("foo" -> Map("bar" -> 6))
val combinedMap = Semigroup[Map[String, Map[String, Int]]].combine(aMap, anotherMap)
combinedMap.get("foo")

aMap ++ anotherMap


Monoid[String].empty
Monoid[String].combineAll(List("a", "b", "c"))
Monoid[String].combineAll(List()) == Nil
val l = List(1, 2, 3, 4, 5)
l.foldMap(identity)

Option(1).map(x => x + 1)

Monad[List].ifM(List(true, false, true))(List(1, 2), List(3, 4))
Monad[Option].ifM(Option(true))(Option("truthy"), Option("falsy"))

Foldable[List].foldK(List(None, Option("two"), Option("three")))
Foldable[List].foldK(List(List(1, 2), List(3, 4, 5)))



def parseInt(s: String): Option[Int] =
  Either.catchOnly[NumberFormatException](s.toInt).toOption

Foldable[List].traverse_(List("1", "2", "3"))(parseInt)
def parseIntEither(s: String): Either[NumberFormatException, Int] =
  Either.catchOnly[NumberFormatException](s.toInt)
List("1", "abc", "3").traverse(parseIntEither).isLeft


val right: Either[String, Int] = Either.right(5)
right.flatMap(x => Either.right(x + 1))

object EitherStyle {
  def parse(s: String): Either[NumberFormatException, Int] =
    if (s.matches("-?[0-9]+")) Either.right(s.toInt)
    else Either.left(new NumberFormatException(s"${s} is not a valid integer."))

  def reciprocal(i: Int): Either[IllegalArgumentException, Double] =
    if (i == 0) Either.left(new IllegalArgumentException("Cannot take reciprocal of 0."))
    else Either.right(1.0 / i)

  def stringify(d: Double): String = d.toString

  def magic(s: String): Either[Exception, String] =
    parse(s).flatMap(reciprocal).map(stringify)
}
val result =EitherStyle.magic("2") match {
  case Left(_: NumberFormatException) => "Not a number!"
  case Left(_: IllegalArgumentException) => "Can't take reciprocal of 0!"
  case Left(_) => "Unknown error"
  case Right(result) => s"Got reciprocal: $result"
}
Either.catchOnly[NumberFormatException]("abc".toInt).isRight
val k:(String,Int) = ("hii",1)

List(1,2,34,4) + "Hi"
val ISTZoneId = ZoneId.of("Asia/Kolkata")
def toLocalDateTimeEndOfDay(date: Date): LocalDateTime =
  Instant.ofEpochMilli(date.getTime).atZone(ISTZoneId).toLocalDateTime

toLocalDateTimeEndOfDay(new Date())

val op = 5.pure[Option]
val op1 = op.flatMap(a => Some(a + 2))
op1.map(a => 100 * a)