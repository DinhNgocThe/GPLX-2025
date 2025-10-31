package com.utc.driverxy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.data.local.datastore.DataStoreManagerImpl
import com.utc.driverxy.data.provider.GoogleAuthClient
import com.utc.driverxy.presentation.onboarding.OnboardingViewModel
import com.utc.driverxy.presentation.signin.SignInViewModel
import com.utc.driverxy.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Firebase
    single { FirebaseAuth.getInstance() }

    // DataStore
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Context>().preferencesDataStoreFile("driverxy_preferences") }
        )
    }
    single<DataStoreManager> {
        DataStoreManagerImpl(get())
    }

    // Room
    //single { Room.databaseBuilder(get(), LocalDatabase::class.java, "DriverXy.db").build() }
    //single { get<LocalDatabase>().userDao() }

    // Provider

    // DataSource

    // Repository

    // UseCase

    // ViewModel
    viewModel { SplashViewModel(get(), get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { OnboardingViewModel(get()) }

    // Google Auth
    single { GoogleAuthClient(get()) }

}