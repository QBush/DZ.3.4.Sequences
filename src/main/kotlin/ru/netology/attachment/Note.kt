package ru.netology.attachment

import ru.netology.Comment

private var noteCount = 0

data class Note(
    var title: String,
    var text: String,
    var comments: MutableList<Comment> = mutableListOf(),
    val isDeleted: Boolean = false
) {
    val noteId: Int = ++noteCount
}