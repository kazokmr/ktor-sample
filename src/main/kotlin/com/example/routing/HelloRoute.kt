package com.example.routing

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.hello() {
    route("/hello") {
        get("/{name}") {
            val name = call.parameters["name"]
            call.respondText("Hello $name!")
        }
        get("") {
            val name = call.parameters["name"]
            call.respondText("Hello $name!")
        }
    }
}
