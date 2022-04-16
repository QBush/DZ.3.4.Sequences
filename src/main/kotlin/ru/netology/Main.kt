package ru.netology

import ru.netology.Chat.ChatService




fun main() {

    ChatService.clear()
    ChatService.createMassage(1, 2, "Текст1").readed = true
    println(ChatService.getChats())
    ChatService.createMassage(1, 2, "Текст2").readed = true
    println(ChatService.getChats())
    ChatService.createMassage(1, 2, "Текст3")
    ChatService.createMassage(1, 2, "Текст4")
    ChatService.createMassage(1, 2, "Текст5")
    println(ChatService.getChats())
    for(chat in ChatService.getChats()) {println(chat.massages)}
    val result1 = ChatService.getMassagesFromChat(2, 1, 3, 3)

}
