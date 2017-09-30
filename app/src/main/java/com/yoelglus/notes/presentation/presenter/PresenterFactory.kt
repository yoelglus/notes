package com.yoelglus.notes.presentation.presenter

import android.content.Context
import com.yoelglus.notes.data.SQLiteNotesRepository
import com.yoelglus.notes.domain.AddNote
import com.yoelglus.notes.domain.GetNote
import com.yoelglus.notes.domain.GetNotes

object PresenterFactory {
    fun createNotesListPresenter(context: Context) = NotesListPresenter(GetNotes(SQLiteNotesRepository.getInstance(context)))
    fun createNotePresenter(context: Context, noteId: Int) = NotePresenter(GetNote(SQLiteNotesRepository.getInstance(context)), noteId)
    fun createAddNotePresenter(context: Context) = AddNotePresenter(AddNote(SQLiteNotesRepository.getInstance(context)))
}