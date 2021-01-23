package com.example.note_taker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
// NoteDao is used to communicate with the Sql-lite table/ entity.
// It contains commands like insert, delete, getAll() etc.
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    @Delete
    suspend fun delete(note: Note)
    @Query("Select * From note_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}