package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import io.reactivex.Maybe

class GetNote(private val notesRepository: NotesRepository) {
    fun execute(id: Int): Maybe<Note> = notesRepository.getNote(id)
}