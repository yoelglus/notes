package com.yoelglus.notes.presentation.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yoelglus.notes.R
import com.yoelglus.notes.domain.Note
import com.yoelglus.notes.presentation.presenter.NotePresenter
import com.yoelglus.notes.presentation.presenter.PresenterFactory
import kotlinx.android.synthetic.main.activity_note_detail.*
import kotlinx.android.synthetic.main.note_detail.*

class NoteDetailFragment : Fragment(), NotePresenter.View {

    private val presenter: NotePresenter by lazy {
        PresenterFactory.createNotePresenter(context, arguments.getInt(ARG_ITEM_ID))
    }

    override fun showNote(note: Note) {
        activity.toolbar_layout?.title = note.title
        note_detail?.text = note.text
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.note_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this)
    }

    companion object {
        val ARG_ITEM_ID = "item_id"
    }
}
