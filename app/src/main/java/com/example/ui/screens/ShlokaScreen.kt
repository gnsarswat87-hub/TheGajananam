package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DevotionalContent
import com.example.ui.components.WordBreakdownCard
import com.example.ui.theme.MarigoldAmber
import com.example.ui.theme.SaffronLight
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.TempleGold
import com.example.ui.theme.TempleGoldLight

@Composable
fun ShlokaScreen(
    isPlayingAudio: Boolean,
    onToggleAudio: () -> Unit,
    onNavigateToJapa: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("shloka_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            // Sacred Sanskrit Mantra Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("sacred_shloka_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header ornament
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "✦", color = TempleGold, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "॥ गजाननं भूतगणादिसेवितम् ॥",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = SaffronPrimary,
                                letterSpacing = 1.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "✦", color = TempleGold, fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Devanagari Shloka Text
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            TempleGold.copy(alpha = 0.4f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = DevotionalContent.GAJANANAM_SHLOKA_SANSKRIT,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 36.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // English Transliteration
                    Text(
                        text = DevotionalContent.GAJANANAM_TRANSLITERATION,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Audio Chanting and Japa CTA buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onToggleAudio,
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("shloka_audio_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPlayingAudio) SaffronPrimary else MaterialTheme.colorScheme.primaryContainer,
                                contentColor = if (isPlayingAudio) Color.White else MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ) {
                            Icon(
                                imageVector = if (isPlayingAudio) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlayingAudio) "Pause Chant" else "Play Chant Loop",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isPlayingAudio) "Chanting..." else "Listen Chant",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            )
                        }

                        OutlinedButton(
                            onClick = onNavigateToJapa,
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("shloka_japa_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.SelfImprovement,
                                contentDescription = "Start Japa",
                                modifier = Modifier.size(20.dp),
                                tint = SaffronPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "108 Japa",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = SaffronPrimary
                            )
                        }
                    }
                }
            }
        }

        item {
            // Full Meaning Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("shloka_meaning_card"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = TempleGold,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Poetic English Translation",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = DevotionalContent.GAJANANAM_MEANING,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 22.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Word-by-Word Breakdown (Padacheda)",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }

        // List of 6 word breakdowns
        itemsIndexed(DevotionalContent.GAJANANAM_WORDS) { index, word ->
            WordBreakdownCard(word = word, index = index)
        }

        item {
            // Spiritual Merits Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .testTag("spiritual_merits_card"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "Why Chant The Gajananam Shloka?",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = SaffronPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "• Removal of Obstacles: Lord Ganesha cleanses hindrances from your path before you embark on studies, work, or new ventures.\n" +
                                "• Dissolution of Grief (Śoka-Vināśa): Calms troubled minds, anxiety, and existential distress through divine grace.\n" +
                                "• Awakening Intellect (Buddhi): Elephant symbolism embodies boundless memory, intellect, and spiritual discrimination.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    }
}
