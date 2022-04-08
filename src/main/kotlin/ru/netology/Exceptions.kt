package ru.netology

class PostNotFoundException : RuntimeException("Такого поста нет или он был удален")

class CommentNotFoundException : RuntimeException("Такого комментария нет или он был удален")

class NoteIsNotFoundExeption : RuntimeException("Такой заметки нет или она была удалена")

class ReportsReasonNumberException : RuntimeException("Код причины жалобы не соответствует возможным вариантам")

class CommentIsNotFoundExeption: RuntimeException("Комментарий отсутствует или был удален")

class CountExeption: RuntimeException("Поле count введено некорректно, число должно быть положительным," +
         " максимальное значение 100")