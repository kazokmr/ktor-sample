package com.example

import com.example.plugins.configureRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.locations.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

@KtorExperimentalLocationsAPI
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Locations)
        install(ContentNegotiation) {
            jackson()
        }
        configureRouting()
    }.start(wait = true)
}
