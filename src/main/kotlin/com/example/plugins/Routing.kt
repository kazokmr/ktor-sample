package com.example.plugins

import com.example.routing.bookRoute
import com.example.routing.greetingRoute
import com.example.routing.hello
import com.example.routing.userRoute
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        greetingRoute()
        hello()
        userRoute()
        bookRoute()
    }

}
