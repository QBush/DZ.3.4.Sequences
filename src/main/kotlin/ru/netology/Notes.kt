package ru.netology

import ru.netology.attachment.*
import kotlin.collections.*

class Notes(
    var notes: MutableSet<Note> = mutableSetOf(),

) {

    fun addNote(title: String, text: String): Int {
        var note = Note(title = title, text = text)
        notes += note
        return note.noteId
    }

    fun createComment(noteId: Int, message: String): Int {
        var comment = Comment(text = message)
        for (note in notes) {
            if (note.noteId == noteId) {
                note.comments.add(comment)
            }
        }
        return comment.commentId
    }
}