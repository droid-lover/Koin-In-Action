package com.assignment.koin1

import androidx.multidex.MultiDexApplication

/**
 * Created by Sachin
 */
class Koin1App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private val TAG = Koin1App::class.java.name
        @get:Synchronized
        var instance: Koin1App? = null
        private val MAX_HEAP_SIZE = Runtime.getRuntime().maxMemory().toInt()

    }
}
