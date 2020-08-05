package com.example.server.repository

import com.example.server.db.dbQuery
import com.example.server.db.table.Cities
import com.example.server.db.table.toCity
import exapmle.com.common.CityDto
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class CitiesRepository {
    suspend  fun getAll() =
        dbQuery {
            Cities.selectAll().map { it.toCity() }
        }

    suspend fun add(createCityDto: CityDto)
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

    suspend fun update(id: Long, createCityDto: CityDto)
    {
        dbQuery {
            Cities.update {updateStatement ->
                Cities.id.eq(id)
                updateStatement[city] = createCityDto.city
            }
        }
    }
}