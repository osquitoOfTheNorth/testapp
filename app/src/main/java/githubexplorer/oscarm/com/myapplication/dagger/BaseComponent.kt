package githubexplorer.oscarm.com.myapplication.dagger

import githubexplorer.oscarm.com.myapplication.delegates.ActivityDelegate

interface BaseComponent {
    fun delegates(): ActivityDelegate
}