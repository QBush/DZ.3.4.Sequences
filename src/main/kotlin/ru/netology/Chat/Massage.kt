package ru.netology.Chat

internal var massagesCount = 0

data class Massage(
    val senderId: Int,
    val recipientId: Int,
    val text: String,
    var readed: Boolean = false
) {
    val massageId = ++massagesCount

    override fun equals(other: Any?) = other is Massage
            && massageId == other.massageId
            && senderId == other.senderId
            && recipientId == other.recipientId

    override fun hashCode(): Int {
        return massageId.hashCode() * senderId.hashCode() * recipientId.hashCode()
    }

    override fun toString(): String {
        return """
            Сообщение: Id: $massageId, Отправитель: $senderId и $recipientId, Текст: $text
        """.trimIndent()
    }
}

