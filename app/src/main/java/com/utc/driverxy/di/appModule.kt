package com.utc.driverxy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.data.local.datastore.DataStoreManagerImpl
import com.utc.driverxy.data.provider.GoogleAuthClient
import com.utc.driverxy.data.remote.datasource.UserRemoteDataSource
import com.utc.driverxy.data.remote.datasource.UserRemoteDataSourceImpl
import com.utc.driverxy.data.repository.UserRepositoryImpl
import com.utc.driverxy.domain.repository.UserRepository
import com.utc.driverxy.domain.usecase.user.SaveUserUseCase
import com.utc.driverxy.presentation.onboarding.OnboardingViewModel
import com.utc.driverxy.presentation.signin.SignInViewModel
import com.utc.driverxy.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    /*
        Local DI
    */
    // Room
    //single { Room.databaseBuilder(get(), LocalDatabase::class.java, "DriverXy.db").build() }
    //single { get<LocalDatabase>().userDao() }

    // DataStore
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Context>().preferencesDataStoreFile("driverxy_preferences") }
        )
    }
    single<DataStoreManager> {
        DataStoreManagerImpl(get())
    }

    // Local data source

    /*
        Remote DI
    */
    // Firebase
    single { FirebaseAuth.getInstance() }
    single { GoogleAuthClient(get()) }
    single {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()

        FirebaseFirestore.getInstance().apply {
            firestoreSettings = settings
        }
    }

    // Remote data source
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }

    /*
        Business DI
    */
    // Repository
    single<UserRepository> { UserRepositoryImpl(get()) }

    // UseCase
    factory { SaveUserUseCase(get(), get()) }

    /*
        UI DI
    */
    // ViewModel
    viewModel { SplashViewModel(get(), get()) }
    viewModel { SignInViewModel(get(), get(), get()) }
    viewModel { OnboardingViewModel(get()) }
}