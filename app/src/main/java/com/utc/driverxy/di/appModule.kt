package com.utc.driverxy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.utc.driverxy.data.local.datasource.QuestionLocalDataSource
import com.utc.driverxy.data.local.datasource.QuestionLocalDataSourceImpl
import com.utc.driverxy.data.local.datasource.RankLocalDataSource
import com.utc.driverxy.data.local.datasource.RankLocalDataSourceImpl
import com.utc.driverxy.data.local.datasource.TopicLocalDataSource
import com.utc.driverxy.data.local.datasource.TopicLocalDataSourceImpl
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.data.local.datastore.DataStoreManagerImpl
import com.utc.driverxy.data.local.room.DriverXyDatabase
import com.utc.driverxy.data.provider.GoogleAuthClient
import com.utc.driverxy.data.remote.datasource.QuestionRemoteDataSource
import com.utc.driverxy.data.remote.datasource.QuestionRemoteDataSourceImpl
import com.utc.driverxy.data.remote.datasource.RankRemoteDataSource
import com.utc.driverxy.data.remote.datasource.RankRemoteDataSourceImpl
import com.utc.driverxy.data.remote.datasource.TopicRemoteDataSource
import com.utc.driverxy.data.remote.datasource.TopicRemoteDataSourceImpl
import com.utc.driverxy.data.remote.datasource.UserRemoteDataSource
import com.utc.driverxy.data.remote.datasource.UserRemoteDataSourceImpl
import com.utc.driverxy.data.repository.QuestionRepositoryImpl
import com.utc.driverxy.data.repository.RankRepositoryImpl
import com.utc.driverxy.data.repository.TopicRepositoryImpl
import com.utc.driverxy.data.repository.UserRepositoryImpl
import com.utc.driverxy.domain.repository.QuestionRepository
import com.utc.driverxy.domain.repository.RankRepository
import com.utc.driverxy.domain.repository.TopicRepository
import com.utc.driverxy.domain.repository.UserRepository
import com.utc.driverxy.domain.usecase.question.CountQuestionsCompleted
import com.utc.driverxy.domain.usecase.question.CountQuestionsCompletedByTopicId
import com.utc.driverxy.domain.usecase.question.CountQuestionsCriticalCompleted
import com.utc.driverxy.domain.usecase.question.GetQuestionsByRankId
import com.utc.driverxy.domain.usecase.question.GetQuestionsCriticalByRank
import com.utc.driverxy.domain.usecase.question.GetWrongQuestion
import com.utc.driverxy.domain.usecase.question.SaveWrongQuestion
import com.utc.driverxy.domain.usecase.question.SetDoneQuestionUseCase
import com.utc.driverxy.domain.usecase.question.SyncAllQuestionsUseCase
import com.utc.driverxy.domain.usecase.question.SyncQuestionsCompletedUseCase
import com.utc.driverxy.domain.usecase.rank.GetAllRanksUseCase
import com.utc.driverxy.domain.usecase.rank.SyncAllRanksUseCase
import com.utc.driverxy.domain.usecase.rank.UpdateRankUseCase
import com.utc.driverxy.domain.usecase.topic.GetAllTopicsUseCase
import com.utc.driverxy.domain.usecase.topic.GetTopicById
import com.utc.driverxy.domain.usecase.topic.SyncAllTopicsUseCase
import com.utc.driverxy.domain.usecase.user.GetUserUseCase
import com.utc.driverxy.domain.usecase.user.SaveUserUseCase
import com.utc.driverxy.presentation.camera.CameraViewModel
import com.utc.driverxy.presentation.changeRank.ChangeRankViewModel
import com.utc.driverxy.presentation.exam.ExamViewModel
import com.utc.driverxy.presentation.examTaking.ExamTakingViewModel
import com.utc.driverxy.presentation.home.HomeViewModel
import com.utc.driverxy.presentation.main.MainViewModel
import com.utc.driverxy.presentation.onboarding.OnboardingViewModel
import com.utc.driverxy.presentation.practice.PracticeViewModel
import com.utc.driverxy.presentation.practiceQuestion.PracticeQuestionViewModel
import com.utc.driverxy.presentation.scanTrafficSigns.ScanTrafficSignsViewModel
import com.utc.driverxy.presentation.signin.SignInViewModel
import com.utc.driverxy.presentation.splash.SplashViewModel
import com.utc.driverxy.presentation.wrongQuestion.WrongQuestionViewModel
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

    single {
        get<DriverXyDatabase>().topicDao()
    }

    single {
        get<DriverXyDatabase>().questionDao()
    }

    single {
        get<DriverXyDatabase>().questionCompletedDao()
    }

    single {
        get<DriverXyDatabase>().wrongQuestionDao()
    }
}

val dataSourceModule = module {
    // Local data source
    single<RankLocalDataSource> { RankLocalDataSourceImpl(get()) }
    single<TopicLocalDataSource> { TopicLocalDataSourceImpl(get()) }
    single<QuestionLocalDataSource> { QuestionLocalDataSourceImpl(get(), get(), get()) }

    // Remote data source
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }
    single<RankRemoteDataSource> { RankRemoteDataSourceImpl(get()) }
    single<TopicRemoteDataSource> { TopicRemoteDataSourceImpl(get()) }
    single<QuestionRemoteDataSource> { QuestionRemoteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<RankRepository> { RankRepositoryImpl(get(), get()) }
    single<TopicRepository> { TopicRepositoryImpl(get(), get()) }
    single<QuestionRepository> { QuestionRepositoryImpl(get(), get(), get()) }
}

val useCaseModule = module {
    // User
    factoryOf(::SaveUserUseCase)
    factoryOf(::GetUserUseCase)

    // Rank
    factoryOf(::SyncAllRanksUseCase)
    factoryOf(::GetAllRanksUseCase)
    factoryOf(::UpdateRankUseCase)

    // Topic
    factoryOf(::SyncAllTopicsUseCase)
    factoryOf(::GetAllTopicsUseCase)
    factoryOf(::GetTopicById)

    // Question
    factoryOf(::SyncAllQuestionsUseCase)
    factoryOf(::SyncQuestionsCompletedUseCase)
    factoryOf(::GetQuestionsByRankId)
    factoryOf(::SetDoneQuestionUseCase)
    factoryOf(::CountQuestionsCompletedByTopicId)
    factoryOf(::CountQuestionsCompleted)
    factoryOf(::GetQuestionsCriticalByRank)
    factoryOf(::CountQuestionsCriticalCompleted)
    factoryOf(::GetWrongQuestion)
    factoryOf(::SaveWrongQuestion)
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
    viewModelOf(::ChangeRankViewModel)
    viewModelOf(::PracticeViewModel)
    viewModelOf(::PracticeQuestionViewModel)
    viewModelOf(::WrongQuestionViewModel)
    viewModelOf(::ExamTakingViewModel)
}