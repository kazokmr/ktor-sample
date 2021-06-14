package com.example.routing

import io.ktor.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.exposedByDsl() {
    route("/exposed/dsl") {
        get {
            dsl()
        }
    }
}

private fun dsl() {
    Database.connect(
        url = "jdbc:postgresql://127.0.0.1:5432/exposed",
        driver = "org.postgresql.Driver",
        user = "exposed",
        password = "exposed"
    )

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

object Member : Table("member") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 32)
}
