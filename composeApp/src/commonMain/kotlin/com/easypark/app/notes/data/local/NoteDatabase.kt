package com.easypark.app.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase(), DB {
    abstract fun noteDao(): NoteDao
}

interface DB {
    fun clearAllTables() {}
}
