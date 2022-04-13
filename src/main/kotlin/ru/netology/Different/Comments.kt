package ru.netology.Different

class Comments(
    var count: Int,
    val canPost: Boolean,
    val groupsCanPost: Boolean,
    val canClose: Boolean,
    val canOpen: Boolean,
    var isDeleted: Boolean = false
) {

}