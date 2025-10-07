package com.utc.driverxy.navigation

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.R
import com.utc.driverxy.presentation.login.LoginContract
import com.utc.driverxy.presentation.login.LoginScreen
import com.utc.driverxy.presentation.login.LoginViewModel
import com.utc.driverxy.presentation.onboarding.OnboardingScreen
import com.utc.driverxy.presentation.onboarding.WelcomeScreen
import com.utc.driverxy.presentation.splash.SplashScreen
import com.utc.driverxy.presentation.splash.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavRoutes() {
    val backStack = rememberNavBackStack<Destination>(Destination.Splash)

    Scaffold { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {

                // ✅ Sửa lỗi truyền navigateToWelcome
                entry<Destination.Splash> {
                    SplashScreen(
                        navigateToWelcome = { viewModel: SplashViewModel ->
                            backStack.add(Destination.Welcome)
                        }
                    )
                }

                entry<Destination.Welcome> {
                    WelcomeScreen(innerPadding = innerPadding) {
                        backStack.add(Destination.Login)
                    }
                }

                entry<Destination.Login> {
                    val context = LocalContext.current
                    val viewModel: LoginViewModel = koinViewModel()
                    val uiState by viewModel.viewState.collectAsState()
                    val googleSignInClient = remember { getGoogleSignInClient(context) }

                    // ✅ Kiểm tra nếu user đã đăng nhập
                    val currentUser = remember {
                        FirebaseAuth.getInstance().currentUser
                    }
                    LaunchedEffect(currentUser) {
                        if (currentUser != null) backStack.add(Destination.Onboarding)
                    }

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult()
                    ) { result ->
                        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                        handleSignInResult(task, viewModel)
                    }

                    LaunchedEffect(viewModel) {
                        viewModel.singleEvent.collectLatest { event ->
                            when (event) {
                                is LoginContract.Effect.NavigateToHome ->
                                    backStack.add(Destination.Onboarding)
                                is LoginContract.Effect.ShowError ->
                                    println("❌ Login error: ${event.message}")
                            }
                        }
                    }

                    LoginScreen(
                        uiState = uiState,
                        onGoogleLoginClicked = {
                            launcher.launch(googleSignInClient.signInIntent)
                        },
                        onContinueWithoutLogin = {
                            viewModel.processIntent(LoginContract.Intent.OnContinueWithoutLoginClicked)
                        }
                    )
                }

                entry<Destination.Onboarding> {
                    OnboardingScreen(innerPadding = innerPadding)
                }
            }
        )
    }
}

// ✅ Thêm import BuildConfig phù hợp với module `app`
fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    return GoogleSignIn.getClient(context, gso)
}

// ✅ Xử lý đăng nhập Google an toàn, tránh crash
fun handleSignInResult(
    task: com.google.android.gms.tasks.Task<GoogleSignInAccount>,
    viewModel: LoginViewModel
) {
    try {
        val account = task.getResult(ApiException::class.java)
        val idToken = account?.idToken
        if (!idToken.isNullOrEmpty()) {
            viewModel.processIntent(LoginContract.Intent.OnGoogleIdTokenReceived(idToken))
        } else {
            viewModel.processIntent(LoginContract.Intent.OnGoogleIdTokenReceived(""))
        }
    } catch (e: ApiException) {
        e.printStackTrace()
        viewModel.processIntent(LoginContract.Intent.OnGoogleIdTokenReceived(""))
    }
}
