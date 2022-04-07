package ru.netology.attachment

import ru.netology.Comment

private var noteCount = 0

data class Note(
    var title: String,
    var text: String,
    val privacy: Int = 3,
    val commentPrivacy: Int = 3,
    val date: Int = 20220407,
    var isDeleted: Boolean = false
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


