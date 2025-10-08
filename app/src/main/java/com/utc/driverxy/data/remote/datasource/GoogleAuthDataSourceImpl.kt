package com.utc.driverxy.data.remote.datasource

import android.app.Activity
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.utc.driverxy.R
import com.utc.driverxy.domain.provider.ContextProvider
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthDataSourceImpl(
    private val contextProvider: ContextProvider
) : GoogleAuthDataSource {

    private val tag = "Firebase AuthManager: "
    private val credentialManager: CredentialManager
        get() = CredentialManager.create(contextProvider.getCurrentContext())
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun isSignedIn(): Boolean {
        if (firebaseAuth.currentUser != null) {
            println(tag + "already signed in")
            return true
        }
        return false
    }

    override suspend fun signIn(): Boolean {
        if (isSignedIn()) return true

        try {
            val result = buildCredentialRequest()
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

    private suspend fun buildCredentialRequest(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder().addCredentialOption(
            GetGoogleIdOption
                .Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(contextProvider.getCurrentContext().getString(R.string.default_web_client_id))
                .setAutoSelectEnabled(false)
                .build()
        ).build()
        return credentialManager.getCredential(context = contextProvider.getCurrentContext(), request = request)
    }

    override suspend fun signOut() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
        firebaseAuth.signOut()
    }
}

