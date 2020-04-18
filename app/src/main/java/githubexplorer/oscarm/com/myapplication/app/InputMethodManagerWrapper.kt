package githubexplorer.oscarm.com.myapplication.app

import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

class InputMethodManagerWrapper @Inject constructor(
    private val inputMethodManager: InputMethodManager
): InputManager {
    override fun hideSoftInputFromWindow(binder: IBinder, flags: Int) {
        inputMethodManager.hideSoftInputFromWindow(binder, flags)
    }
}


interface InputManager {
    fun hideSoftInputFromWindow(binder: IBinder, flags: Int)
}