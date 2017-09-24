package com.yoelglus.notes.presentation.activity

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
import com.yoelglus.notes.presentation.fragment.NoteDetailFragment
import com.yoelglus.notes.R
import kotlinx.android.synthetic.main.activity_note_list.*


import com.yoelglus.notes.dummy.DummyContent

class NoteListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        addNoteButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        setupRecyclerView(findViewById<RecyclerView>(R.id.note_list))

        if (findViewById<FrameLayout>(R.id.note_detail_container) != null) {
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
