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
            select(NotesDatabaseOpenHelper.NOTES_TABLE_NAME).exec {
                val rawParser = classParser<Note>()
                note = parseOpt(rawParser)
            }
        }
        note
    }

    override fun addNote(note: Note): Completable = createCompletable {
        notesDatabase.use {
            insert(NotesDatabaseOpenHelper.NOTES_TABLE_NAME, "id" to note.id, "text" to note.text)
        }

    }

    override fun deleteNote(note: Note): Completable = createCompletable {
        notesDatabase.use {
            delete(NotesDatabaseOpenHelper.NOTES_TABLE_NAME, "id = {noteId}", "noteId" to note.id)
        }
    }

    override fun updateNote(note: Note): Completable = createCompletable {
        notesDatabase.use {
            update(NotesDatabaseOpenHelper.NOTES_TABLE_NAME, "text" to note.text)
                    .whereSimple("id = ?", note.id.toString()).exec()
        }
    }

}