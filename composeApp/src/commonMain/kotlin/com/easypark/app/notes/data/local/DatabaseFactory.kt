package com.easypark.app.notes.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getRoomDatabase(
    builder: RoomDatabase.Builder<NoteDatabase>
): NoteDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .build()
}

expect class DatabaseBuilder {
    fun createBuilder(): RoomDatabase.Builder<NoteDatabase>
}