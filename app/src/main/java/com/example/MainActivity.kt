package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AppTab
import com.example.ui.GajananamViewModel
import com.example.ui.components.GajananamHeader
import com.example.ui.screens.JapaMalaScreen
import com.example.ui.screens.PujaScreen
import com.example.ui.screens.ShlokaScreen
import com.example.ui.screens.StotrasScreen
import com.example.ui.theme.SaffronLight
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.TheGajananamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGajananamTheme {
                TheGajananamApp()
            }
        }
    }
}

@Composable
fun TheGajananamApp(
    viewModel: GajananamViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("the_gajananam_scaffold"),
        topBar = {
            GajananamHeader(
                soundEnabled = uiState.soundEnabled,
                hapticEnabled = uiState.hapticEnabled,
                onToggleSound = { viewModel.toggleSound() },
                onToggleHaptic = { viewModel.toggleHaptic() },
                onBellClick = { viewModel.ringTempleBell() }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .testTag("bottom_nav_bar"),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = uiState.currentTab == AppTab.SHLOKA,
                    onClick = { viewModel.setTab(AppTab.SHLOKA) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AutoStories,
                            contentDescription = "Gajananam Shloka"
                        )
                    },
                    label = { Text("Shloka") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SaffronPrimary,
                        selectedTextColor = SaffronPrimary,
                        indicatorColor = SaffronLight.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.testTag("nav_tab_shloka")
                )

                NavigationBarItem(
                    selected = uiState.currentTab == AppTab.JAPA,
                    onClick = { viewModel.setTab(AppTab.JAPA) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.SelfImprovement,
                            contentDescription = "Japa Mala"
                        )
                    },
                    label = { Text("108 Japa") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SaffronPrimary,
                        selectedTextColor = SaffronPrimary,
                        indicatorColor = SaffronLight.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.testTag("nav_tab_japa")
                )

                NavigationBarItem(
                    selected = uiState.currentTab == AppTab.STOTRAS,
                    onClick = { viewModel.setTab(AppTab.STOTRAS) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LibraryBooks,
                            contentDescription = "Stotras & Mantras"
                        )
                    },
                    label = { Text("Stotras") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SaffronPrimary,
                        selectedTextColor = SaffronPrimary,
                        indicatorColor = SaffronLight.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.testTag("nav_tab_stotras")
                )

                NavigationBarItem(
                    selected = uiState.currentTab == AppTab.PUJA,
                    onClick = { viewModel.setTab(AppTab.PUJA) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Whatshot,
                            contentDescription = "Aarti & Puja"
                        )
                    },
                    label = { Text("Puja") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SaffronPrimary,
                        selectedTextColor = SaffronPrimary,
                        indicatorColor = SaffronLight.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.testTag("nav_tab_puja")
                )
            }
        }
    ) { innerPadding ->
        when (uiState.currentTab) {
            AppTab.SHLOKA -> ShlokaScreen(
                isPlayingAudio = uiState.isPlayingAudioChant,
                onToggleAudio = { viewModel.toggleChantLoop() },
                onNavigateToJapa = { viewModel.setTab(AppTab.JAPA) },
                modifier = Modifier.padding(innerPadding)
            )

            AppTab.JAPA -> JapaMalaScreen(
                count = uiState.japaCount,
                rounds = uiState.japaRounds,
                lifetimeChants = uiState.lifetimeChants,
                selectedMantra = uiState.selectedMantra,
                hapticEnabled = uiState.hapticEnabled,
                isCelebration = uiState.isRoundCompletedCelebration,
                onIncrement = { viewModel.incrementJapa() },
                onResetRound = { viewModel.resetJapaCurrentRound() },
                onSelectMantra = { viewModel.selectMantra(it) },
                modifier = Modifier.padding(innerPadding)
            )

            AppTab.STOTRAS -> StotrasScreen(
                favoriteIds = uiState.favoriteIds,
                onToggleFavorite = { viewModel.toggleFavorite(it) },
                modifier = Modifier.padding(innerPadding)
            )

            AppTab.PUJA -> PujaScreen(
                isDiyaLit = uiState.isDiyaLit,
                flowerCount = uiState.flowerCount,
                isBellRinging = uiState.isBellRinging,
                onToggleDiya = { viewModel.toggleDiya() },
                onOfferFlower = { viewModel.offerFlower() },
                onRingBell = { viewModel.ringTempleBell() },
                onBlowShankh = { viewModel.blowSacredShankh() },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
