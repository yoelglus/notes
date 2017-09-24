package com.yoelglus.notes.domain

import com.yoelglus.notes.domain.gateways.NotesRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class GetNoteTest {

    @Test
    fun callsRepository() {
        val notesRepository = mock(NotesRepository::class.java)
        val getNote = GetNote(notesRepository)
        val id = 1

        getNote.execute(id)

        verify(notesRepository).getNote(id)
    }
}