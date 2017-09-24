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
        val title = "title"
        val text = "text"

        sqLiteNotesRepository.addNote(title, text).subscribe {
            val notesList = listOf(Note(it, title, text))
            assertGetNotesReturns(notesList)
        }
    }

    @Test
    fun removesNotesFromRepository() {
        val title = "title"
        val text = "text"

        sqLiteNotesRepository.addNote(title, text).subscribe {
            val note = Note(it, title, text)

            sqLiteNotesRepository.deleteNote(note).subscribe()

            assertGetNotesReturns(emptyList())
        }

    }

    @Test
    fun updatesNotesInRepository() {
        val title = "title"
        val text = "text"

        sqLiteNotesRepository.addNote(title, text).subscribe {
            val note = Note(it, title, text)
            val updatedNote = note.copy(text = "new text")

            sqLiteNotesRepository.updateNote(updatedNote).subscribe()

            assertGetNotesReturns(listOf(updatedNote))
        }

    }

    private fun assertGetNotesReturns(notesList: List<Note>) {
        sqLiteNotesRepository.getNotes().toObservable().subscribe(testNotesObserver)
        testNotesObserver.assertResult(notesList)
    }
}