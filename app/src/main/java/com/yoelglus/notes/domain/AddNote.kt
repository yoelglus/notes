package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import io.reactivex.Maybe

class AddNote(private val notesRepository: NotesRepository) {
    fun execute(title: String, text: String): Maybe<Int> = notesRepository.addNote(title, text)
}