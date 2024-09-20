package com.example.myapplication.core.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.core.database.Database
import com.example.myapplication.core.database.dao.CardDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "Database")
            .fallbackToDestructiveMigration(dropAllTables = false)
            .build()
    }

    @Singleton
    @Provides
    fun provideCardDAO(db: Database): CardDAO {
        return db.CardDAO()
    }

}