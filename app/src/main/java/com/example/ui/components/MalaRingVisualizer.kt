package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ChandanCard
import com.example.ui.theme.MarigoldAmber
import com.example.ui.theme.SaffronLight
import com.example.ui.theme.SaffronPrimary
import com.example.ui.theme.TempleGold
import com.example.ui.theme.TempleGoldDark
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun MalaRingVisualizer(
    count: Int,
    rounds: Int,
    totalCount: Int,
    isCelebration: Boolean,
    onBeadTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedProgress by animateFloatAsState(
        targetValue = count / 108f,
        animationSpec = spring(),
        label = "mala_progress"
    )

    val scaleAnim by animateFloatAsState(
        targetValue = if (isCelebration) 1.08f else 1f,
        animationSpec = spring(),
        label = "celebration_scale"
    )

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(280.dp)
            .scale(scaleAnim)
            .testTag("mala_counter_ring"),
        contentAlignment = Alignment.Center
    ) {
        // Beads Ring drawn on Canvas (showing 108 beads around circumference)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val radius = (size.minDimension / 2f) - 20.dp.toPx()

            // Connecting thread circle
            drawCircle(
                color = TempleGold.copy(alpha = 0.25f),
                radius = radius,
                center = center,
                style = Stroke(width = 2.dp.toPx())
            )

            // Draw 54 visible simplified bead indicators around circle
            val displayBeads = 54
            val currentBeadIndex = ((count.toFloat() / 108f) * displayBeads).toInt()

            for (i in 0 until displayBeads) {
                // Angle from -90 degrees (top / 12 o'clock)
                val angleDeg = (i * 360f / displayBeads) - 90f
                val angleRad = Math.toRadians(angleDeg.toDouble())
                val x = center.x + radius * cos(angleRad).toFloat()
                val y = center.y + radius * sin(angleRad).toFloat()

                val isMeru = (i == 0) // Guru / Meru bead at the top
                val isPassed = i <= currentBeadIndex && count > 0

                val beadColor = when {
                    isMeru -> TempleGoldDark
                    isPassed -> SaffronPrimary
                    else -> Color(0xFFD4A373).copy(alpha = 0.45f)
                }

                val beadRadius = when {
                    isMeru -> 6.5.dp.toPx()
                    isPassed -> 4.5.dp.toPx()
                    else -> 3.dp.toPx()
                }

                drawCircle(
                    color = beadColor,
                    radius = beadRadius,
                    center = Offset(x, y)
                )
            }
        }

        // Center interactive button to tap and increment Japa
        Box(
            modifier = Modifier
                .size(190.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                )
                .border(
                    width = 2.5.dp,
                    brush = Brush.sweepGradient(
                        listOf(TempleGold, SaffronPrimary, MarigoldAmber, TempleGold)
                    ),
                    shape = CircleShape
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple(bounded = true, color = SaffronLight),
                    onClick = onBeadTap
                )
                .testTag("tap_to_chant_button"),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "॥ ॐ ॥",
                    color = TempleGold,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "$count",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )

                Text(
                    text = "/ 108",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(SaffronPrimary.copy(alpha = 0.15f))
                        .border(1.dp, SaffronPrimary.copy(alpha = 0.4f), CircleShape)
                ) {
                    Text(
                        text = "Mala $rounds",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = SaffronPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Tap bead to chant",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 10.sp
                    )
                )
            }
        }
    }
}
