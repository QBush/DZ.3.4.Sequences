package ru.netology

import org.junit.Assert.*
import org.junit.Test
import ru.netology.attachment.noteCount


class WallServiseTest {

    var post1 = Post(
        ownerId = 2, fromId = 3, createdBy = 5, date = 25, text = "text", replyOwnerId = 2,
        replyPostId = 24, friendsOnly = true, comments = null, copyright = null,
        likes = null, reposts = null, postType = "postType", signerId = 6, canPin = true,
        canDelete = true, canEdit = true, isPinned = true, markedAsAds = true, isFavorite = true,
        donut = null, postponedId = "postponedId", postSource = null, geo = null, copyHistory = null,
        attachment = null
    )
    var post2 = Post(
        ownerId = 2, fromId = 3, createdBy = 5, date = 25, text = "text", replyOwnerId = 2,
        replyPostId = 24, friendsOnly = true, comments = null, copyright = null,
        likes = null, reposts = null, postType = "postType", signerId = 6, canPin = true,
        canDelete = true, canEdit = true, isPinned = true, markedAsAds = true, isFavorite = true,
        donut = null, postponedId = "postponedId", postSource = null, geo = null, copyHistory = null,
        attachment = null
    )

    @Test
    fun addPostTest() {
        WallServise.clear()

        noteCount = 0
        val result = WallServise.addPost(post1)
        assertNotEquals(result.id, 0)

    }

    @Test
    fun updateTestTrue() {
        WallServise.clear()
        WallServise.addPost(post1)
        WallServise.addPost(post2)
        val result = WallServise.update(
            Post(
                id = 2,
                ownerId = 2, fromId = 3, createdBy = 5, date = 25, text = "text", replyOwnerId = 2,
                replyPostId = 24, friendsOnly = true, comments = null, copyright = null,
                likes = null, reposts = null, postType = "postType", signerId = 6, canPin = true,
                canDelete = true, canEdit = true, isPinned = true, markedAsAds = true, isFavorite = true,
                donut = null, postponedId = "postponedId", postSource = null, geo = null, copyHistory = null,
                attachment = null
            )
        )
        assertTrue(result)

    }

    @Test
    fun updateTestFalse() {
        WallServise.clear()
        WallServise.addPost(post1)
        WallServise.addPost(post2)
        val result = WallServise.update(
            Post(
                id = 3, ownerId = 2, fromId = 3, createdBy = 5, date = 25, text = "text", replyOwnerId = 2,
                replyPostId = 24, friendsOnly = true, comments = null, copyright = null,
                likes = null, reposts = null, postType = "postType", signerId = 6, canPin = true,
                canDelete = true, canEdit = true, isPinned = true, markedAsAds = true, isFavorite = true,
                donut = null, postponedId = "postponedId", postSource = null, geo = null, copyHistory = null,
                attachment = null
            )
        )
        assertFalse(result)
    }

    @Test
    fun createCommentTest() {
        WallServise.clear()
        WallServise.addPost(post1)
        var result = WallServise.createComment(Comment(1), 1)
        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentWithExceptionTest() {
        WallServise.clear()
        WallServise.addPost(post1)
        WallServise.createComment(Comment(1), 2)

    }

    @Test
    fun reportCommentTest() {
        WallServise.clear()
        WallServise.addPost(post1)
        WallServise.addPost(post2)
        WallServise.createComment(Comment(1), 1)
        WallServise.createComment(Comment(2), 2)
        var result = WallServise.reportComment(1, 6)
        assertTrue(result)
    }

    @Test(expected = CommentIsNotFoundExeption::class)
    fun reportCommentThereIsNoCommentTest() {
        WallServise.clear()
        WallServise.addPost(post1)
        WallServise.addPost(post2)
        WallServise.createComment(Comment(1), 1)
        WallServise.createComment(Comment(2), 2)
        WallServise.reportComment(3, 6)
    }

    @Test(expected = ReportsReasonNumberException::class)
    fun reportCommentThereIsNoReasonTest() {
        WallServise.clear()
        WallServise.addPost(post1)
        WallServise.addPost(post2)
        WallServise.createComment(Comment(1), 1)
        WallServise.createComment(Comment(2), 2)
        WallServise.reportComment(1, 9)
    }
}