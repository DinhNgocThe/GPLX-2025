package com.utc.driverxy.data.firebase

import android.app.Activity
import android.util.Log
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
import kotlinx.coroutines.tasks.await

class GoogleAuthClient(
    private val firebaseAuth: FirebaseAuth
) {
    private val tag = "GoogleAuthClient"

    suspend fun signIn(activity: Activity): Boolean {
        return try {
            val credentialManager = CredentialManager.create(activity)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(activity.getString(R.string.default_web_client_id))
                .setAutoSelectEnabled(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            // ⚠️ Phải truyền Activity ở đây, KHÔNG phải context
            val result: GetCredentialResponse = credentialManager.getCredential(activity, request)
            handleSignIn(result)
        } catch (e: Exception) {
            Log.e(tag, "SignIn error: ${e.message}", e)
            false
        }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse): Boolean {
        val credential = result.credential

        return if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                Log.d(tag, "name: ${tokenCredential.displayName}")
                Log.d(tag, "email: ${tokenCredential.id}")
                Log.d(tag, "image: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
                val authResult = firebaseAuth.signInWithCredential(authCredential).await()

                authResult.user != null
            } catch (e: GoogleIdTokenParsingException) {
                Log.e(tag, "GoogleIdTokenParsingException: ${e.message}")
                false
            }
        } else {
            Log.e(tag, "Credential is not GoogleIdTokenCredential")
            false
        }
    }


    suspend fun signOut() {
        firebaseAuth.signOut()
        CredentialManager.create(firebaseAuth.app.applicationContext)
            .clearCredentialState(ClearCredentialStateRequest())
    }
}
