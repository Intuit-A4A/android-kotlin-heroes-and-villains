package com.example.heroesandvillains

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.heroesandvillains.ui.components.ERROR_SCREEN_TEST_TAG
import com.example.heroesandvillains.ui.components.ErrorStateUI
import com.example.heroesandvillains.ui.components.LOADING_SCREEN_TEST_TAG
import com.example.heroesandvillains.ui.components.LoadingScreenUI

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UIComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorUiTest() {
        composeTestRule.setContent {
            ErrorStateUI(exception = Exception("Test Exception"))
        }

        composeTestRule.onNodeWithTag(ERROR_SCREEN_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithText("Error loading Hero list, error is Test Exception").assertIsDisplayed()
    }

    @Test
    fun loadingUiTest() {
        composeTestRule.setContent {
            LoadingScreenUI()
        }

        composeTestRule.onNodeWithTag(LOADING_SCREEN_TEST_TAG).assertIsDisplayed()
    }
}