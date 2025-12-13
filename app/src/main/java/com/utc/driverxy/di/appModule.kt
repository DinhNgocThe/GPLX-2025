package com.utc.driverxy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.utc.driverxy.data.local.datasource.RankLocalDataSource
import com.utc.driverxy.data.local.datasource.RankLocalDataSourceImpl
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.data.local.datastore.DataStoreManagerImpl
import com.utc.driverxy.data.local.room.DriverXyDatabase
import com.utc.driverxy.data.provider.GoogleAuthClient
import com.utc.driverxy.data.remote.datasource.RankRemoteDataSource
import com.utc.driverxy.data.remote.datasource.RankRemoteDataSourceImpl
import com.utc.driverxy.data.remote.datasource.UserRemoteDataSource
import com.utc.driverxy.data.remote.datasource.UserRemoteDataSourceImpl
import com.utc.driverxy.data.repository.RankRepositoryImpl
import com.utc.driverxy.data.repository.UserRepositoryImpl
import com.utc.driverxy.domain.repository.RankRepository
import com.utc.driverxy.domain.repository.UserRepository
import com.utc.driverxy.domain.usecase.user.GetUserUseCase
import com.utc.driverxy.domain.usecase.user.SaveUserUseCase
import com.utc.driverxy.presentation.camera.CameraViewModel
import com.utc.driverxy.presentation.exam.ExamViewModel
import com.utc.driverxy.presentation.home.HomeViewModel
import com.utc.driverxy.presentation.main.MainViewModel
import com.utc.driverxy.presentation.onboarding.OnboardingViewModel
import com.utc.driverxy.presentation.scanTrafficSigns.ScanTrafficSignsViewModel
import com.utc.driverxy.presentation.signin.SignInViewModel
import com.utc.driverxy.presentation.splash.SplashViewModel
import com.utc.driverxy.worker.WorkerManager
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    // DataStore
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Context>().preferencesDataStoreFile("driverxy_preferences") }
        )
    }
    single<DataStoreManager> {
        DataStoreManagerImpl(get())
    }

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


    // Worker
    single { WorkerManager(get()) }
}

val roomModule = module {
    single {
        DriverXyDatabase.getInstance(get())
    }

    single {
        get<DriverXyDatabase>().rankDao()
    }
}

val dataSourceModule = module {
    // Local data source
    single<RankLocalDataSource> { RankLocalDataSourceImpl(get()) }

    // Remote data source
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }
    single<RankRemoteDataSource> { RankRemoteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<RankRepository> { RankRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    factoryOf(::SaveUserUseCase)
    factoryOf(::GetUserUseCase)
}

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CameraViewModel)
    viewModelOf(::ScanTrafficSignsViewModel)
    viewModelOf(::ExamViewModel)
}