package com.example.server.db.table

import exapmle.com.common.City
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toCity() = City(
    id = this[Cities.id],
    city = this[Cities.city]
)