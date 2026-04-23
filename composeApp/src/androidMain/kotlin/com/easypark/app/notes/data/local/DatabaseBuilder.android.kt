package com.easypark.app.notes.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseBuilder(private val context: Context) {
    actual fun createBuilder(): RoomDatabase.Builder<NoteDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath("notes.db")
        return Room.databaseBuilder<NoteDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}