package com.yoelglus.notes.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.yoelglus.notes.R
import com.yoelglus.notes.presentation.fragment.NoteDetailFragment
import kotlinx.android.synthetic.main.activity_note_detail.*

/**
 * An activity representing a single Note detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [NoteListActivity].
 */
class NoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val arguments = Bundle()
            arguments.putInt(NoteDetailFragment.ARG_ITEM_ID,
                    intent.getIntExtra(NoteDetailFragment.ARG_ITEM_ID, -1))
            val fragment = NoteDetailFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction().add(R.id.note_detail_container, fragment).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, Intent(this, NoteListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
