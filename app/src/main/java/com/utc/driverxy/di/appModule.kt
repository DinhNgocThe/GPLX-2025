package com.utc.driverxy.di

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.data.firebase.GoogleAuthClient
import com.utc.driverxy.presentation.signin.SignInViewModel
import com.utc.driverxy.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Firebase
    single { FirebaseAuth.getInstance() }

    // Room
    //single { Room.databaseBuilder(get(), LocalDatabase::class.java, "DriverXy.db").build() }
    //single { get<LocalDatabase>().userDao() }

    // Provider

    // DataSource

    // Repository

    // UseCase

    // ViewModel
    viewModel { SplashViewModel() }
    viewModel { SignInViewModel(get()) }

    // Google Auth
    single { GoogleAuthClient(get()) }

}