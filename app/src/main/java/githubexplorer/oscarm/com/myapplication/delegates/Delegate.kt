package githubexplorer.oscarm.com.myapplication.delegates

interface Delegate {
    fun start()

    fun stop()
}

class CompositeDelegates(vararg val delegates: Delegate) : Delegate {
    override fun start() = delegates.forEach(Delegate::start)

    override fun stop()  = delegates.forEach(Delegate::stop)
}