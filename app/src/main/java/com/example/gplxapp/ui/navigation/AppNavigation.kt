package com.example.gplxapp.ui.navigation

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gplxapp.R
import com.example.gplxapp.ui.login.LoginScreen
import com.example.gplxapp.ui.register.RegisterScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(
    auth: FirebaseAuth,
    googleLauncher: ActivityResultLauncher<Intent>
) {
    val nav = rememberNavController()
    val context = LocalContext.current

    var loginLoading by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }
    var registerLoading by remember { mutableStateOf(false) }
    var registerError by remember { mutableStateOf<String?>(null) }

    var currentUser by remember { mutableStateOf(auth.currentUser) }
    DisposableEffect(auth) {
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            currentUser = firebaseAuth.currentUser
        }
        auth.addAuthStateListener(listener)
        onDispose { auth.removeAuthStateListener(listener) }
    }

    var pendingGoogleNavRoute by remember { mutableStateOf<String?>(null) }

    val startDest = if (currentUser != null) "home" else "login"

    NavHost(navController = nav, startDestination = startDest, modifier = Modifier.fillMaxSize()) {
        composable("login") {
            LoginScreen(
                onLoginClick = { email, pass ->
                    loginError = null
                    loginLoading = true
                    auth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { task ->
                            loginLoading = false
                            if (task.isSuccessful) {
                                nav.navigate("home") { popUpTo("login") { inclusive = true } }
                            } else {
                                loginError = task.exception?.localizedMessage ?: "Login failed"
                            }
                        }
                },
                onGoogleLoginClick = {
                    loginError = null
                    loginLoading = true
                    pendingGoogleNavRoute = "home"
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(context.getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()
                    val client = GoogleSignIn.getClient(context, gso)
                    googleLauncher.launch(client.signInIntent)
                },
                onNavigateToRegister = { nav.navigate("register") },
                isLoading = loginLoading,
                errorMessage = loginError
            )

            LaunchedEffect(currentUser) {
                if (loginLoading && currentUser != null && pendingGoogleNavRoute == "home") {
                    loginLoading = false
                    pendingGoogleNavRoute = null
                    nav.navigate("home") { popUpTo("login") { inclusive = true } }
                }
            }
        }

        composable("register") {
            RegisterScreen(
                onRegisterClick = { email, pass, confirm ->
                    registerError = null
                    if (pass != confirm) {
                        registerError = "Passwords do not match"
                        return@RegisterScreen
                    }
                    registerLoading = true
                    auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { task ->
                            registerLoading = false
                            if (task.isSuccessful) {
                                nav.navigate("home") { popUpTo("login") { inclusive = true } }
                            } else {
                                registerError = task.exception?.localizedMessage ?: "Register failed"
                            }
                        }
                },
                onGoogleRegisterClick = {
                    registerError = null
                    registerLoading = true
                    pendingGoogleNavRoute = "home"
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(context.getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()
                    val client = GoogleSignIn.getClient(context, gso)
                    googleLauncher.launch(client.signInIntent)
                },
                onNavigateToLogin = { nav.popBackStack() },
                isLoading = registerLoading,
                errorMessage = registerError
            )

            LaunchedEffect(currentUser) {
                if (registerLoading && currentUser != null && pendingGoogleNavRoute == "home") {
                    registerLoading = false
                    pendingGoogleNavRoute = null
                    nav.navigate("home") { popUpTo("login") { inclusive = true } }
                }
            }
        }

        composable("home") {
            // simple home screen
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Logged in as: ${currentUser?.email ?: "(no email)"}", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    auth.signOut()
                    nav.navigate("login") { popUpTo("home") { inclusive = true } }
                }) {
                    Text("Log out")
                }
            }
        }
    }
}
