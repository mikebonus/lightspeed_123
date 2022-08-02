package com.lightspeed

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.lightspeed.lightspeedproject.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    // activity-visibility
    @Test
    fun checkActivityVisibility(){
        Espresso.onView(withId(R.id.rel_layout_main))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    // imageView-visibility
    @Test
    fun checkImageVisibility() {
        Espresso.onView(withId(R.id.img_thisImage))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    // button-one-visibility
    @Test
    fun checkButtonOneVisibility(){
        Espresso.onView(withId(R.id.btn_first_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // button-two-visibility
    @Test
    fun checkButtonTwoVisibility() {
        Espresso.onView(withId(R.id.btn_second_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // first-button-to FIRST-RECYCLERVIEW-SCREEN
    @Test
    fun checkButtonOneClickToSecondActivity(){
        Espresso.onView(withId(R.id.btn_first_button))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rel_layout_first))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // second-button-to SECOND-RECYCLERVIEW-SCREEN
    @Test
    fun checkButtonTwoClickToSecondActivity(){
        Espresso.onView(withId(R.id.btn_second_button))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rel_layout_second))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}