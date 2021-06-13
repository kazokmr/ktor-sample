package com.example.routing

import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Routing.bookRoute() {
    route("/book") {
        @Location("/detail/{bookId}")
        data class BookLocation(val bookId: Long)
        get<BookLocation> { request ->
            val response = BookResponse(request.bookId, "Kotlin入門", "Kotlin太郎")
            call.respond(response)
        }

        post("/register") {
            val request = call.receive<RegisterRequest>()
            // TODO 登録処理
            call.respondText("registered. id=${request.id} title=${request.title} author=${request.author}")
        }
    }
}

data class BookResponse(val id: Long, val title: String, val author: String) {}

data class RegisterRequest(val id: Long, val title: String, val author: String) {}