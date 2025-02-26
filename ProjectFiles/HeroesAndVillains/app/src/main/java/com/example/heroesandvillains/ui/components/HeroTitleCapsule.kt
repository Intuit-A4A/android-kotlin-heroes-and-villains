package com.example.heroesandvillains.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class HeroDataType {
    GOOD,
    BAD,
    OTHER
}

/**
 * Data class to hold the Hero Title Capsule data
 *
 * @param title The title
 * @param heroType The hero type
 */
data class HeroTitleCapsuleData(
    val title: String,
    val heroType: HeroDataType
)

/**
 * Composable function to render the Hero Title Capsule
 *
 * @param modifier The modifier
 * @param data The data
 */
@Composable
fun HeroTitleCapsule(
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    data: HeroTitleCapsuleData
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(text = data.title, style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .background(
                    color = when (data.heroType) {
                        HeroDataType.GOOD -> Color.Green
                        HeroDataType.BAD -> Color.Red
                        HeroDataType.OTHER -> Color.LightGray
                    },
                    RoundedCornerShape(50.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .width(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = data.heroType.name.lowercase(),
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}