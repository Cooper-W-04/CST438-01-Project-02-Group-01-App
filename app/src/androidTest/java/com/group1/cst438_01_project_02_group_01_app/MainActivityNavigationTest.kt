package com.group1.cst438_01_project_02_group_01_app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityNavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun enableIntentAssertions() {
        androidx.test.espresso.intent.Intents.init()
    }

    @After
    fun disableIntentAssertions() {
        androidx.test.espresso.intent.Intents.release()
    }

    @Test
    fun postGroup_opensResourceWithGroupData() {
        composeRule.onNodeWithText("Post Group").performClick()

        intended(allOf(
            hasComponent(Resource::class.java.name),
            hasExtra("TYPE", "PostGroup"),
            hasExtra("GROUPNAME", "Pizza Party")
        ))
    }

    @Test
    fun editGroup_opensResourceWithGroupData() {
        composeRule.onNodeWithText("Edit Group").performClick()

        intended(allOf(
            hasComponent(Resource::class.java.name),
            hasExtra("TYPE", "EditGroup"),
            hasExtra("GROUPNAME", "Villain Club")
        ))
    }

    @Test
    fun postTime_opensResourceWithTimeData() {
        composeRule.onNodeWithText("Post Time").performClick()

        intended(allOf(
            hasComponent(Resource::class.java.name),
            hasExtra("TYPE", "PostTime"),
            hasExtra("TIME", "Mon8-10 Tue14 Thu12-14")
        ))
    }

    @Test
    fun editTime_opensResourceWithTimeData() {
        composeRule.onNodeWithText("Edit Time").performClick()

        intended(allOf(
            hasComponent(Resource::class.java.name),
            hasExtra("TYPE", "EditTime"),
            hasExtra("TIME", "Fri16-18")
        ))
    }
}
