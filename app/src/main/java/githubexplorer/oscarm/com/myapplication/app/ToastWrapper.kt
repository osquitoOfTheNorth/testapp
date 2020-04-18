package githubexplorer.oscarm.com.myapplication.app

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastWrapper @Inject constructor(private val context: Context) {
    fun showToast(msg: CharSequence) = Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}