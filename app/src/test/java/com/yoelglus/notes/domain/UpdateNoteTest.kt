package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import org.junit.Test
import org.mockito.Mockito

class UpdateNoteTest {
    @Test
    fun callsRepository() {
        val notesRepository = Mockito.mock(NotesRepository::class.java)
        val updateNote = UpdateNote(notesRepository)
        val note = Note(1, "title", "text")

        updateNote.execute(note)

        Mockito.verify(notesRepository).updateNote(note)
    }
}