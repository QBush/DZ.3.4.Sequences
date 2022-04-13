package ru.netology

import ru.netology.Different.Placeholder
import ru.netology.attachment.*

internal var commentCount = 0

data class Comment(
    val fromId: Int = 0,
    val date: Int = 0,
    var text: String? = null,
    val donut: String? = null,
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: Attachments? = null,
    var isDeleted: Boolean = false,
    val noteId: Int = 0
) {
    val commentId: Int = ++commentCount

    override fun equals(other: Any?) = other is Comment && (commentId == other.commentId)

    override fun hashCode(): Int {
        return commentId.hashCode()
    }

    override fun toString(): String {
        return "Комментарий: Id: $commentId, text: $text"
    }
}





