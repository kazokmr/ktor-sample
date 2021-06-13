package com.example.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/hello/{name}") {
            val name = call.parameters["name"]
            call.respondText("Hello $name!")
        }
        get("/hello") {
            val name = call.parameters["name"]
            call.respondText("Hello $name!")
        }
    }

}
