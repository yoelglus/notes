package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import org.junit.Test
import org.mockito.Mockito

class GetNotesTest {
    @Test
    fun callsRepository() {
        val notesRepository = Mockito.mock(NotesRepository::class.java)
        val getNotes = GetNotes(notesRepository)

        getNotes.execute()

        Mockito.verify(notesRepository).getNotes()
    }
}