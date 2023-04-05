package com.ghitatomy.yooxshop

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ghitatomy.yooxshop.presentation.activities.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class OverviewFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isFragmentVisible_onAppLaunch() {
        Thread.sleep(3 * DateUtils.SECOND_IN_MILLIS)
        onView(withId(R.id.items_recycle_view)).check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test(expected = PerformException::class)
    fun itemWithText_notInTheList_doesNotExist() {
        onView(withId(R.id.items_recycle_view))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("not in the list"))
                )
            )
    }
}