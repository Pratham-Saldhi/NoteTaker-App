package com.example.note_taker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    private val repository : NoteRepository
     init{
         // Creating a dao that is of type NoteDao-- contains insert, delete and getAllNotes function.
         val dao  = NoteDataBase.getDatabase(application).getNoteDao()
         // Passing dao in NoteRepository
          repository = NoteRepository(dao)
         allNotes = repository.allNotes
     }
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}