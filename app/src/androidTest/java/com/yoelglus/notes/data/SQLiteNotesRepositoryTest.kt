package com.yoelglus.notes.data

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.runner.AndroidJUnit4
import com.yoelglus.notes.domain.Note
import io.reactivex.observers.TestObserver.create
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SQLiteNotesRepositoryTest {

    private val sqLiteNotesRepository = SQLiteNotesRepository(getTargetContext())

    private val testNotesObserver = create<List<Note>>()

    @After
    fun tearDown() {
        sqLiteNotesRepository.getNotes().subscribe { notes ->
            notes.forEach { sqLiteNotesRepository.deleteNote(it).subscribe() }
        }
    }

    @Test
    fun returnsEmptyNotesList() {
        assertGetNotesReturns(emptyList())
    }

    @Test
    fun addsNoteToRepository() {
        val note = Note(1, "test note")
        val notesList = listOf(note)

        sqLiteNotesRepository.addNote(note).subscribe()

        assertGetNotesReturns(notesList)
    }

    @Test
    fun removesNotesFromRepository() {
        val note = Note(1, "test note")
        sqLiteNotesRepository.addNote(note).subscribe()

        sqLiteNotesRepository.deleteNote(note).subscribe()

        assertGetNotesReturns(emptyList())
    }

    @Test
    fun updatesNotesInRepository() {
        val note = Note(1, "test note")
        sqLiteNotesRepository.addNote(note).subscribe()

        val updatedNote = note.copy(text = "new text")
        sqLiteNotesRepository.updateNote(updatedNote).subscribe()

        assertGetNotesReturns(listOf(updatedNote))
    }

    private fun assertGetNotesReturns(notesList: List<Note>) {
        sqLiteNotesRepository.getNotes().toObservable().subscribe(testNotesObserver)
        testNotesObserver.assertResult(notesList)
    }
}