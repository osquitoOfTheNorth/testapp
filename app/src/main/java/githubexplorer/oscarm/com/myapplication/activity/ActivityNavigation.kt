package githubexplorer.oscarm.com.myapplication.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import githubexplorer.oscarm.com.myapplication.R
import javax.inject.Inject

class ActivityNavigation @Inject constructor(private val fragmentManager: FragmentManager) {

    fun add(fragment: Fragment, tag: String?) {
        fragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .add(R.id.content, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}