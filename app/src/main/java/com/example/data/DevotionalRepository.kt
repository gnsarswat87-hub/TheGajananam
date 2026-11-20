package com.example.data

import android.content.Context
import android.content.SharedPreferences

class DevotionalRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("the_gajananam_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_JAPA_COUNT = "key_japa_count"
        private const val KEY_JAPA_ROUNDS = "key_japa_rounds"
        private const val KEY_LIFETIME_CHANTS = "key_lifetime_chants"
        private const val KEY_FAVORITES = "key_favorites"
        private const val KEY_SELECTED_MANTRA = "key_selected_mantra"
        private const val KEY_SOUND_ENABLED = "key_sound_enabled"
        private const val KEY_HAPTIC_ENABLED = "key_haptic_enabled"
    }

    fun getJapaCount(): Int = prefs.getInt(KEY_JAPA_COUNT, 0)
    fun setJapaCount(count: Int) = prefs.edit().putInt(KEY_JAPA_COUNT, count).apply()

    fun getJapaRounds(): Int = prefs.getInt(KEY_JAPA_ROUNDS, 0)
    fun setJapaRounds(rounds: Int) = prefs.edit().putInt(KEY_JAPA_ROUNDS, rounds).apply()

    fun getLifetimeChants(): Int = prefs.getInt(KEY_LIFETIME_CHANTS, 0)
    fun setLifetimeChants(chants: Int) = prefs.edit().putInt(KEY_LIFETIME_CHANTS, chants).apply()

    fun getFavoriteIds(): Set<String> = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
    fun setFavoriteIds(ids: Set<String>) = prefs.edit().putStringSet(KEY_FAVORITES, ids).apply()

    fun getSelectedMantraId(): String = prefs.getString(KEY_SELECTED_MANTRA, "m1") ?: "m1"
    fun setSelectedMantraId(id: String) = prefs.edit().putString(KEY_SELECTED_MANTRA, id).apply()

    fun isSoundEnabled(): Boolean = prefs.getBoolean(KEY_SOUND_ENABLED, true)
    fun setSoundEnabled(enabled: Boolean) = prefs.edit().putBoolean(KEY_SOUND_ENABLED, enabled).apply()

    fun isHapticEnabled(): Boolean = prefs.getBoolean(KEY_HAPTIC_ENABLED, true)
    fun setHapticEnabled(enabled: Boolean) = prefs.edit().putBoolean(KEY_HAPTIC_ENABLED, enabled).apply()
}
