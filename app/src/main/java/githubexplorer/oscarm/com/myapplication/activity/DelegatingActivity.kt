package githubexplorer.oscarm.com.myapplication.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import githubexplorer.oscarm.com.myapplication.delegates.Delegate

abstract class DelegatingActivity : AppCompatActivity() {

    private lateinit var delegate: Delegate

    abstract fun createDelegates() : Delegate

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