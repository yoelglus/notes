package com.yoelglus.notes.data

import android.content.Context
import com.yoelglus.notes.domain.Note
import com.yoelglus.notes.domain.NotesRepository
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class SQLiteNotesRepository(private val context: Context) : NotesRepository {

    private val notesDatabase = context.notesDatabase

    override fun getNotes() : List<Note> {
        var notes: List<Note>
        notesDatabase.use {
            select("Notes").exec {
                val rawParser = classParser<Note>()
                notes = parseList(rawParser)
            }
        }

        return notes
    }

    override fun addNote(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateNote(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}