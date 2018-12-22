package se.chimps.bitzness.mini.hmac

import java.util.UUID

import com.roundeights.hasher.Implicits._
import org.scalatest.{FunSuite, Matchers}

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

	test("matches real world") {
		val subject = "very secret"
		val key = "08f20a5a09f2b3bcb5ded75da44dd2292a667fe89a803295b015b7f7429afd3e"

		val hash = subject.hmac(key).sha256.hex

		hash shouldBe "3fdc183da5b06a6a47476b674000951bfb62189af4e878d0f524ce8b70a3f7f8"
	}
}
