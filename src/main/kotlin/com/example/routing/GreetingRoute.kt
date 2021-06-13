package com.example.routing

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.greetingRoute() {
    get("/") {
        call.respondText("Hello Ktor!")
    }
    route("/greeting") {
        get("/hello") {
            call.respondText("Hello!")
        }
        get("/goodmorning") {
            call.respondText("Good morning!")
        }
    }
}