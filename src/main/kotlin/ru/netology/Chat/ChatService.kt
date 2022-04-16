package ru.netology.Chat

import ru.netology.*

object ChatService {
    private var chats = ArrayList<Chat>()

    fun getChats() = chats

    fun getUnreadChatsCount(userId: Int): Int {
        val usersChats = getChatByUserID(userId)
            .filter { it ->
                it.massages.any { !it.readed && it.recipientId == userId }
            }
            .size
        return usersChats
    }

    fun getChatByUserID(userId: Int): List<Chat> {
        val chats = chats
            .filter { it.UsersIdFromTo.toList().contains(userId) }
        if (!chats.any()) throw ChatIsNotFoundExeption()
        return chats
    }

    fun getChatByTwoUsers(firstId: Int, secondId: Int): Chat {
        return chats.find {
            it.UsersIdFromTo.toList().contains(firstId) && it.UsersIdFromTo.toList().contains(secondId)
        } ?: throw ChatIsNotFoundExeption()
    }


    fun getMassagesFromChat(userID: Int, chatId: Int, fromMassageId: Int, count: Int): List<Massage> {
        val chat = chats.find { it.chatId == chatId } ?: throw ChatIsNotFoundExeption()
        return chat.massages
            .dropWhile { it.massageId < fromMassageId }
            .filter { it.recipientId == userID }
            .onEach { it.readed = true }
            .take(count)
    }

    private fun createChat(from: Int, to: Int): Chat {
        val chat = Chat(Pair(from, to))
        println("Создан новый чат")
        chats.add(chat)
        return chat
    }

    fun createMassage(from: Int, to: Int, text: String): Massage {
        val chat = try {
            getChatByTwoUsers(from, to)
        } catch (e: ChatIsNotFoundExeption) {
            createChat(from, to)
        }
        val massage = Massage(from, to, text)

        chat.massages.add(massage)

//        chats.forEachIndexed { index, it ->
//            if (it.chatId == chat.chatId) {
//                chats[index] = chat.copy(massages = (chat.massages + massage) as ArrayList<Massage>)
//                return@forEachIndexed
//            }
//        }
        return massage
    }

    fun deleteChat(chatId: Int): Boolean {
        return chats.removeIf { it.chatId == chatId }
    }

    fun deleteMassage(id: Int): Boolean {
        val chat = chats
            .firstOrNull { chat ->
                chat.massages.any { it.massageId == id }
            } ?: throw MassageIsNotFoundExeption()
        chat.massages.removeIf { it.massageId == id }
        if (chat.massages.isEmpty()) chats.remove(chat)
        return true
    }

    fun clear() {
        chats.clear()
        massagesCount = 0
        chatsCount = 0
    }

}


