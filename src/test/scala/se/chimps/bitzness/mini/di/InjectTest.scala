package se.chimps.bitzness.mini.di

import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class InjectTest extends FunSuite with ScalaFutures {

	test("creation and injection") {
		Inject("testClass1", new TestClass1)

		val result = Future {
			val subject = new TestClass2
			subject.hello
		}

		whenReady(result) { text =>
			assert(text.equals("Hello world!"))
		}
	}
}

class TestClass1 {
	def world:String = "world!"
}

class TestClass2 {
	val t:TestClass1 = Inject("testClass1")

	def hello:String = s"Hello ${t.world}"
}