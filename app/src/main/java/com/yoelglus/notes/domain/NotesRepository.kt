package com.yoelglus.notes.domain

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface NotesRepository {
    fun getNotes(): Single<List<Note>>

    fun getNote(id: Int): Maybe<Note>

    fun addNote(note: Note): Completable

    fun deleteNote(note: Note): Completable

    fun updateNote(note: Note): Completable
}