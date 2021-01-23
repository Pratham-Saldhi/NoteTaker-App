package com.example.note_taker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table") // After the entity annotation, we make an actual sql-lite table of Note.kt class

//"text" is the column in the table
data class Note(@ColumnInfo(name = "text") val text: String){
    @PrimaryKey(autoGenerate = true)
    private var id :Int =  0 // Making a primaryKey that auto-increments from id = 0
    fun getId(): Int{
        return id
    }
    fun setId(num: Int){
        this.id = num
    }
}