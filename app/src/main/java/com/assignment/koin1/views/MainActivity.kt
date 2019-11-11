package com.assignment.koin1.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.assignment.koin1.R
import com.assignment.koin1.model.GithubUser
import com.assignment.koin1.viewmodels.GithubViewModel
import com.assignment.koin1.utils.Result
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val githubViewModel by lazy { ViewModelProviders.of(this).get(GithubViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeModelChanges()
        getUserList()
    }

    private fun observeModelChanges() {
        githubViewModel.users.observe(this, Observer {
            when (it) {
                is Result.Success<ArrayList<GithubUser>> -> {
                    showUserList(it.data)
                }
                is Result.Failure -> {
                    Toast.makeText(this@MainActivity, "${it.throwable}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun getUserList() {
        githubViewModel.getUsers()
    }


    private fun showUserList(data: java.util.ArrayList<GithubUser>) {
        tvApiResponseRaw.text = data.toString()
    }
}
