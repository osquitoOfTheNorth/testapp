package githubexplorer.oscarm.com.myapplication.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import githubexplorer.oscarm.com.myapplication.delegates.ActivityDelegate

abstract class DelegatingActivity : AppCompatActivity() {

    private lateinit var delegate: ActivityDelegate

    abstract fun createDelegates() : ActivityDelegate

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = createDelegates()
    }

    override fun onStart() {
        super.onStart()
        delegate.start()
    }

    override fun onStop() {
        super.onStop()
        delegate.stop()
    }
}