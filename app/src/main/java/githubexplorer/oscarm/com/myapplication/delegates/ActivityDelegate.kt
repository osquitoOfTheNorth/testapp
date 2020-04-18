package githubexplorer.oscarm.com.myapplication.delegates

interface ActivityDelegate {
    fun start()

    fun stop()
}

class CompositeActivityDelegate(vararg val delegates: ActivityDelegate) : ActivityDelegate {
    override fun start() = delegates.forEach(ActivityDelegate::start)

    override fun stop()  = delegates.forEach(ActivityDelegate::stop)
}