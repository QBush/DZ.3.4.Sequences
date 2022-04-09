package ru.netology

import org.junit.Test
import org.junit.Assert.*
import ru.netology.attachment.Note

class NotesServiseTest {


    @Test
    fun addTest() {
        WallServise.clear()
        NotesServise.clear()
        val result = NotesServise.add("заголовок", "заметка")
        assertEquals(1, result)
    }

    @Test
    fun createCommentTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.createComment(1, "Что-то")
        assertEquals(1, result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun createCommentWithNoteIsNotFoundTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(2, "Что-то")
    }

    @Test
    fun deleteTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.delete(1)
        assertTrue(result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun deleteIfDeletedTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.delete(1)
        NotesServise.delete(1)

    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun deleteWithNoteIsNotFoundTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка") // TODO попробовать поймать ошибку пустого массива Notes
        NotesServise.delete(2)
    }

    @Test
    fun deleteCommentTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        val result = NotesServise.deleteComment(1)
        assertTrue(result)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun deleteCommentIfDeletedTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        NotesServise.deleteComment(1)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun deleteCommentIfNotFoundTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(2)
    }

    @Test
    fun editTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.edit(1, "заголовок измененный", "заметка измененная")
        assertTrue(result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun editIfDeletedTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.delete(1)
        NotesServise.edit(1, "заголовок измененный", "заметка измененная")
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun editIfNotFoundTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.edit(2, "заголовок измененный", "заметка измененная")
    }

    @Test
    fun editCommentTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        val result = NotesServise.editComment(1, "что-то другое")
        assertTrue(result)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun editCommentIfNotFoundTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.editComment(2, "что-то другое")
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun editCommentIfDeletedTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        NotesServise.editComment(1, "что-то другое")
    }

    @Test(expected = CountExeption::class)
    fun getCountExeptionTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.get("1,2", 101)
    }

    @Test
    fun getNumberFormatExeptionTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val result = NotesServise.get("1 2", 2)
        assertEquals(null, result)
    }

    @Test
    fun getByIdTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        val expected = NotesServise.notes.first()
        val result = NotesServise.getById(1)
        assertEquals(expected, result)
    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun getByIdNoteIsNotFoundTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.getById(2)
    }

    @Test(expected = CountExeption::class)
    fun getCommentsCountExeptionTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.getComments(1, 101)

    }

    @Test(expected = NoteIsNotFoundExeption::class)
    fun getCommentsNoteIsNotFoundTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        (NotesServise.getById(1)).isDeleted = true
        NotesServise.getComments(2, 20)
    }

    @Test
    fun restoreCommentTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.deleteComment(1)
        val result = NotesServise.restoreComment(1)
        assertTrue(result)
    }

    @Test(expected = CommentIsNotDeletedExeption::class)
    fun restoreCommentIsNotDeletedTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.createComment(1, "Что-то")
        NotesServise.restoreComment(1)

    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun restoreCommentIsNotFoundTest() {
        WallServise.clear()
        NotesServise.clear()
        NotesServise.add("заголовок", "заметка")
        NotesServise.restoreComment(1)

    }
}