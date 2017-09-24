package com.yoelglus.notes.domain.gateways

import com.yoelglus.notes.domain.Note
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface NotesRepository {
    fun getNotes(): Single<List<Note>>

    fun getNote(id: Int): Maybe<Note>

    fun addNote(title: String, text: String): Single<Int>

    fun deleteNote(note: Note): Completable

    fun updateNote(note: Note): Completable
}