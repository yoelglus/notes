package com.yoelglus.notes.presentation.presenter

import com.yoelglus.notes.domain.GetNote
import com.yoelglus.notes.domain.Note

class NotePresenter(private val getNote: GetNote, private val noteId: Int) : Presetner<NotePresenter.View>() {



    override fun onTakeView() {
        getNote.execute(noteId).doOnSuccess { note ->
            view?.showNote(note)
        }.doOnError {
            view?.showError(it.message)
        }
    }

    interface View {
        fun showNote(note: Note)
        fun showError(errorMessage: String?)
    }
}