package com.example.routing

import com.example.plugins.MyUserPrincipal
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authenticatedUserRoute() {
    get("/authenticated") {
        val user = call.authentication.principal<MyUserPrincipal>()
        call.respondText("authenticated id=${user?.id} name=${user?.name} profile=${user?.profile}")
    }
}