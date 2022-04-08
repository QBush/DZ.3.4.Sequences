package ru.netology

import ru.netology.attachment.*
import java.util.*
import kotlin.collections.*

val COUNT_VIEW = 1..100

object NotesServise {
    var notes: MutableSet<Note> = TreeSet()
    var comments: MutableSet<Comment> = TreeSet()

    fun add(title: String, text: String): Int {
        val note = Note(title = title, text = text)
        notes += note
        return note.noteId
    }

    fun createComment(noteId: Int, message: String, replyTo: Int = 0, ownerId: Int = 0): Int {
        val comment = Comment(text = message, noteId = noteId)
        if (getById(noteId).isDeleted) throw NoteIsNotFoundExeption()
        comments.add(comment)
        (getById(noteId).commentsCount)++
        return comment.commentId
    }


    fun delete(noteId: Int): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                if (note.isDeleted) if (note.isDeleted) throw NoteIsNotFoundExeption()
                note.isDeleted = true
                return true
            }
        }
        throw NoteIsNotFoundExeption()
    }


    fun deleteComment(commentId: Int, ownerId: Int = 0): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId) {
                if (comment.isDeleted) throw CommentIsNotFoundExeption()
                comment.isDeleted = true
                return true
            }
        }
        throw CommentIsNotFoundExeption()
    }


    fun edit(noteId: Int, title: String? = null, text: String? = null): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                if (note.isDeleted) if (note.isDeleted) throw NoteIsNotFoundExeption()
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
                if (comment.isDeleted) throw CommentIsNotFoundExeption()
                comment.text = massage
                return true
            }
        }
        throw CommentIsNotFoundExeption()
    }

    fun get(noteIds: String, count: Int = 20, sort: Int = 1, userId: Int = 0): List<Note>? {
        if (count !in COUNT_VIEW) throw CountExeption()
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
        if (sort == 0) {
            return (noteList.sortedBy { it.date })
        } else {
            return (noteList.sortedByDescending { it.date })
        }

    }

    fun getById(noteId: Int, ownerId: Int = 0): Note {
        for (note in notes) {
            if (note.noteId == noteId) {
                return note
            }
        }
        throw NoteIsNotFoundExeption()
    }

    fun getComments(noteId: Int, ownerID: Int, sort: Int = 1, count: Int = 20): List<Comment> {
        if (count !in COUNT_VIEW) throw CountExeption()
        val commentsList = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment.noteId == noteId && !comment.isDeleted) commentsList += comment
        }
        if (sort == 0) {
            return (commentsList.sortedBy { it.date })
        } else {
            return (commentsList.sortedByDescending { it.date })
        }
    }

    fun restoreComment(commentId: Int, ownerId: Int = 0): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId) {
                if (comment.isDeleted) {
                    comment.isDeleted = false
                    return true
                } else {
                    println("Нельзя возродить то, что еще живо (Comment)")
                    return false
                }
            }
        }
        throw CommentIsNotFoundExeption()
    }

    fun clear() {
        notes.clear()
        comments.clear()
    }

}