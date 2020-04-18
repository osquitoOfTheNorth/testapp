package githubexplorer.oscarm.com.myapplication.main

import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import githubexplorer.oscarm.com.myapplication.activity.LoadingDialog
import githubexplorer.oscarm.com.myapplication.app.InputManager
import githubexplorer.oscarm.com.myapplication.rx.DisposableDelegate
import javax.inject.Inject

class MainButtonClickDelegate @Inject constructor(
    private val searchButton: Button,
    private val loadingDialog: LoadingDialog,
    private val textInput: TextInputEditText,
    private val viewModel: MainActivityViewModel,
    private val inputMethodManager: InputManager
) : DisposableDelegate() {

    override fun start() {
        searchButton.setOnClickListener {
            loadingDialog.show()
            viewModel.searchForGifs(textInput.text.toString())
            inputMethodManager.hideSoftInputFromWindow(textInput.windowToken, 0)
        }
    }
}