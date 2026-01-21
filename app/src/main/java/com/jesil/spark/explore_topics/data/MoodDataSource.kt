package com.jesil.spark.explore_topics.data

import com.jesil.spark.explore_topics.domain.model.Mood

object MoodDataSource {
    val moods = listOf(
        Mood(1, "sad", "\uD83D\uDE14"),
        Mood(2, "Lonely", "\uD83D\uDE14"),
        Mood(3, "Tired", "\uD83D\uDE14"),
        Mood(4, "Depressed", "\uD83D\uDE14"),
        Mood(5, "Hopeless", "\uD83D\uDE14"),
        Mood(6, "Burnt out", "\uD83D\uDE14"),
        Mood(7, "Angry", "\uD83D\uDE24"),
        Mood(8, "Furious", "\uD83D\uDE24"),
        Mood(9, "Frustrated", "\uD83D\uDE24"),
        Mood(10, "Anxious", "\uD83D\uDE24"),
        Mood(11, "Stressed", "\uD83D\uDE24"),
        Mood(12, "Overwhelmed", "\uD83D\uDE24"),
        Mood(13, "Focused", "\uD83E\uDDE0"),
        Mood(14, "Motivated", "\uD83E\uDDE0"),
        Mood(15, "Disciplined", "\uD83E\uDDE0"),
        Mood(16, "Curious", "\uD83E\uDDE0"),
        Mood(17, "Inspired", "\uD83E\uDDE0"),
        Mood(18, "Happy", "\uD83D\uDE04"),
        Mood(19, "Excited", "\uD83D\uDE04"),
        Mood(20, "Grateful", "\uD83D\uDE04"),
        Mood(21, "Confident", "\uD83D\uDE04"),
        Mood(22, "Calm", "\uD83D\uDE04"),
        Mood(23, "Healing", "❤\uFE0F"),
        Mood(24, "Reflective", "❤\uFE0F"),
        Mood(25, "Peaceful", "❤\uFE0F"),
        Mood(26, "Harmonious", "❤\uFE0F"),
        Mood(27, "Forgiving", "❤\uFE0F"),
        Mood(28, "Loving", "❤\uFE0F"),
        Mood(28, "Hopeful", "❤\uFE0F")

    ).shuffled()
}