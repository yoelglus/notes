package com.yoelglus.notes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoelglus.notes.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_note_detail.*
import kotlinx.android.synthetic.main.note_detail.*

/**
 * A fragment representing a single Note detail screen.
 * This fragment is either contained in a [NoteListActivity]
 * in two-pane mode (on tablets) or a [NoteDetailActivity]
 * on handsets.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class NoteDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var mItem: DummyContent.DummyItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP[arguments.getString(ARG_ITEM_ID)]
            toolbar_layout?.title = mItem!!.content
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.note_detail, container, false)

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            note_detail?.text = mItem!!.details
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        val ARG_ITEM_ID = "item_id"
    }
}
