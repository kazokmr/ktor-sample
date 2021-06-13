package com.example.routing

import io.ktor.application.*
import io.ktor.locations.*
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
    }
}

data class BookResponse(val id: Long, val title: String, val author: String) {

}
