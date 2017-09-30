package com.yoelglus.notes.presentation.presenter

import com.yoelglus.notes.domain.GetNote
import com.yoelglus.notes.domain.Note
import io.reactivex.disposables.Disposable

class NotePresenter(private val getNote: GetNote, private val noteId: Int) : Presetner<NotePresenter.View>() {

    private var getNoteDisposable: Disposable? = null

    override fun onTakeView() {
        getNoteDisposable = getNote.execute(noteId).doOnSuccess { note ->
            view?.showNote(note)
        }.doOnError {
            view?.showError(it.message)
        }.subscribe()
    }

    override fun onDropView() {
        getNoteDisposable?.dispose()
    }

    interface View {
        fun showNote(note: Note)
        fun showError(errorMessage: String?)
    }
}