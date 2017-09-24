package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class DeleteNoteTest {
    @Test
    internal fun callsRepository() {
        val notesRepository = mock(NotesRepository::class.java)
        val deleteNote = DeleteNote(notesRepository)
        val note = Note(1, "text")

        deleteNote.execute(note)

        Mockito.verify(notesRepository).deleteNote(note)
    }
}