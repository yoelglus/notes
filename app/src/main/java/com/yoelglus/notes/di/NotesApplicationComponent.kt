package com.yoelglus.notes.di

import com.yoelglus.notes.NotesApplication
import com.yoelglus.notes.presentation.activity.NoteListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NotesApplicationModule::class))
interface NotesApplicationComponent {
    fun inject(app: NotesApplication)
    fun inject(activity: NoteListActivity)
}