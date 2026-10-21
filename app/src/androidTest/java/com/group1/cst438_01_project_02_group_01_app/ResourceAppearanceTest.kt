package com.group1.cst438_01_project_02_group_01_app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResourceAppearanceTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun postGroup_displaysGroupAppearance() {
        composeRule.onNodeWithText("Post Group").performClick()

        composeRule.onNodeWithText("Group UI").assertExists()
        composeRule.onNodeWithText("Pizza Party").assertExists()
        composeRule.onNodeWithText("Time UI").assertDoesNotExist()
    }

    @Test
    fun editGroup_displaysGroupAppearance() {
        composeRule.onNodeWithText("Edit Group").performClick()

        composeRule.onNodeWithText("Group UI").assertExists()
        composeRule.onNodeWithText("Villain Club").assertExists()
        composeRule.onNodeWithText("Time UI").assertDoesNotExist()
    }

    @Test
    fun postTime_displaysTimeAppearance() {
        composeRule.onNodeWithText("Post Time").performClick()

        composeRule.onNodeWithText("Time UI").assertExists()
        composeRule.onNodeWithText("Mon8-10 Tue14 Thu12-14").assertExists()
        composeRule.onNodeWithText("Group UI").assertDoesNotExist()
    }

    @Test
    fun editTime_displaysTimeAppearance() {
        composeRule.onNodeWithText("Edit Time").performClick()

        composeRule.onNodeWithText("Time UI").assertExists()
        composeRule.onNodeWithText("Fri16-18").assertExists()
        composeRule.onNodeWithText("Group UI").assertDoesNotExist()
    }
}
