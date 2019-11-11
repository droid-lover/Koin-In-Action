package com.assignment.koin1.viewmodels

import androidx.lifecycle.ViewModel
import com.assignment.koin1.repos.GithubRepo

class GithubViewModel : ViewModel() {
    private val repo = GithubRepo()
    var showProgressBar = repo.showProgressBar

    val users= repo.users
    fun getUsers() = repo.getUsers()

}