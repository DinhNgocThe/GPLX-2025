package com.example.gplxapp.data.remote.auth

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.example.gplxapp.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthClient(
    private val context: Context
) {
    private val tag = "Firebase AuthManager: "
    private val credentialManager = CredentialManager.Companion.create(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun isSignedIn(): Boolean {
        if (firebaseAuth.currentUser != null) {
            println(tag + "already signed in")
            return true
        }
        return false
    }

    suspend fun signIn(onUiShown: () -> Unit = {}): Boolean {
        if (isSignedIn()) return true

        try {
            val result = buildCredentialRequest(onUiShown)
            return handleSignIn(result)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e(tag, "SignIn error: ${e.message}", e)
            return false
        }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse): Boolean {
        val credential = result.credential

        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                val tokenCredential = GoogleIdTokenCredential.Companion.createFrom(credential.data)

                println(tag + "name: ${tokenCredential.displayName}")
                println(tag + "email: ${tokenCredential.id}")
                println(tag + "image: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
                val authRequest = firebaseAuth.signInWithCredential(authCredential).await()

                return authRequest.user != null
            } catch (e: GoogleIdTokenParsingException) {
                Log.e(tag, "GoogleIdTokenParsingException: ${e.message}")
                return false
            }
        } else {
            Log.e(tag, "credential is not GoogleIdTokenCredential")
            return false
        }
    }

    private suspend fun buildCredentialRequest(onUiShown: () -> Unit = {}): GetCredentialResponse {
        val request = GetCredentialRequest.Builder().addCredentialOption(
            GetGoogleIdOption
                .Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(ContextCompat.getString(context, R.string.default_web_client_id))
                .setAutoSelectEnabled(false)
                .build()
        ).build()
        onUiShown()
        return credentialManager.getCredential(context = context, request = request)
    }

    suspend fun signOut() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
        firebaseAuth.signOut()
    }
}