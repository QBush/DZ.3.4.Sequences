package ru.netology

import ru.netology.Different.*
import ru.netology.attachment.Attachments

data class Post(
    var id: Int = 0,
    val ownerId: Int,
    val fromId: Int, // от чьего имени опубликована запись
    val createdBy: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comments: Comments?,
    val copyright: Copyright?,
    val likes: Likes?,
    val reposts: Reposts?,
    val postType: String,
    val postSource: PostSource?,
    val geo: Geo?,
    val attachment: MutableList<Attachments>?,
    val signerId: Int,
    val copyHistory: CopyHistory?,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Donut?,
    val postponedId: String,
    ) {

    override fun equals(other: Any?) = other is Post && (id == other.id)
}