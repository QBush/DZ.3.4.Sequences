package ru.netology.attachment

import ru.netology.Comment

private var noteCount = 0

data class Note(
    var title: String,
    var text: String,
    var date: Int = 20220407,
    var isDeleted: Boolean = false,
    var commentsCount: Int = 0
) {

    val noteId: Int = ++noteCount

    override fun equals(other: Any?) = other is Note && (this.noteId == other.noteId)

    override fun hashCode(): Int {
        return noteId.hashCode()
    }

    override fun toString(): String {
        return "Заметка: Id: $noteId, title: $title, text: $text"
    }
}


