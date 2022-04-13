package ru.netology

import org.junit.Test
import org.junit.Assert.*
import ru.netology.attachment.Note
import ru.netology.attachment.noteCount

class NotesServiseTest {


    @Test
    fun addTest() {
        NotesServise.clear()
        val result = NotesServise.add("заголовок", "заметка")
        assertEquals(1, result)
    }

    @Test
    fun createCommentTest() {
        NotesServise.clear()

        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.createComment(1, "Что-то")
        assertEquals(1, result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun createCommentWithNoteIsNotFoundTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(2, "Что-то")
    }

    @Test
    fun deleteTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.delete(1)
        assertTrue(result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun deleteIfDeletedTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.delete(1)
        NotesServise.delete(1)

    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun deleteWithNoteIsNotFoundTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка") // TODO попробовать поймать ошибку пустого массива Notes
        NotesServise.delete(2)
    }

    @Test
    fun deleteCommentTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        val result = NotesServise.deleteComment(1)
        assertTrue(result)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun deleteCommentIfDeletedTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        NotesServise.deleteComment(1)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun deleteCommentIfNotFoundTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(2)
    }

    @Test
    fun editTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.edit(1, "заголовок измененный", "заметка измененная")
        assertTrue(result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun editIfDeletedTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.delete(1)
        NotesServise.edit(1, "заголовок измененный", "заметка измененная")
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun editIfNotFoundTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.edit(2, "заголовок измененный", "заметка измененная")
    }

    @Test
    fun editCommentTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        val result = NotesServise.editComment(1, "что-то другое")
        assertTrue(result)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun editCommentIfNotFoundTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.editComment(2, "что-то другое")
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun editCommentIfDeletedTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        NotesServise.editComment(1, "что-то другое")
    }

    @Test
    fun getTest() {
        NotesServise.clear()
        val note1 = Note("заголовок", "заметка")
        val note2  = Note("заголовок2", "заметка2")
        val expected: List<Note>? = listOf(note2, note1)
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.add("заголовок2", "заметка2")
        val result = NotesServise.get("1,2", 2)
        assertEquals(expected, result)
    }

    @Test(expected = CountExeption::class)
    fun getCountExeptionTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.get("1,2", 101)
    }

    @Test
    fun getNumberFormatExeptionTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.get("1 2", 2)
        assertEquals(null, result)
    }

    @Test
    fun getByIdTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val expected = NotesServise.notes.first()
        val result = NotesServise.getById(1)
        assertEquals(expected, result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun getByIdNoteIsNotFoundTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.getById(2)
    }

    @Test
    fun getCommentsTest() {
        NotesServise.clear()
        val comment1 = Comment(text = "комментарий1")
        val comment2 = Comment(text = "комментарий2")
        val expected : List<Comment> = listOf(comment1,comment2)
        commentCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Коммент1")
        NotesServise.createComment(1, "Коммент2")
        val result = NotesServise.getComments(1, 2)
        assertEquals(expected, result)

    }

    @Test(expected = CountExeption::class)
    fun getCommentsCountExeptionTest() {
        NotesServise.clear()
        NotesServise.getComments(1, 101)

    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun getCommentsNoteIsNotFoundTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        (NotesServise.getById(1)).isDeleted = true
        NotesServise.getComments(2, 20)
    }

    @Test
    fun restoreCommentTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        val result = NotesServise.restoreComment(1)
        assertTrue(result)
    }

    @Test(expected = CommentIsNotDeletedExeption::class)
    fun restoreCommentIsNotDeletedTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.restoreComment(1)

    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun restoreCommentIsNotFoundTest() {
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.restoreComment(1)

    }
}