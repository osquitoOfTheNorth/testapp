package githubexplorer.oscarm.com.myapplication.views

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withText

fun editText() = onView(isAssignableFrom(EditText::class.java))!!

fun viewWithText(string: String) = onView(withText(string))