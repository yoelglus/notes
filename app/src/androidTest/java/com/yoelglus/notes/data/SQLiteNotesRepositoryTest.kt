package com.yoelglus.notes.data

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.runner.AndroidJUnit4
import com.yoelglus.notes.domain.Note
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SQLiteNotesRepositoryTest {

    private val sqLiteNotesRepository = SQLiteNotesRepository(getTargetContext())

    private val testNotesObserver = TestObserver<List<Note>>()

    @Test
    fun returnsEmptyNotesList() {
        sqLiteNotesRepository.getNotes().toObservable().subscribe(testNotesObserver)

        testNotesObserver.assertResult(emptyList())
    }
}