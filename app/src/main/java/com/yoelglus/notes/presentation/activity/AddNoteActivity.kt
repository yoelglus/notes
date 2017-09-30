package com.yoelglus.notes.presentation.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.yoelglus.notes.R
import com.yoelglus.notes.presentation.presenter.AddNotePresenter
import com.yoelglus.notes.presentation.presenter.PresenterFactory
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity(), AddNotePresenter.View {

    override fun titleChanged(): Observable<String> = titleEditText.textChanges().map { it.toString() }

    override fun textChanged(): Observable<String> = textEditText.textChanges().map { it.toString() }

    override fun addButtonClicked(): Observable<Unit> = saveNoteButton.clicks()

    override fun notifyAddFailed(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun notifySuccess() {
        Toast.makeText(this, R.string.note_added_toast, Toast.LENGTH_SHORT).show()
        setResult(Activity.RESULT_OK)
        finish()
    }

    private val presenter: AddNotePresenter by lazy {
        PresenterFactory.createAddNotePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        presenter.takeView(this)
    }

}