package com.yoelglus.notes.data

import android.content.Context
import com.yoelglus.notes.domain.Note
import com.yoelglus.notes.domain.gateways.NotesRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import org.jetbrains.anko.db.*
import io.reactivex.Completable.fromCallable as createCompletable
import io.reactivex.Maybe.fromCallable as createMaybe
import io.reactivex.Single.fromCallable as createSingle

class SQLiteNotesRepository(context: Context) : NotesRepository {

    companion object {
        @Volatile private var INSTANCE: SQLiteNotesRepository? = null

        fun getInstance(context: Context): SQLiteNotesRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: SQLiteNotesRepository(context).also { INSTANCE = it }
                }
    }

    private val notesDatabase = context.notesDatabase

    override fun getNotes(): Single<List<Note>> = createSingle {
        val notes = arrayListOf<Note>()
        notesDatabase.use {
            select(NotesDatabaseOpenHelper.NOTES_TABLE_NAME).exec {
                val rawParser = classParser<Note>()
                notes.addAll(parseList(rawParser))
            }
        }
        notes
    }

    override fun getNote(id: Int): Maybe<Note> = createMaybe {
        var note: Note? = null
        notesDatabase.use {
            select(NotesDatabaseOpenHelper.NOTES_TABLE_NAME)
                    .whereArgs("id = {noteId}", "noteId" to id).exec {
                val rawParser = classParser<Note>()
                note = parseOpt(rawParser)
            }
        }
        note
    }

    override fun addNote(title: String, text: String): Single<Int> = createSingle {
        var rowId = -1L
        notesDatabase.use {
            rowId = insert(NotesDatabaseOpenHelper.NOTES_TABLE_NAME, "title" to title, "text" to text)
        }
        if (rowId == -1L)
            throw Exception("Failed to add note")
        rowId.toInt()
    }

    override fun deleteNote(note: Note): Completable = createCompletable {
        notesDatabase.use {
            delete(NotesDatabaseOpenHelper.NOTES_TABLE_NAME, "id = {noteId}", "noteId" to note.id)
        }
    }

    override fun updateNote(note: Note): Completable = createCompletable {
        notesDatabase.use {
            update(NotesDatabaseOpenHelper.NOTES_TABLE_NAME, "title" to note.title, "text" to note.text)
                    .whereSimple("id = ?", note.id.toString()).exec()
        }
    }

}