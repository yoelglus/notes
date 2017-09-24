package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import org.junit.Test
import org.mockito.Mockito.mock

import org.mockito.Mockito.verify

class AddNoteTest {
    @Test
    internal fun callsRepository() {
        val notesRepository = mock(NotesRepository::class.java)
        val addNote = AddNote(notesRepository)
        val note = Note(1, "text")

        addNote.execute(note)

        verify(notesRepository).addNote(note)
    }
}