package ru.netology

import ru.netology.attachment.Attachments

private var commentCount = 0

class Comment(
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String? = null,
    val donut: String? = null,
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: Attachments? = null
) {
    val commentId: Int = ++commentCount
}
