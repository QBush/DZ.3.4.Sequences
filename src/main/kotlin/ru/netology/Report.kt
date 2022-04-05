package ru.netology

class Report (val commentId: Int, reason: Int) {
    val reason = if (reason in 1..8) reason else throw ReportsReasonNumberException()
}


