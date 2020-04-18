package githubexplorer.oscarm.com.myapplication.runner

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.facebook.testing.screenshot.ScreenshotRunner
import githubexplorer.oscarm.com.myapplication.app.MyAppTestApp

class ScreenshotTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return Instrumentation.newApplication(MyAppTestApp::class.java, context)
    }
    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        ScreenshotRunner.onCreate(this, arguments)
    }

    override fun finish(resultCode: Int, results: Bundle?) {
        ScreenshotRunner.onDestroy()
        super.finish(resultCode, results)
    }
}