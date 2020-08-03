package com.example.server.repository

import City
import CreateCityDto
import com.example.server.db.dbQuery
import com.example.server.db.table.Cities
import com.example.server.db.table.toCity
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class CitiesRepository {
    suspend  fun getAll() =
        dbQuery {
            Cities.selectAll().map { it.toCity() }
        }

    suspend fun add(createCityDto: CreateCityDto)
    {
        dbQuery {
            Cities.insert{ insertStatement ->
                insertStatement[city] = createCityDto.city
            }
        }
    }

    suspend fun delete(id : Long) {
        dbQuery {
            Cities.deleteWhere {
                Cities.id.eq(id)
            }

        }
    }
}