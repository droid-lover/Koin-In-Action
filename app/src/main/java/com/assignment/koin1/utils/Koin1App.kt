package com.assignment.koin1.utils

import androidx.multidex.MultiDexApplication
import com.assignment.koin1.koin_modules.apiModule
import com.assignment.koin1.koin_modules.repositoryModule
import com.assignment.koin1.koin_modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Sachin
 */
class Koin1App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger(Level.DEBUG)
            androidLogger(Level.INFO)
            androidContext(this@Koin1App)
            modules(listOf(viewModelModule, repositoryModule, apiModule))
        }
    }

    companion object {
        private val TAG = Koin1App::class.java.name
        @get:Synchronized
        var instance: Koin1App? = null
        private val MAX_HEAP_SIZE = Runtime.getRuntime().maxMemory().toInt()

    }
}
