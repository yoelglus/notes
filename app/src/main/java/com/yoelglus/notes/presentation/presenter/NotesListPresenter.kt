package com.yoelglus.notes.presentation.presenter

import com.yoelglus.notes.domain.GetNotes
import com.yoelglus.notes.domain.Note
import io.reactivex.disposables.Disposable

class NotesListPresenter(private val getNotes: GetNotes) : Presetner<NotesListPresenter.View>() {

    private var getNotesDisposable: Disposable? = null

    override fun onTakeView() {
        getNotesList()
    }

    private fun getNotesList() {
        getNotesDisposable = getNotes.execute().doOnSuccess { notes ->
            view?.showNotes(notes)
        }.doOnError {
            view?.showError("could not load notes")
        }.subscribe()
    }

    override fun onDropView() {
        getNotesDisposable?.dispose()
    }

    interface View {
        fun showNotes(notes: List<Note>)
        fun showError(message: String)
    }

    fun refreshData() {
        getNotesList()
    }
}