package com.yoelglus.notes.presentation.presenter

import android.content.Context
import com.yoelglus.notes.data.SQLiteNotesRepository
import com.yoelglus.notes.domain.GetNote
import com.yoelglus.notes.domain.GetNotes

object PresenterFactory {
    fun createNotesListPresenter(context: Context) = NotesListPresenter(GetNotes(SQLiteNotesRepository(context)))
    fun createNotePresenter(context: Context, noteId: Int) = NotePresenter(GetNote(SQLiteNotesRepository(context)), noteId)
}