package ru.netology.Chat

import ru.netology.Comment

internal var chatsCount = 0

data class Chat (
    val UsersIdFromTo : Pair<Int,Int>,
    var massages : ArrayList<Massage> = ArrayList(),

    ){
    val chatId: Int = ++chatsCount

    override fun equals(other: Any?) = other is Chat && (chatId == other.chatId)

    override fun hashCode(): Int {
        return chatId.hashCode()
    }

    override fun toString(): String {
        return "Чат: Id: $chatId, Между пользователями: id: ${UsersIdFromTo.first} и id: ${UsersIdFromTo.second}"
    }

}


