package com.yoelglus.notes

import android.app.Application
import com.yoelglus.notes.di.DaggerNotesApplicationComponent

import com.yoelglus.notes.di.NotesApplicationComponent
import com.yoelglus.notes.di.NotesApplicationModule


class NotesApplication : Application() {

    val component: NotesApplicationComponent by lazy {
        DaggerNotesApplicationComponent.builder().notesApplicationModule(NotesApplicationModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}