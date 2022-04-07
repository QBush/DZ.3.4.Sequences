package ru.netology

import ru.netology.attachment.*
import kotlin.collections.*

object NotesServise {
    var notes: MutableSet<Note> = HashSet()
    var comments: MutableSet<Comment> = HashSet()
    // TODO переделать в Мапу массивы и производить поиск по ключу - id вместо перебора
    // TODO Сделать переменные в отдельном классе от 0 до 3-х


    fun add(title: String, text: String, privacy: Int = 3, commentPrivacy: Int = 3): Int {
        if ((privacy !in 0..3) || (commentPrivacy !in 0..3)) throw NotePrivacyNumberException()
        val note = Note(title = title, text = text, privacy = privacy, commentPrivacy = commentPrivacy)
        notes += note
        return note.noteId
    }

    fun createComment(noteId: Int, message: String, replyTo: Int = 0, ownerId: Int = 0): Int {
        val comment = Comment(text = message)
        for (note in notes) {
            if (note.noteId == noteId) {
                if (note.isDeleted) throw NoteIsNotFoundExeption()
                comments.add(comment)
                return comment.commentId
            }
        }
        throw NoteNotFoundException()
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
    fun get(noteIds: String, count: Int, sort: Int = 0, userId: Int = 0): MutableList<Note> {
        val noteIdListString = noteIds.split(",")
        var noteIdListInt = MutableList(noteIdListString.size) { noteIdListString[it].toInt() }
        var noteList = mutableListOf<Note>()
        while (!noteIdListInt.isEmpty())
            for (note in notes) {
                if (note.noteId == noteIdListInt.last()) {
                    noteIdListInt.removeLast()
                    noteList.add(note)
                }
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
}
