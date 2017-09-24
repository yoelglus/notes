package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import io.reactivex.Completable

class UpdateNote constructor(private val notesRepository: NotesRepository) {
    fun execute(note: Note): Completable = notesRepository.updateNote(note)
}