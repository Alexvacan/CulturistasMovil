package com.easypark.app.notes.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT COUNT(*) FROM notes")
    suspend fun getCount(): Int

    @Query("DELETE FROM notes WHERE id IN (SELECT id FROM notes ORDER BY createdAt ASC LIMIT :count)")
    suspend fun deleteOldest(count: Int)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}
