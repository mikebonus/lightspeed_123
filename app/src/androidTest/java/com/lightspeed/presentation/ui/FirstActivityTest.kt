package com.lightspeed.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.lightspeed.MainActivity
import com.lightspeed.lightspeedproject.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// MORE UI-TEST-EXAMPLES
// http://blogs.quovantis.com/how-to-use-espresso-for-android-ui-testing/

@RunWith(AndroidJUnit4ClassRunner::class)
class FirstActivityTest {

    @Rule
    @JvmField
    public var activityRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var activityScenarioRule = activityScenarioRule<FirstActivity>()

    // activity-visibility
    @Test
    fun checkActivityVisibility() {
        Espresso.onView(ViewMatchers.withId(R.id.rel_layout_first))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    // recyclerView-visibility
    @Test
    fun checkRecyclerViewVisibility() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_View))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    // recyclerView-visibility
    @Test
    fun checkRecyclerViewScrollableTest() {
        var recyclerView: RecyclerView? = activityRule.activity.findViewById(R.id.recycler_View)
        var itemCount = recyclerView?.adapter?.itemCount

        if (itemCount != null) {
            Espresso.onView(ViewMatchers.withId(R.id.recycler_View)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    itemCount.minus(1)
                )
            )
        }
    }
}