package com.yoelglus.notes.presentation.presenter

import com.yoelglus.notes.domain.AddNote
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class AddNotePresenter(private val addNote: AddNote) : Presetner<AddNotePresenter.View>() {

    private val compositeDisposable = CompositeDisposable()
    private var title = ""
    private var text = ""

    override fun onTakeView() {
        val view = view
        if (view != null) {
            compositeDisposable.add(view.titleChanged().subscribe { title = it })
            compositeDisposable.add(view.textChanged().subscribe { text = it })
            compositeDisposable.add(view.addButtonClicked().subscribe {
                addNote.execute(title, text).doOnSuccess {
                    view.notifySuccess()
                }.doOnError {
                    view.notifyAddFailed(it.message)
                }.subscribe()
            })
        }
    }

    override fun onDropView() {
        compositeDisposable.dispose()
    }


    interface View {
        fun titleChanged(): Observable<String>
        fun textChanged(): Observable<String>
        fun addButtonClicked(): Observable<Unit>
        fun notifyAddFailed(errorMessage: String?)
        fun notifySuccess()

    }
}