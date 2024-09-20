package com.example.myapplication.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.core.database.dao.CardDAO
import com.example.myapplication.core.database.entities.CardEntity

@Database(entities = [CardEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    /** DAO'S */
    abstract fun CardDAO() : CardDAO

}