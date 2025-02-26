package com.example.heroesandvillains.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.heroesandvillains.R

const val LOADING_SCREEN_TEST_TAG = "loading_screen"
const val ERROR_SCREEN_TEST_TAG = "error_screen"

/**
 * Composable function rendering loading screen
 */
@Composable
fun LoadingScreenUI() = Box(
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState())
        .background(color = colorResource(id = R.color.color_container_background_primary))
        .testTag(LOADING_SCREEN_TEST_TAG)
    ,
    contentAlignment = Alignment.Center
) {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

/**
 * Composable function rendering error screen
 *
 * @param exception The exception that caused the error
 */
@Composable
fun ErrorStateUI(exception: Throwable) = Box(
    modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .fillMaxHeight()
        .testTag(ERROR_SCREEN_TEST_TAG)
        .verticalScroll(rememberScrollState()),
    contentAlignment = Alignment.Center
) {
    Text(
        text = "Error loading Hero list, error is ${exception.message}"
    )
}

