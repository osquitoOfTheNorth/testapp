package githubexplorer.oscarm.com.myapplication.views

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*

fun ViewInteraction.type(string: String) = perform(typeText(string), closeSoftKeyboard())

fun ViewInteraction.click() = perform(ViewActions.click())