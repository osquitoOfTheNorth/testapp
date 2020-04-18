package githubexplorer.oscarm.com.myapplication

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.children
import androidx.test.rule.ActivityTestRule

fun ActivityTestRule<*>.removeUnreliableElements() {
    runOnUiThread {
        with(activity.findViewById<ViewGroup>(R.id.content)) {
            removeFlakyElementsRecursive()
        }
    }
}

private fun ViewGroup.removeFlakyElementsRecursive() {
    children.forEach {
        it.removeFlakyElements()
        if (it is ViewGroup) it.removeFlakyElementsRecursive()
    }
}

private fun View.removeFlakyElements() {
    isFocusable = false
    if (this is EditText) {
        isCursorVisible = false
    }
}
