package ru.netology.Chat

import ru.netology.*

object ChatService {
    private var chats = ArrayList<Chat>()

    fun getChats() = chats

    fun getUnreadChatsCount(userId: Int): Int {
        val usersChats = getChatsByUserID(userId)
        val unreadChatsCount = usersChats.count { it ->
            it.massages.any {
                !it.readed && it.recipientId == userId
            }
        }
        return unreadChatsCount
    }

    fun getChatsByUserID(userId: Int): List<Chat> {
        val chats = chats.filter { it.UsersIdFromTo.toList().contains(userId) }
        if (!chats.any()) throw ChatIsNotFoundExeption()
        return chats
    }

    fun getChatByTwoUsers(firstId: Int, secondId: Int): Chat {
        val chat = chats.find {
            it.UsersIdFromTo.toList().contains(firstId) && it.UsersIdFromTo.toList().contains(secondId)
        } ?: throw ChatIsNotFoundExeption()
        return chat
    }


    fun getMassagesFromChat(userID: Int, chatId: Int, fromMassageId: Int, count: Int): List<Massage> {
        val chat = chats.find { it.chatId == chatId } ?: throw ChatIsNotFoundExeption()
        val unreadMassages = chat.massages
            .filter { it.massageId >= fromMassageId }
            .take(count)
        unreadMassages.filter { it.recipientId == userID }
            .forEach { it.readed = true }
        return unreadMassages
    }

    private fun createChat(from: Int, to: Int): Chat {
        try {
            return getChatByTwoUsers(from, to)
        } catch (e: ChatIsNotFoundExeption) {
            val chat = Chat(Pair(from, to))
            println("Создан новый чат")
            chats.add(chat)
            return chat
        }
    }


    fun createMassage(from: Int, to: Int, text: String): Massage {
        val chat = try {
            getChatByTwoUsers(from, to)
        } catch (e: ChatIsNotFoundExeption) {
            createChat(from, to)
        }
        val massage = Massage(from, to, text)
        chat.massages.add(massage)
        return massage
    }

    fun deleteChat(chatId: Int): Boolean {
        return chats.removeIf { it.chatId == chatId }
    }

    fun deleteMassage(id: Int): Boolean {
        val chat = try {
            chats.first {
                it.massages.any {
                    it.massageId == id
                }
            }
        } catch (e: NoSuchElementException) {
            throw MassageIsNotFoundExeption()
        }
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


