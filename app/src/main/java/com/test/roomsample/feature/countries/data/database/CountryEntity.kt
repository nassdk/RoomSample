package com.test.roomsample.feature.countries.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.roomsample.feature.countries.data.database.CountryEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CountryEntity(

    @PrimaryKey @ColumnInfo(name = "countryId")
    val id: String,

    @ColumnInfo(name = "countryName")
    val name: String
) {
    companion object {
        const val TABLE_NAME = "COUNTRIES_ENTITY_TABLE_NAME"
    }
}