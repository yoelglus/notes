package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import io.reactivex.Single

class AddNote(private val notesRepository: NotesRepository) {
    fun execute(title: String, text: String): Single<Int> = notesRepository.addNote(title, text)
}