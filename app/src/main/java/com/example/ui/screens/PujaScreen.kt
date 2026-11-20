package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.DiyaGlow
import com.example.ui.theme.LotusPink
import com.example.ui.theme.MarigoldAmber
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.TempleGold
import com.example.ui.theme.TempleGoldLight

@Composable
fun PujaScreen(
    isDiyaLit: Boolean,
    flowerCount: Int,
    isBellRinging: Boolean,
    onToggleDiya: () -> Unit,
    onOfferFlower: () -> Unit,
    onRingBell: () -> Unit,
    onBlowShankh: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Bell swing rotation animation
    val bellRotation = remember { Animatable(0f) }
    LaunchedEffect(isBellRinging) {
        if (isBellRinging) {
            bellRotation.animateTo(
                targetValue = 20f,
                animationSpec = tween(150, easing = LinearEasing)
            )
            bellRotation.animateTo(
                targetValue = -20f,
                animationSpec = tween(150, easing = LinearEasing)
            )
            bellRotation.animateTo(
                targetValue = 10f,
                animationSpec = tween(120, easing = LinearEasing)
            )
            bellRotation.animateTo(
                targetValue = 0f,
                animationSpec = tween(100, easing = LinearEasing)
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("puja_screen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Divine Shrine Mandir Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("sacred_mandir_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "॥ श्री गणेशाय नमः ॥",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = SaffronPrimary,
                        letterSpacing = 1.sp
                    )
                )
                Text(
                    text = "Virtual Darshan & Puja Mandir",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Deity Shrine Frame
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            2.dp,
                            Brush.linearGradient(listOf(TempleGold, SaffronPrimary, TempleGoldLight)),
                            RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_hero_ganesha),
                        contentDescription = "Lord Ganesha in Mandir",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Warm Diya Lighting Ambient Glow
                    if (isDiyaLit) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            DiyaGlow.copy(alpha = 0.25f),
                                            Color.Transparent
                                        ),
                                        radius = 350f
                                    )
                                )
                        )
                    }

                    // Bottom altar bar with offered flowers and diya
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(Color.Black.copy(alpha = 0.65f))
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocalFlorist,
                                contentDescription = null,
                                tint = LotusPink,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "$flowerCount Flowers Offered",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Whatshot,
                                contentDescription = null,
                                tint = if (isDiyaLit) DiyaGlow else Color.Gray,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isDiyaLit) "Diya Glowing" else "Diya Unlit",
                                color = if (isDiyaLit) TempleGoldLight else Color.LightGray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Sacred Puja Actions Grid
        Text(
            text = "Sacred Offerings & Seva",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Row 1: Flower Offering & Diya Light
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onOfferFlower,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .testTag("puja_offer_flower_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LotusPink.copy(alpha = 0.9f)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.LocalFlorist,
                    contentDescription = "Offer Flower",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Offer Pushpa", fontWeight = FontWeight.SemiBold)
            }

            FilledTonalButton(
                onClick = onToggleDiya,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .testTag("puja_toggle_diya_button"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Whatshot,
                    contentDescription = "Light Diya",
                    tint = if (isDiyaLit) MarigoldAmber else MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isDiyaLit) "Extinguish" else "Light Deepam",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 2: Temple Bell & Shankh
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onRingBell,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .testTag("puja_ring_bell_button"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Ring Bell",
                    tint = TempleGold,
                    modifier = Modifier
                        .size(22.dp)
                        .rotate(bellRotation.value)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Ring Ghanta", fontWeight = FontWeight.SemiBold)
            }

            OutlinedButton(
                onClick = onBlowShankh,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .testTag("puja_blow_shankh_button"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.GraphicEq,
                    contentDescription = "Blow Shankh",
                    tint = SaffronPrimary,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Blow Shankh", fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Daily Devotional Sankalpa Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("daily_sankalpa_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
            ),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Daily Morning & Evening Sankalpa",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = SaffronPrimary
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "“May Lord Gajananam, the radiant jewel of Mount Kailash and beloved son of Uma, reside forever in our hearts, dispelling shadows of ignorance, doubt, and sorrow, and granting all noble aspirations.”",
                    style = MaterialTheme.typography.bodySmall.copy(
                        lineHeight = 20.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
