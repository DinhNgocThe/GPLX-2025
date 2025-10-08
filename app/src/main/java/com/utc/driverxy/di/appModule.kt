package com.utc.driverxy.di

import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSourceImpl
import com.utc.driverxy.data.repository.UserRepositoryImpl
import com.utc.driverxy.domain.repository.UserRepository
import com.utc.driverxy.domain.usecase.SignInWithGoogleUseCase
import com.utc.driverxy.presentation.login.LoginViewModel
import com.utc.driverxy.presentation.splash.SplashViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val appModule = module {
    // Firebase
    single { FirebaseAuth.getInstance() }

    // DataSource
    single<GoogleAuthDataSource> { GoogleAuthDataSourceImpl(get(), get()) }

    // Repository
    single<UserRepository> { UserRepositoryImpl(get()) }

    // UseCase
    factory { SignInWithGoogleUseCase(get()) }

    // ViewModels
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel(get()) }
}
