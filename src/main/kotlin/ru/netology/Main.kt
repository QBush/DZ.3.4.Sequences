package ru.netology


fun main() {

//    val video1: Video? = null
//    val audio1: Audio? = null
//    val link1: Link? = null
//    val note1: Note? = null
//    val photo1: Photo? = null
//
//    val attachment1: MutableList<Attachments> = mutableListOf(
//        VideoAttachment(video = video1),
//        AudioAttachment(audio = audio1),
//        LinkAttachment(link = link1),
//        NoteAttachment(note = note1),
//        PhotoAttachment(photo = photo1)
//    )
//
//
//    WallServise.addPost(
//        Post(
//            ownerId = 2, fromId = 3, createdBy = 5, date = 25, text = "text", replyOwnerId = 2,
//            replyPostId = 24, friendsOnly = true, comments = null, copyright = null,
//            likes = null, reposts = null, postType = "postType", signerId = 6, canPin = true,
//            canDelete = true, canEdit = true, isPinned = true, markedAsAds = true, isFavorite = true,
//            donut = null, postponedId = "postponedId", postSource = null, geo = null, copyHistory = null,
//            attachment = attachment1
//        )
//    )
//
//    WallServise.addPost(
//        Post(
//            ownerId = 2, fromId = 3, createdBy = 5, date = 25, text = "text", replyOwnerId = 2,
//            replyPostId = 24, friendsOnly = true, comments = null, copyright = null,
//            likes = null, reposts = null, postType = "postType", signerId = 6, canPin = true,
//            canDelete = true, canEdit = true, isPinned = true, markedAsAds = true, isFavorite = true,
//            donut = null, postponedId = "postponedId", postSource = null, geo = null, copyHistory = null,
//            attachment = attachment1
//        )
//    )
    println(NotesServise.add("123", "321"))
    println(NotesServise.add("123", "321"))
    println(NotesServise.add("123", "321"))

    println(NotesServise.createComment(1, "раз, два, три"))
    println(NotesServise.editComment(1, "100 101 102"))
    println(NotesServise.edit(1, "one two free"))
    println(NotesServise.delete(1))
    println(NotesServise.deleteComment(1))

    for (note in NotesServise.notes) {
        println(note)
    }

}
