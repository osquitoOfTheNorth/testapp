package githubexplorer.oscarm.com.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import githubexplorer.oscarm.com.myapplication.delegates.Delegate

abstract class DelegatingFragment : Fragment() {

    private lateinit var delegate: Delegate

    abstract fun createDelegates() : Delegate
    @LayoutRes abstract fun viewResourceId(): Int

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        delegate = createDelegates()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(viewResourceId(), container, false)
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