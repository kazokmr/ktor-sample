package com.example.plugins

import io.ktor.auth.*

data class MyUserPrincipal(val id: Long, val name: String, val profile: String) : Principal

fun Authentication.Configuration.basicAuthentication() {
    basic {
        validate { credential ->
            if (credential.name == "user" && credential.password == "password") {
                // TODO 認証処理

                val id = 100L
                val name = "user"
                val profile = "USER"
                MyUserPrincipal(id, name, profile)
            } else {
                null
            }
        }
    }
}
