package services


import play.api.test.Helpers._

import org.joda.time.DateMidnight


import org.specs2.mutable._
import org.specs2.time.NoTimeConversions

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import play.api.test.FakeApplication

class BelgacomReaderTest extends Specification with NoTimeConversions {

 "the movie search for today" should {
    "return data" in {
        running(FakeApplication()) {
          val today = new DateMidnight
	        val result = Await.result(BelgacomReader.readMovies(today), 60 seconds)
//	      result.map{ movie =>
//	          println(movie)
//	      }
          val movie = result.head
          movie.toDateTime.toDateMidnight must be equalTo today
          movie.getProgramUrl must not contain "null"
          movie.title must not beNull
	      //result must not be empty
        }
    }
  }
 
 "the movie search for tomorow" should {
    "return data" in {
        running(FakeApplication()) {
          val tomorow = new DateMidnight().plusDays(1)
	        val result = Await.result(BelgacomReader.readMovies(tomorow), 60 seconds)
//	      result.map{ movie =>
//	          println(movie)
//	      }
          val movie = result.head
          movie.toDateTime.toDateMidnight must be equalTo tomorow
          movie.getProgramUrl must not contain "null"
          movie.title must not beNull
	      //result must not be empty
        }
    }
  }
}