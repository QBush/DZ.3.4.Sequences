package ru.netology

import ru.netology.attachment.*
import kotlin.collections.*

object NotesServise {
    var notes: MutableSet<Note> = HashSet()
    var comments: MutableSet<Comment> = HashSet()
    // TODO сделать метод, который быстро ищет в HashSet
    // TODO Сделать переменные в отдельном классе от 0 до 3-х


    fun add(title: String, text: String, privacy: Int = 3, commentPrivacy: Int = 3): Int {
        if ((privacy !in 0..3) || (commentPrivacy !in 0..3)) throw NotePrivacyNumberException()
        val note = Note(title = title, text = text, privacy = privacy, commentPrivacy = commentPrivacy)
        notes += note
        return note.noteId
    }

    fun createComment(noteId: Int, message: String, replyTo: Int = 0, ownerId: Int = 0): Int {
        val comment = Comment(text = message, noteId = noteId)
        if (getById(noteId).isDeleted) throw NoteIsNotFoundExeption()
        comments.add(comment)
        return comment.commentId
    }


    fun delete(noteId: Int): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                if (!note.isDeleted) {
                    note.isDeleted = true
                    return true
                }
            }
        }
        throw NoteIsNotFoundExeption()
    }

    fun deleteComment(commentId: Int): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId) {
                if (!comment.isDeleted) {
                    comment.isDeleted = true
                    return true
                }
            }
        }
        throw CommentIsNotFoundExeption()
    }

    fun edit(noteId: Int, title: String? = null, text: String? = null): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                if (title != null) note.title = title
                if (text != null) note.text = text
                return true
            }
        }
        throw NoteIsNotFoundExeption()
    }

    fun editComment(commentId: Int, massage: String, ownerId: Int = 0): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId) {
                comment.text = massage
                return true
            }
        }
        throw CommentIsNotFoundExeption()
    }

    // TODO реализовать сортировку по дате в отдельном методе
    // TODO сохранить интервал в отдельной переменной
    fun get(noteIds: String, count: Int = 20, sort: Int = 0, userId: Int = 0): MutableList<Note>? {
        if (count !in 1..100) throw CountExecption()
        var noteIdListString = noteIds.split(",")
        var noteIdListInt = MutableList(noteIdListString.size) {
            try {
                noteIdListString[it].toInt()
            } catch (e: NumberFormatException) {
                println("Неверный формат ввода ID, должны быть разделены только запятыми")
                return null
            }
        }
        var noteList = mutableListOf<Note>()
        while (noteIdListInt.isNotEmpty()) {
            for (note in notes) {
                if (note.noteId == noteIdListInt.last()) {
                    noteList.add(note)
                }
            }
            noteIdListInt.removeLast()
        }
        return noteList
    }


    fun getById(noteId: Int): Note {
        for (note in notes) {
            if (note.noteId == noteId) {
                return note
            }
        }
        throw NoteIsNotFoundExeption()
    }

    // TODO реализовать сортировку по дате в отдельном методе
    // TODO сохранить интервал в отдельной переменной
    fun getComments(noteId: Int, ownerID: Int, sort: Int = 0, count: Int = 20): List<Comment> {
        if (count !in 1..100) throw CountExecption()
        val commentsList = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment.noteId == noteId && !comment.isDeleted) commentsList += comment
        }
        return commentsList
    }

    fun restoreComment(commentId: Int, ownerId: Int = 0): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId) {
                if (comment.isDeleted) {
                    comment.isDeleted = false
                    return true
                }
            }
        }
        return false
    }
}