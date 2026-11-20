package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.MarigoldAmber
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.TempleGold
import com.example.ui.theme.TempleGoldLight

@Composable
fun GajananamHeader(
    soundEnabled: Boolean,
    hapticEnabled: Boolean,
    onToggleSound: () -> Unit,
    onToggleHaptic: () -> Unit,
    onBellClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("gajananam_header"),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Top action bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(SaffronPrimary, TempleGold)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ॐ",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "The Gajananam",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 0.5.sp
                            )
                        )
                        Text(
                            text = "गजाननं भूतगणादिसेवितम्",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBellClick,
                        modifier = Modifier
                            .size(40.dp)
                            .testTag("header_bell_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Ring Temple Bell",
                            tint = TempleGold
                        )
                    }

                    IconButton(
                        onClick = onToggleSound,
                        modifier = Modifier
                            .size(40.dp)
                            .testTag("header_sound_button")
                    ) {
                        Icon(
                            imageVector = if (soundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                            contentDescription = "Toggle Sound",
                            tint = if (soundEnabled) SaffronPrimary else MaterialTheme.colorScheme.outline
                        )
                    }

                    IconButton(
                        onClick = onToggleHaptic,
                        modifier = Modifier
                            .size(40.dp)
                            .testTag("header_haptic_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Vibration,
                            contentDescription = "Toggle Vibration",
                            tint = if (hapticEnabled) SaffronPrimary else MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }

            // Divine Hero Banner Artwork
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        1.dp,
                        Brush.linearGradient(listOf(TempleGold, SaffronPrimary)),
                        RoundedCornerShape(16.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_ganesha),
                    contentDescription = "Divine Lord Ganesha - Gajananam",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                // Atmospheric devotional gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.75f)
                                ),
                                startY = 40f
                            )
                        )
                )

                // Title overlay badge inside banner
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                ) {
                    Text(
                        text = "॥ श्री गणेशाय नमः ॥",
                        color = TempleGoldLight,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Remover of All Obstacles • Lord of Wisdom",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
