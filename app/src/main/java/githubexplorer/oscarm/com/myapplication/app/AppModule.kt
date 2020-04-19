package githubexplorer.oscarm.com.myapplication.app

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import githubexplorer.oscarm.com.api.persistence.GifDatabase
import githubexplorer.oscarm.com.myapplication.dagger.AppScope
import githubexplorer.oscarm.com.myapplication.viewmodelbase.ViewModelFactory

@Module
class AppModule {

    @Provides
    @AppScope
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @AppScope
    fun inputMethodManager(ctx: App): InputMethodManager =
        ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    @Provides
    @AppScope
    fun inputManager(inputMethodManager: InputMethodManager): InputManager =
        InputMethodManagerWrapper(inputMethodManager)

    @Provides @AppScope fun context(app: App): Context = app

    @Provides @AppScope
    fun database(context: Context): GifDatabase =  Room
        .databaseBuilder(context, GifDatabase::class.java, "gifs_database").build()
}