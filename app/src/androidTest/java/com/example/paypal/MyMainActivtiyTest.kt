package com.example.paypal

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MyMainActivtiyTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginTest() {
        // 1. Find view by Id
        onView(withId(R.id.etName))

        // 2. Perform action—type string and click button
            .perform(typeText("android paypal testig"), closeSoftKeyboard());

        // 3. Check that the text was changed
        onView(withId(R.id.textView))
            .check(matches(withText("android paypal testig")));
    }
}