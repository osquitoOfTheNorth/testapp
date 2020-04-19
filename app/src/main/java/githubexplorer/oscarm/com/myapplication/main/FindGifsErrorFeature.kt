package githubexplorer.oscarm.com.myapplication.main

import githubexplorer.oscarm.com.myapplication.activity.LoadingDialog
import githubexplorer.oscarm.com.myapplication.app.ToastWrapper
import githubexplorer.oscarm.com.myapplication.rx.DisposableDelegate
import javax.inject.Inject

class FindGifsErrorFeature @Inject constructor(
    private val viewModel: MainActivityViewModel,
    private val toastWrapper: ToastWrapper,
    private val loadingDialog: LoadingDialog
) : DisposableDelegate() {
    override fun start() {
        viewModel.errors.subscribeSafely(toastWrapper::showToast)
        loadingDialog.hide()
    }
}