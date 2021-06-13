package com.example.location

import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
@Location("/user")
class UserLocation {

    @Location("/{id}")
    data class GetUserLocation(val id: Long)

    @Location("/detail/{id}")
    data class GetDetailLocation(val id: Long)
}


@KtorExperimentalLocationsAPI
fun Routing.userRoute() {
    get<UserLocation.GetUserLocation> { param ->
        val id = param.id
        call.respondText("id=$id")
    }

    get<UserLocation.GetDetailLocation> { param ->
        val id = param.id
        call.respondText("getDetail id=$id")
    }
}


