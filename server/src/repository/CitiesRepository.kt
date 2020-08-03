package com.example.server.repository

import City
import com.example.server.db.dbQuery
import com.example.server.db.table.Cities
import com.example.server.db.table.toCity
import org.jetbrains.exposed.sql.selectAll

class CitiesRepository {
    suspend  fun getAll() =
        dbQuery {
            Cities.selectAll().map { it.toCity() }
        }
}