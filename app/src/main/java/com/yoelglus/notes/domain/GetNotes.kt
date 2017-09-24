package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import io.reactivex.Single

class GetNotes(private val notesRepository: NotesRepository) {
    fun execute() : Single<List<Note>> = notesRepository.getNotes()
}