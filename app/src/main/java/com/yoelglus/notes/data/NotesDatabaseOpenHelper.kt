package com.yoelglus.notes.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class NotesDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "NotesDatabase", null, 1) {
    companion object {
        private var instance: NotesDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): NotesDatabaseOpenHelper {
            if (instance == null) {
                instance = NotesDatabaseOpenHelper(context.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable("Notes", true, "id" to INTEGER + PRIMARY_KEY + UNIQUE, "text" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // no upgrades yet
    }

}

val Context.notesDatabase: NotesDatabaseOpenHelper
    get() = NotesDatabaseOpenHelper.getInstance(applicationContext)
