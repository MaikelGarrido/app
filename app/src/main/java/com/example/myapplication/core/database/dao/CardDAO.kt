package com.example.myapplication.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.core.database.entities.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)

    @Query("SELECT * FROM cards")
    fun getAllCards(): Flow<List<CardEntity>>

    @Query("DELETE FROM cards WHERE cardNumber = :cardNumber")
    suspend fun deleteCard(cardNumber: String)
}