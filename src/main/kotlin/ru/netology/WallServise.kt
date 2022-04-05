package ru.netology

import ru.netology.attachment.*


object WallServise {
    private var posts = mutableListOf<Post>()
    private var comments = mutableListOf<Comment>()
    private var commentsReports = mutableListOf<Report>()
    private var currentPostID = 0


    fun addPost(post: Post): Post {
        currentPostID++
        val post1 = post.copy(id = currentPostID)
        posts += post1
        return posts.last()
    }

    fun findpostById(postId: Int): Post? {
        for (post in posts) {
            if (post.id == postId) {
                return post
            }
        }
        return null
    }

    fun createComment(comment: Comment, postId: Int): Boolean {
        if (findpostById(postId) != null) {
            comments += comment
            return true
        }
        throw PostNotFoundException()
    }

    fun reportComment(commentId: Int, reason: Int): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentId) {
                commentsReports += Report(commentId, reason)
                return true
            }
        }
        throw CommentNotFoundException()
    }

    fun update(currentPost: Post): Boolean {
        for (i in 1..posts.size) {
            if (posts[i].id == currentPost.id) {
                posts[i] = currentPost.copy(id = posts[i].id, date = posts[i].date)
                return true
            }
        }
        return false
    }

    fun clear() {
        posts.clear()
        currentPostID = 0
    }
}