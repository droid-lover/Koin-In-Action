package com.assignment.koin1.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

open class Repo {

    protected val disposables = CompositeDisposable()

    protected val _showProgressBar = MutableLiveData<Boolean>()

    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    fun onCleared() {
        disposables.dispose()
    }
}