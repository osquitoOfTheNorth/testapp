package githubexplorer.oscarm.com.myapplication.dagger

import githubexplorer.oscarm.com.myapplication.delegates.Delegate

interface BaseComponent {
    fun delegates(): Delegate
}