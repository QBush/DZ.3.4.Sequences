package ru.netology

class PostNotFoundException : RuntimeException("Такого поста нет или он был удален")

class CommentNotFoundException : RuntimeException("Такого комментария нет или он был удален")

class NoteNotFoundException : RuntimeException("Такой заметки нет или она была удалена")

class ReportsReasonNumberException : RuntimeException("Код причины жалобы не соответствует возможным вариантам")

class NotePrivacyNumberException: RuntimeException("Код privacy в Note не соответствует допустимому значению")

class NoteIsNotFoundExeption: RuntimeException("Заметка отсутствует или была удалена")

class CommentIsNotFoundExeption: RuntimeException("Комментарий отсутствует или был удален")

class CountExecption: RuntimeException("Поле count введено некорректно, число должно быть положительным," +
         " максимальное значение 100")