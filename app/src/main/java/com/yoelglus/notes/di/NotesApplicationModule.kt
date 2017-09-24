package com.yoelglus.notes.di

import com.yoelglus.notes.NotesApplication
import com.yoelglus.notes.data.SQLiteNotesRepository
import com.yoelglus.notes.domain.GetNotes
import com.yoelglus.notes.domain.gateways.NotesRepository
import com.yoelglus.notes.presentation.presenter.NotesListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class NotesApplicationModule(val notesApplication: NotesApplication) {

    @Provides
    @Singleton
    fun provideNotesApplication() = notesApplication

    @Provides
    @Singleton
    fun provideGetNotes(notesRepository: NotesRepository) = GetNotes(notesRepository)

    @Provides
    @Singleton
    fun provideNotesRepository(context: NotesApplication): NotesRepository = SQLiteNotesRepository(context)

    @Provides
    @Singleton
    fun provideNotesListPresenter(getNotes: GetNotes) = NotesListPresenter(getNotes)

}