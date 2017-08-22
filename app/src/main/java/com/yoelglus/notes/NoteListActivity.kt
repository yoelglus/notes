package com.yoelglus.notes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_note_list.*


import com.yoelglus.notes.dummy.DummyContent

/**
 * An activity representing a list of Notes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [NoteDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class NoteListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        setupRecyclerView(findViewById<RecyclerView>(R.id.note_list))

        if (findViewById<FrameLayout>(R.id.note_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(DummyContent.ITEMS)
    }

    inner class SimpleItemRecyclerViewAdapter(private val mValues: List<DummyContent.DummyItem>) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun getItemCount(): Int {
            return mValues.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.item = mValues[position]
            holder.idView.text = mValues[position].id
            holder.contentView.text = mValues[position].content

            holder.mView.setOnClickListener { v ->
                if (twoPane) {
                    val arguments = Bundle()
                    arguments.putString(NoteDetailFragment.ARG_ITEM_ID, holder.item!!.id)
                    val fragment = NoteDetailFragment()
                    fragment.arguments = arguments
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.note_detail_container, fragment)
                            .commit()
                } else {
                    val context = v.context
                    val intent = Intent(context, NoteDetailActivity::class.java)
                    intent.putExtra(NoteDetailFragment.ARG_ITEM_ID, holder.item!!.id)

                    context.startActivity(intent)
                }
            }
        }

        inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            val idView: TextView = mView.findViewById<TextView>(R.id.id)
            val contentView: TextView = mView.findViewById<TextView>(R.id.content)
            var item: DummyContent.DummyItem? = null

            override fun toString(): String {
                return super.toString() + " '" + contentView.text + "'"
            }
        }
    }
}
