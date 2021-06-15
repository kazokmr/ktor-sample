package com.example.kotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserServiceTest : StringSpec() {
    init {
        "createMessage:: when user name is exist then return message"{
            val userRepository = mockk<UserRepository>()
            val target = UserService(userRepository)
            every { userRepository.findName(any()) } returns "Kotest"

            val id = 100
            val result = target.createMessage(id)

            result shouldBe "Hello Kotest"
            verify { userRepository.findName(id) }
        }
    }
}

