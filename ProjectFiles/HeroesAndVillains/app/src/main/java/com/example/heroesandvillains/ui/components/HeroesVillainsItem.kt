package com.example.heroesandvillains.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.heroesandvillains.R
import com.example.heroesandvillains.data.HeroDataModel
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * Composable function to render the Heroes and Villains item
 *
 * @param modifier The modifier
 * @param dataModel The data model
 * @param onItemSelected The callback to be invoked when item is selected
 */
@Composable
fun HeroesVillainsItem(
    modifier: Modifier = Modifier,
    dataModel: HeroDataModel,
    onItemSelected:(Int) -> Unit
) {
    val cardShape = RoundedCornerShape(size = 16.dp)
    ElevatedCard(
        modifier = modifier
            .padding(bottom = 2.dp)
            .clip(cardShape),
        onClick = { onItemSelected(dataModel.id) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            val brush = Brush.horizontalGradient(
                listOf(
                    colorResource(id = R.color.purple_gradient),
                    Color.Transparent,
                    colorResource(id = R.color.neon_gradient)
                )
            )
            Canvas(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RectangleShape)
                ,
                onDraw = {
                    drawCircle(brush)
                }
            )

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HeroStarImage(
                    modifier = Modifier
                        .size(150.dp)
                        .weight(0.8f),
                    imageUrl = dataModel.images.md
                )
                HeroTitleCapsule(
                    modifier = Modifier.weight(1f),
                    data = HeroTitleCapsuleData(
                        title = dataModel.name,
                        heroType = when (dataModel.biography.alignment) {
                            "good" -> HeroDataType.GOOD
                            "bad" -> HeroDataType.BAD
                            else -> HeroDataType.OTHER
                        }
                    )
                )
                Icon(
                    modifier = Modifier
                        .weight(0.2f)
                        .padding(end = 16.dp)
                        .size(64.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Select",
                )
            }
        }
    }
}

@Composable
private fun HeroStarImage(
    imageUrl: String,
    modifier: Modifier
) {
    val triangleShape = GenericShape { size, _ ->
        val numPoints = 5
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val outerRadius = min(size.width, size.height) / 2f
        val innerRadius = outerRadius / 2.5f // Adjust the inner radius as needed

        val doublePi = 2 * PI
        val angleIncrement = doublePi / numPoints

        var angle = -PI / 2f // Start angle at the top point of the star
        moveTo(
            x = (centerX + outerRadius * cos(angle)).toFloat(),
            y = (centerY + outerRadius * sin(angle)).toFloat()
        )

        // Draw the points of the star in the correct sequence
        for (i in 1..numPoints) {
            angle += angleIncrement / 2 // Move to the inner angle first
            lineTo(
                x = (centerX + innerRadius * cos(angle)).toFloat(),
                y = (centerY + innerRadius * sin(angle)).toFloat()
            )
            angle += angleIncrement / 2 // Move to the outer angle
            lineTo(
                x = (centerX + outerRadius * cos(angle)).toFloat(),
                y = (centerY + outerRadius * sin(angle)).toFloat()
            )
        }
    }
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = null,
        modifier = modifier
            .clip(triangleShape),
    )
}
