package com.utc.driverxy.di

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.R
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSourceImpl
import com.utc.driverxy.data.repository.AuthRepositoryImpl
import com.utc.driverxy.domain.repository.AuthRepository
import com.utc.driverxy.domain.usecase.SignInWithGoogleUseCase
import com.utc.driverxy.presentation.login.LoginViewModel
import com.utc.driverxy.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

    // Google Sign-In
    single<GoogleSignInClient> {
        val context = get<android.content.Context>()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }
    // ViewModels
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel(get(),get()) }
}
