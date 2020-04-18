package githubexplorer.oscarm.com.myapplication.activity

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import githubexplorer.oscarm.com.myapplication.R

class LoadingDialog(private val activity: Activity) {
    private lateinit var dialog: AlertDialog

    fun show() {
        dialog = AlertDialog.Builder(activity).setView(R.layout.loading_dialog).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
    fun hide(){
        dialog.dismiss()
    }
}