package com.assignment.koin1.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.assignment.koin1.R
import com.assignment.koin1.model.GithubUser
import com.assignment.koin1.utils.Result
import com.assignment.koin1.viewmodels.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

// normal way
// private val githubViewModel by lazy { ViewModelProviders.of(this).get(GithubViewModel::class.java) }

    //using KOIN
    private val githubViewModel by viewModel<GithubViewModel>()


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
