package com.example.ui.screens

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DevotionalContent
import com.example.model.JapaMantra
import com.example.ui.components.MalaRingVisualizer
import com.example.ui.theme.MarigoldAmber
import com.example.ui.theme.SaffronLight
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.TempleGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JapaMalaScreen(
    count: Int,
    rounds: Int,
    lifetimeChants: Int,
    selectedMantra: JapaMantra,
    hapticEnabled: Boolean,
    isCelebration: Boolean,
    onIncrement: () -> Unit,
    onResetRound: () -> Unit,
    onSelectMantra: (JapaMantra) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isMantraMenuExpanded by remember { mutableStateOf(false) }

    fun triggerVibration() {
        if (!hapticEnabled) return
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vibratorManager?.defaultVibrator?.vibrate(
                    VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE)
                )
            } else {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator?.vibrate(30)
                }
            }
        } catch (_: Exception) {
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("japa_mala_screen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mantra Selector Dropdown
        ExposedDropdownMenuBox(
            expanded = isMantraMenuExpanded,
            onExpandedChange = { isMantraMenuExpanded = !isMantraMenuExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedMantra.name,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selected Japa Mantra") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMantraMenuExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .testTag("mantra_selector_dropdown"),
                shape = RoundedCornerShape(14.dp)
            )

            ExposedDropdownMenu(
                expanded = isMantraMenuExpanded,
                onDismissRequest = { isMantraMenuExpanded = false }
            ) {
                DevotionalContent.JAPA_MANTRAS.forEach { mantra ->
                    DropdownMenuItem(
                        text = {
                            Column {
                                Text(text = mantra.name, fontWeight = FontWeight.Bold)
                                Text(
                                    text = mantra.devanagari,
                                    fontSize = 12.sp,
                                    color = SaffronPrimary
                                )
                            }
                        },
                        onClick = {
                            onSelectMantra(mantra)
                            isMantraMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Selected Mantra Display Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("selected_mantra_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = selectedMantra.devanagari,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = selectedMantra.transliteration,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 108 Mala Ring Visualizer
        MalaRingVisualizer(
            count = count,
            rounds = rounds,
            totalCount = lifetimeChants,
            isCelebration = isCelebration,
            onBeadTap = {
                triggerVibration()
                onIncrement()
            }
        )

        // Celebration banner on 108 milestone
        AnimatedVisibility(
            visible = isCelebration,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(TempleGold.copy(alpha = 0.2f))
                    .border(1.dp, TempleGold, RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Celebration,
                        contentDescription = null,
                        tint = SaffronPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "108 Japa Round Completed! May Ganesha bless you.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = SaffronPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Statistics and Round Reset Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Lifetime Japa",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    Text(
                        text = "$lifetimeChants chants",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            OutlinedButton(
                onClick = onResetRound,
                modifier = Modifier.testTag("reset_round_button"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset Round",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Reset Round", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
