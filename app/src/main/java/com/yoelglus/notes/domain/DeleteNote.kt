package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository

class DeleteNote(private val notesRepository: NotesRepository) {
    fun execute(note: Note) = notesRepository.deleteNote(note)
}