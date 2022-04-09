package ru.netology

import org.junit.Test
import org.junit.Assert.*
import ru.netology.attachment.Note

class NotesServiseTest {


    @Test
    fun addTest() {
        val result = NotesServise.add("заголовок", "заметка")
        assertEquals(3, result)
    }

    @Test
    fun createCommentTest() {
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.createComment(1, "Что-то")
        assertEquals(1, result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun createCommentWithNoteIsNotFoundTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(2, "Что-то")
    }

    @Test
    fun deleteTest() {
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.delete(1)
        assertTrue(result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun deleteIfDeletedTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.delete(1)
        NotesServise.delete(1)

    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun deleteWithNoteIsNotFoundTest() {
        NotesServise.add("заголовок", "заметка") // TODO попробовать поймать ошибку пустого массива Notes
        NotesServise.delete(2)
    }

    @Test
    fun deleteCommentTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        val result = NotesServise.deleteComment(1)
        assertTrue(result)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun deleteCommentIfDeletedTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        NotesServise.deleteComment(1)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun deleteCommentIfNotFoundTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(2)
    }

    @Test
    fun editTest() {
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.edit(1, "заголовок измененный", "заметка измененная")
        assertTrue(result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun editIfDeletedTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.delete(1)
        NotesServise.edit(1, "заголовок измененный", "заметка измененная")
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun editIfNotFoundTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.edit(2, "заголовок измененный", "заметка измененная")
    }

    @Test
    fun editCommentTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        val result = NotesServise.editComment(1, "что-то другое")
        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIfNotFoundTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.editComment(2, "что-то другое")
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIfDeletedTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        NotesServise.editComment(1, "что-то другое")
    }

    @Test(expected = CountExeption::class)
    fun getCountExeptionTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.get("1,2", 101)
    }

    @Test
    fun getNumberFormatExeptionTest() {
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.get("1 2", 2)
        assertEquals(null, result)
    }

    @Test
    fun getByIdTest() {
        NotesServise.add("заголовок", "заметка")
        val expected = NotesServise.notes.first()
        val result = NotesServise.getById(1)
        assertEquals(expected, result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun getByIdNoteIsNotFoundTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.getById(2)
    }

    @Test(expected = CountExeption::class)
    fun getCommentsCountExeptionTest() {
        NotesServise.getComments(1, 101)

    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun getCommentsNoteIsNotFoundTest() {
        NotesServise.add("заголовок", "заметка")
        (NotesServise.getById(1)).isDeleted = true
        NotesServise.getComments(2, 20)
    }

    @Test
    fun restoreCommentTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        val result = NotesServise.restoreComment(1)
        assertTrue(result)
    }

    @Test
    fun restoreCommentIsNotDeletedTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        val result = NotesServise.restoreComment(1)
        assertTrue(result)
    }

    @Test
    fun restoreCommentIsNotFoundTest() {
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        val result = NotesServise.restoreComment(1)
        assertTrue(result)
    }
}