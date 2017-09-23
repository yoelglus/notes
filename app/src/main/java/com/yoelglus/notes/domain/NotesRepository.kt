package com.yoelglus.notes.domain

interface NotesRepository {
    fun getNotes(): List<Note>

    fun addNote(note: Note)

    fun deleteNote(note: Note)

    fun updateNote(note: Note)
}