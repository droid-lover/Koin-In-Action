package com.assignment.koin1.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.koin1.apis.ApiClient
import com.assignment.koin1.model.GithubUser
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import com.assignment.koin1.utils.Result

class GithubRepo : Repo(){

    private val _users = MutableLiveData<Result<ArrayList<GithubUser>>>()
    val users: LiveData<Result<ArrayList<GithubUser>>> = _users


    fun getUsers() {
        _showProgressBar.postValue(true)
        disposables.add(ApiClient.client.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<GithubUser>>() {
                    override fun onSuccess(addressDocuments: ArrayList<GithubUser>) {
                        Log.d("users:", GsonBuilder().create().toJson(addressDocuments))
                        _showProgressBar.postValue(false)
                        _users.postValue(Result.Success(addressDocuments))
                    }

                    override fun onError(throwable: Throwable) {
                        _showProgressBar.postValue(false)
                        _users.postValue(Result.Failure(throwable))
                    }
                }))

    }

}