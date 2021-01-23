package com.example.note_taker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), INoteRVAdapter {


    lateinit var viewModel: NoteViewModel
   // lateinit var input: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // input = findViewById(R.id.input)
       // val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list->
        // The list we get could be nullable
        // So using " list?. == to Check whether nullable or not "
        list?.let {
            // If list is not nullable, only then this block is executed
            adapter.updateList(it)
        }

        })

    }

    override fun onItemClicked(notes: Note) {
        viewModel.deleteNote(notes)
        Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()

        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "Inserted", Toast.LENGTH_LONG).show()
        }
    }
}