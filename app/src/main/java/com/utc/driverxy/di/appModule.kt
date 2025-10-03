package com.utc.driverxy.di

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.data.provider.ContextProviderImpl
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSourceImpl
import com.utc.driverxy.data.repository.UserRepositoryImpl
import com.utc.driverxy.domain.provider.ContextProvider
import com.utc.driverxy.domain.repository.UserRepository
import com.utc.driverxy.domain.usecase.user.SignInWithGoogleUseCase
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
    single<ContextProvider> {
        ContextProviderImpl()
    }

    // DataSource
    single<GoogleAuthDataSource> {
        GoogleAuthDataSourceImpl(get())
    }

    // Repository
    single<UserRepository> { UserRepositoryImpl(get()) }

    // UseCase
    factory { SignInWithGoogleUseCase(get()) }

    // ViewModel
    viewModel { SplashViewModel() }
    viewModel { SignInViewModel(get(), get()) }
}