package ru.netology

class PostNotFoundException : RuntimeException("Такого поста нет или он был удален")

class CommentNotFoundException : RuntimeException("Такого комментария нет или он был удален")

class ReportsReasonNumberException : RuntimeException("Код причины жалобы не соответствует возможным вариантам")