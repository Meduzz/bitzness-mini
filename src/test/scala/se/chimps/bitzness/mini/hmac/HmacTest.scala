package se.chimps.bitzness.mini.hmac

import java.util.UUID

import org.scalatest.{FunSuite, Matchers}
import com.roundeights.hasher.Implicits._

class HmacTest extends FunSuite with Hmac with Matchers {

	test("hash a string and verify it") {
		val subject = UUID.randomUUID().toString
		val key = UUID.randomUUID().toString.sha256.hex

		val hash = signString(key, subject)
		verifyString(key, subject, hash) shouldBe true
	}

	test("hash some bytes and verify them") {
		val subject = UUID.randomUUID().toString.getBytes("utf-8")
		val key = UUID.randomUUID().toString.sha256.hex

		val hash = signBytes(key, subject)
		verifyBytes(key, subject, hash) shouldBe true
	}
}
