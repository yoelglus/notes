package com.yoelglus.notes.presentation.presenter

import android.content.Context
import com.yoelglus.notes.data.SQLiteNotesRepository
import com.yoelglus.notes.domain.GetNotes

object PresenterFactory {
    fun createNotesListPresenter(context: Context) = NotesListPresenter(GetNotes(SQLiteNotesRepository(context)))
}