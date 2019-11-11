package com.assignment.koin1.apis

import com.assignment.koin1.model.GithubUser
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface APIs {

    @GET("users")
    fun getUsers(): Single<ArrayList<GithubUser>>
}