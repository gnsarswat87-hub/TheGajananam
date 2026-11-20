package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.DevotionalAudioPlayer
import com.example.data.DevotionalContent
import com.example.data.DevotionalRepository
import com.example.model.JapaMantra
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

enum class AppTab(val title: String) {
    SHLOKA("Gajananam"),
    JAPA("Japa Mala"),
    STOTRAS("Stotras"),
    PUJA("Aarti & Puja")
}

data class DevotionalUiState(
    val currentTab: AppTab = AppTab.SHLOKA,
    val japaCount: Int = 0,
    val japaRounds: Int = 0,
    val lifetimeChants: Int = 0,
    val selectedMantra: JapaMantra = DevotionalContent.JAPA_MANTRAS.first(),
    val isPlayingAudioChant: Boolean = false,
    val isDiyaLit: Boolean = true,
    val flowerCount: Int = 0,
    val isBellRinging: Boolean = false,
    val favoriteIds: Set<String> = emptySet(),
    val soundEnabled: Boolean = true,
    val hapticEnabled: Boolean = true,
    val isRoundCompletedCelebration: Boolean = false
)

class GajananamViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DevotionalRepository(application.applicationContext)

    private val _uiState = MutableStateFlow(
        DevotionalUiState(
            japaCount = repository.getJapaCount(),
            japaRounds = repository.getJapaRounds(),
            lifetimeChants = repository.getLifetimeChants(),
            selectedMantra = DevotionalContent.JAPA_MANTRAS.find { it.id == repository.getSelectedMantraId() }
                ?: DevotionalContent.JAPA_MANTRAS.first(),
            favoriteIds = repository.getFavoriteIds(),
            soundEnabled = repository.isSoundEnabled(),
            hapticEnabled = repository.isHapticEnabled()
        )
    )
    val uiState: StateFlow<DevotionalUiState> = _uiState.asStateFlow()

    private var chantJob: Job? = null

    fun setTab(tab: AppTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun selectMantra(mantra: JapaMantra) {
        _uiState.update { it.copy(selectedMantra = mantra) }
        repository.setSelectedMantraId(mantra.id)
    }

    fun incrementJapa() {
        val currentCount = _uiState.value.japaCount
        val newCount = currentCount + 1
        val newLifetime = _uiState.value.lifetimeChants + 1

        if (newCount >= 108) {
            val newRounds = _uiState.value.japaRounds + 1
            _uiState.update {
                it.copy(
                    japaCount = 0,
                    japaRounds = newRounds,
                    lifetimeChants = newLifetime,
                    isRoundCompletedCelebration = true
                )
            }
            repository.setJapaCount(0)
            repository.setJapaRounds(newRounds)
            repository.setLifetimeChants(newLifetime)

            if (_uiState.value.soundEnabled) {
                viewModelScope.launch {
                    DevotionalAudioPlayer.playTempleBell()
                }
            }

            viewModelScope.launch {
                delay(2500)
                _uiState.update { it.copy(isRoundCompletedCelebration = false) }
            }
        } else {
            _uiState.update {
                it.copy(
                    japaCount = newCount,
                    lifetimeChants = newLifetime
                )
            }
            repository.setJapaCount(newCount)
            repository.setLifetimeChants(newLifetime)
        }
    }

    fun resetJapaCurrentRound() {
        _uiState.update { it.copy(japaCount = 0) }
        repository.setJapaCount(0)
    }

    fun toggleFavorite(stotraId: String) {
        val current = _uiState.value.favoriteIds.toMutableSet()
        if (current.contains(stotraId)) {
            current.remove(stotraId)
        } else {
            current.add(stotraId)
        }
        _uiState.update { it.copy(favoriteIds = current) }
        repository.setFavoriteIds(current)
    }

    fun toggleDiya() {
        _uiState.update { it.copy(isDiyaLit = !it.isDiyaLit) }
    }

    fun offerFlower() {
        _uiState.update { it.copy(flowerCount = it.flowerCount + 1) }
    }

    fun ringTempleBell() {
        _uiState.update { it.copy(isBellRinging = true) }
        if (_uiState.value.soundEnabled) {
            viewModelScope.launch {
                DevotionalAudioPlayer.playTempleBell()
            }
        }
        viewModelScope.launch {
            delay(1200)
            _uiState.update { it.copy(isBellRinging = false) }
        }
    }

    fun blowSacredShankh() {
        if (_uiState.value.soundEnabled) {
            viewModelScope.launch {
                DevotionalAudioPlayer.playShankh()
            }
        }
    }

    fun toggleChantLoop() {
        val current = _uiState.value.isPlayingAudioChant
        if (current) {
            chantJob?.cancel()
            _uiState.update { it.copy(isPlayingAudioChant = false) }
        } else {
            _uiState.update { it.copy(isPlayingAudioChant = true) }
            chantJob = viewModelScope.launch {
                while (isActive) {
                    DevotionalAudioPlayer.playChantTone()
                    delay(2200)
                }
            }
        }
    }

    fun toggleSound() {
        val newValue = !_uiState.value.soundEnabled
        _uiState.update { it.copy(soundEnabled = newValue) }
        repository.setSoundEnabled(newValue)
    }

    fun toggleHaptic() {
        val newValue = !_uiState.value.hapticEnabled
        _uiState.update { it.copy(hapticEnabled = newValue) }
        repository.setHapticEnabled(newValue)
    }

    override fun onCleared() {
        super.onCleared()
        chantJob?.cancel()
    }
}
