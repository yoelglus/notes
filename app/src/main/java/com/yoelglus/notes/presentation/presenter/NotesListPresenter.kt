package com.yoelglus.notes.presentation.presenter

import com.yoelglus.notes.domain.GetNotes
import com.yoelglus.notes.domain.Note

class NotesListPresenter(private val getNotes: GetNotes): Presetner<NotesListPresenter.View>() {

    override fun onTakeView() {
        getNotes.execute().doOnSuccess { notes ->
            view?.showNotes(notes)
        }.doOnError {
            view?.showError("could not load notes")
        }
    }

    interface View {
        fun showNotes(notes: List<Note>)
        fun showError(message: String)
    }
}