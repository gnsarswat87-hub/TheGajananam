package com.example.model

data class WordMeaning(
    val wordSanskrit: String,
    val wordEnglish: String,
    val meaning: String,
    val explanation: String
)

data class DevotionalItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val devanagari: String,
    val transliteration: String,
    val translation: String,
    val significance: String,
    val category: DevotionalCategory
)

enum class DevotionalCategory(val displayName: String) {
    SHLOKA("Shloka"),
    MANTRA("Mantra"),
    STOTRAM("Stotram"),
    AARTI("Aarti")
}

data class JapaMantra(
    val id: String,
    val name: String,
    val devanagari: String,
    val transliteration: String,
    val meaning: String
)
