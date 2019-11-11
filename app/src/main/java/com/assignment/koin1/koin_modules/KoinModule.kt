package com.assignment.koin1.koin_modules

import com.assignment.koin1.apis.ApiClient
import com.assignment.koin1.repos.GithubRepo
import com.assignment.koin1.viewmodels.GithubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel{
      GithubViewModel()
    }
}

val repositoryModule = module {
    single {
        GithubRepo()
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }
    single { provideUseApi(get()) }
}
