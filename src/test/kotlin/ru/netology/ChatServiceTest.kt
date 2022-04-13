package ru.netology

import org.junit.Assert.*
import org.junit.Test

import ru.netology.Chat.*


class ChatServiceTest {

    @Test
    fun getUnreadChatsCountTest() {
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст1")
        ChatService.createMassage(2, 3, "Текст2")
        ChatService.createMassage(3, 2, "Текст3")
        assertEquals(1, ChatService.getUnreadChatsCount(3))
        assertEquals(2, ChatService.getUnreadChatsCount(2))
        assertEquals(0, ChatService.getUnreadChatsCount(1))
    }

    @Test
    fun getChatsByUserIdTest() {
        ChatService.clear()
        val chat1 = Chat(Pair(1, 2), arrayListOf(Massage(1, 2, "Текст1")))
        val chat2 = Chat(Pair(2, 3), arrayListOf(Massage(2, 3, "Текст2")))
        val chat3 = Chat(Pair(3, 1), arrayListOf(Massage(3, 1, "Текст3")))
        var expected1 = listOf(chat2, chat3)
        var expected2 = listOf(chat1, chat3)
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст1")
        ChatService.createMassage(2, 3, "Текст2")
        ChatService.createMassage(3, 1, "Текст3")
        val result1 = ChatService.getChatsByUserID(3)
        val result2 = ChatService.getChatsByUserID(1)
        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test(expected = ChatIsNotFoundExeption::class)
    fun getChatsByUserIdChatIsNotFoundTest() {
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст1")
        ChatService.getChatsByUserID(3)
    }

    @Test
    fun getChatByTwoUsersTest() {
        ChatService.clear()
        val expected = Chat(Pair(1, 2), arrayListOf(Massage(1, 2, "Текст1")))
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст")
        assertEquals(expected, ChatService.getChatByTwoUsers(2, 1))
        assertEquals(expected, ChatService.getChatByTwoUsers(1, 2))
    }

    @Test(expected = ChatIsNotFoundExeption::class)
    fun getChatByTwoUsersChatIsNotFoundTest() {
        ChatService.clear()
        val expected = Chat(Pair(1, 2), arrayListOf(Massage(1, 2, "Текст1")))
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст")
        assertEquals(expected, ChatService.getChatByTwoUsers(3, 1))
    }

    @Test
    fun getMassagesFromChatTest() {
        ChatService.clear()
        Massage(1, 2, "Текст1")
        Massage(1, 2, "Текст2")
        val expected = listOf(
            Massage(1, 2, "Текст3"),
            Massage(1, 2, "Текст4"),
            Massage(1, 2, "Текст5")
        )
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст1").readed = true
        ChatService.createMassage(1, 2, "Текст2").readed = true
        ChatService.createMassage(1, 2, "Текст3")
        ChatService.createMassage(1, 2, "Текст4")
        ChatService.createMassage(1, 2, "Текст5")
        val result1 = ChatService.getMassagesFromChat(2, 1, 3, 3)
        assertEquals(expected, result1)
        assertTrue(ChatService.getChats()[0].massages.all {it.readed})
    }

    @Test(expected = ChatIsNotFoundExeption::class)
    fun getMassagesFromChatIsNotFoundTest() {
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст1").readed = true
        ChatService.createMassage(1, 2, "Текст2").readed = true
        ChatService.createMassage(1, 2, "Текст3")
        ChatService.createMassage(1, 2, "Текст4")
        ChatService.createMassage(1, 2, "Текст5")
        ChatService.getMassagesFromChat(2, 2, 3, 3)
    }

    @Test
    fun createMassageWithoutChatTest() {
        ChatService.clear()
        val expected = Massage(1, 2, "Текст1")
        ChatService.clear()
        val result = ChatService.createMassage(1, 2, "Текст1")
        assertEquals(expected, result)
    }

    @Test
    fun createMassageWithChatTest() {
        ChatService.clear()
        val expected = Massage(1, 2, "Текст2")
        ChatService.clear()
        val result = ChatService.createMassage(1, 2, "Текст2")
        assertEquals(expected, result)
    }

    @Test
    fun deleteChatTest() {
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст1")
        assertTrue(ChatService.deleteChat(1))
        assertFalse(ChatService.deleteChat(2))
    }

    @Test
    fun deleteMassageTest() {
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст123")
        assertTrue(ChatService.deleteMassage(1))
    }

    @Test(expected = MassageIsNotFoundExeption::class)
    fun deleteMassageIsNotFoundTest() {
        ChatService.clear()
        ChatService.createMassage(1, 2, "Текст123")
        ChatService.deleteMassage(2)
    }
}