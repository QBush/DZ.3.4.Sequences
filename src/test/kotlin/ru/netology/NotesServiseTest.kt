package ru.netology

import org.junit.Test
import org.junit.Assert.*
import ru.netology.attachment.Note
import ru.netology.attachment.noteCount

class NotesServiseTest {


    @Test
    fun addTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        val result = NotesServise.add("заголовок", "заметка")
        assertEquals(1, result)
    }

    @Test
    fun createCommentTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.createComment(1, "Что-то")
        assertEquals(1, result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun createCommentWithNoteIsNotFoundTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(2, "Что-то")
    }

    @Test
    fun deleteTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.delete(1)
        assertTrue(result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun deleteIfDeletedTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.delete(1)
        NotesServise.delete(1)

    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun deleteWithNoteIsNotFoundTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка") // TODO попробовать поймать ошибку пустого массива Notes
        NotesServise.delete(2)
    }

    @Test
    fun deleteCommentTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        val result = NotesServise.deleteComment(1)
        assertTrue(result)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun deleteCommentIfDeletedTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        NotesServise.deleteComment(1)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun deleteCommentIfNotFoundTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(2)
    }

    @Test
    fun editTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.edit(1, "заголовок измененный", "заметка измененная")
        assertTrue(result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun editIfDeletedTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.delete(1)
        NotesServise.edit(1, "заголовок измененный", "заметка измененная")
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun editIfNotFoundTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.edit(2, "заголовок измененный", "заметка измененная")
    }

    @Test
    fun editCommentTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        val result = NotesServise.editComment(1, "что-то другое")
        assertTrue(result)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun editCommentIfNotFoundTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.editComment(2, "что-то другое")
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun editCommentIfDeletedTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        NotesServise.editComment(1, "что-то другое")
    }

    @Test(expected = CountExeption::class)
    fun getCountExeptionTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.get("1,2", 101)
    }

    @Test
    fun getNumberFormatExeptionTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.get("1 2", 2)
        assertEquals(null, result)
    }

    @Test
    fun getByIdTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        val expected = NotesServise.notes.first()
        val result = NotesServise.getById(1)
        assertEquals(expected, result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun getByIdNoteIsNotFoundTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.getById(2)
    }

    @Test(expected = CountExeption::class)
    fun getCommentsCountExeptionTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.getComments(1, 101)

    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun getCommentsNoteIsNotFoundTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        (NotesServise.getById(1)).isDeleted = true
        NotesServise.getComments(2, 20)
    }

    @Test
    fun restoreCommentTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        val result = NotesServise.restoreComment(1)
        assertTrue(result)
    }

    @Test(expected = CommentIsNotDeletedExeption::class)
    fun restoreCommentIsNotDeletedTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.restoreComment(1)

    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun restoreCommentIsNotFoundTest() {
        NotesServise.clear()
        commentCount = 0
        noteCount = 0
        NotesServise.add("заголовок", "заметка")
        NotesServise.restoreComment(1)

    }
}