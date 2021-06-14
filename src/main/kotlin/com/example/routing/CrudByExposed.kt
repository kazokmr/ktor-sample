package com.example.routing

import com.example.entity.MemberEntity
import com.example.entity.MemberTable
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

@KtorExperimentalLocationsAPI
fun Routing.crud() {

    route("/member") {
        createSessionFactory()

        get("/list") { findAll() }

        @Location("/id/{id}")
        data class GetMemberById(val id: Int)
        get<GetMemberById> { request -> findById(request.id) }

        @Location("/name/{name}")
        data class GetMemberByName(val name: String)
        get<GetMemberByName> { request -> findByName(request.name) }

        post("/add") { call.receive<SaveMemberRequest>().let { create(it.name) } }

        put("/update") { call.receive<SaveMemberRequest>().let { update(it.id, it.name) } }

        @Location("/delete/{id}")
        data class DeleteMember(val id: Int)
        delete<DeleteMember> { request -> delete(request.id) }
    }
}

fun findAll() {
    transaction { MemberEntity.all().map { MemberModel(it) }.forEach { println(it) } }
}

fun findById(id: Int) {
    transaction { MemberEntity.findById(id)?.let { MemberModel(it) }.let { println(it) } }
}

fun findByName(name: String) {
    transaction { MemberEntity.find { MemberTable.name eq name }.map { MemberModel(it) }.let { println(it) } }
}

fun create(name: String) {
    transaction { MemberEntity.new { this.name = name }.let { MemberModel(it) }.let { println(it) } }
}

fun update(id: Int, name: String) {
    transaction {
        MemberEntity.findById(id)?.let { it.name = name }
        findById(id)
    }
}

fun delete(id: Int) {
    transaction { MemberEntity.findById(id)?.let { it.delete() } }
}

data class MemberModel(val id: Int, val name: String) {
    constructor(entity: MemberEntity) : this(entity.id.value, entity.name)
}

data class SaveMemberRequest(val id: Int, val name: String)


fun createSessionFactory() {
    Database.connect("jdbc:postgresql://127.0.0.1:5432/exposed", "org.postgresql.Driver", "exposed", "exposed")
}