package com.example.routing

import com.example.entity.Member
import com.example.entity.MemberEntity
import io.ktor.routing.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.exposed() {

    route("/exposed") {

        connectDatabase()

        get("/dsl") {
            dsl()
        }

        get("dao") {
            dao()
        }
    }
}

private fun dao() {
    transaction {
        addLogger(StdOutSqlLogger)

        // insert
        val member = MemberEntity.new {
            name = "Kotlin"
        }
        println("Inserted id: ${member.id}")

        // select
        MemberEntity.findById(member.id)?.let {
            println("id: ${it.id}")
            println("name: ${it.name}")
        }
    }
}

private fun dsl() {
    transaction {
        addLogger(StdOutSqlLogger)

        // Insert
        val id = Member.insert {
            it[name] = "Kotlin"
        } get Member.id
        println("Inserted id: $id")

        // select
        val member = Member.select { Member.id eq id }.single()
        println("id: ${member[Member.id]}")
        println("name: ${member[Member.name]}")
    }
}

private fun connectDatabase() {
    Database.connect(
        url = "jdbc:postgresql://127.0.0.1:5432/exposed",
        driver = "org.postgresql.Driver",
        user = "exposed",
        password = "exposed"
    )
}
