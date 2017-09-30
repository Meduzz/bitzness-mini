package se.chimps.bitzness.mini

import org.scalatest.FunSuite

class OperationTest extends FunSuite {

	test("create an result from a function") {
		val op = Operation(() => Result("test"))

		assert(op != null)
		assert(op.nonEmpty)
		assert(op.result.nonEmpty)
		assert(op.isInstanceOf[Result[String]])
		assert(!op.isProblem)

		assert(op.result.contains("test"))
	}

	test("create a problem from a function") {
		val op = Operation(() => Problem[String]("test"))

		assert(op != null)
		assert(!op.nonEmpty)
		assert(op.result.isEmpty)
		assert(op.isInstanceOf[Problem[String]])
		assert(op.isProblem)
	}

	test("create an operation from data") {
		val op = Operation("test")

		assert(op != null)
		assert(op.nonEmpty)
		assert(op.result.nonEmpty)
		assert(op.isInstanceOf[Result[String]])
		assert(!op.isProblem)

		assert(op.result.contains("test"))
	}

	test("map") {
		val op = Operation("test")

		val reversed = op.map(t => t.reverse)

		assert(reversed != null)
		assert(reversed.nonEmpty)
		assert(reversed.result.nonEmpty)
		assert(reversed.isInstanceOf[Result[String]])
		assert(!reversed.isProblem)

		assert(reversed.result.contains("tset"))
	}

	test("flatMap") {
		val op = Operation("test")

		val reversed = op.flatMap(t => Result(t.reverse))

		assert(reversed != null)
		assert(reversed.nonEmpty)
		assert(reversed.result.nonEmpty)
		assert(reversed.isInstanceOf[Result[String]])
		assert(!reversed.isProblem)

		assert(reversed.result.contains("tset"))
	}

	test("map on problem") {
		val op = Operation(() => Problem[Int]("Something went wrong"))
			.map(_ * 2)
	  	.map(_ * 2)

		assert(op != null)
		assert(!op.nonEmpty)
		assert(op.result.isEmpty)
		assert(op.isInstanceOf[Problem[Int]])
		assert(op.isProblem)
	}
}
