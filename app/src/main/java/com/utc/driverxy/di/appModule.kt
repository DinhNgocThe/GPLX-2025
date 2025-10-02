package com.utc.driverxy.di

import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSourceImpl
import com.utc.driverxy.data.repository.AuthRepository
import com.utc.driverxy.data.repository.AuthRepositoryImpl
import com.utc.driverxy.domain.usecase.SignInWithGoogleUseCase
import com.utc.driverxy.presentation.auth.AuthViewModel
import com.utc.driverxy.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Firebase
    single { FirebaseAuth.getInstance() }

    // DataSource
    single<GoogleAuthDataSource> { GoogleAuthDataSourceImpl(get()) }

    // Repository
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    // UseCase
    factory { SignInWithGoogleUseCase(get()) }

    // ViewModels
    viewModel { SplashViewModel() }
    viewModel { AuthViewModel(get()) } // truyền SignInWithGoogleUseCase
}
