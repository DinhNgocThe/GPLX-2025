package com.utc.driverxy.di

import com.utc.driverxy.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Firebase
    //single { FirebaseAuth.getInstance() }

    // Room
    //single { Room.databaseBuilder(get(), LocalDatabase::class.java, "DriverXy.db").build() }
    //single { get<LocalDatabase>().userDao() }

    // DataSource
    //single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }

    // Repository
    //single<UserRepository> { UserRepositoryImpl(get(), get(), get(),get()) }

    // UseCase
    //factory { SignInWithGoogleUseCase(get()) }

    // ViewModel
    viewModel { SplashViewModel() }
}