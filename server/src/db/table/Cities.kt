package com.example.server.db.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Cities : Table() {
    val id: Column<Long> = Cities.long("id").autoIncrement().primaryKey()
    val city: Column<String> = Cities.text("city")
    val temperature: Column<Int> = Cities.integer("temperature")
}