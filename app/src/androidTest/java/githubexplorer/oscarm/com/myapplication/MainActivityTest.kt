package githubexplorer.oscarm.com.myapplication

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.facebook.testing.screenshot.internal.ScreenshotImpl
import githubexplorer.oscarm.com.myapplication.main.MainActivity
import githubexplorer.oscarm.com.myapplication.views.*
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainActivityTest {

    @JvmField @Rule val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainScreenTest() {
        val activity = activityTestRule.launchActivity(Intent())

        ScreenshotImpl.getInstance().snapActivity(activity).setName("Main_Activity_Starting_Screen").record()
    }

    @Test
    fun detailScreenTest() {
        val activity = activityTestRule.launchActivity(Intent())
        onView(withText("First Object Title")).click()
        ScreenshotImpl.getInstance().snapActivity(activity).setName("Main_Activity_Starting_Detail_Results").record()
    }
}
