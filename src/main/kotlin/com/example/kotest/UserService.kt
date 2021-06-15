package com.example.kotest

class UserService(private val userRepository: UserRepository) {
    fun createMessage(id: Int): String? {
        return if (id < 0) null else userRepository.findName(id)?.let { "Hello $it" }
    }
}