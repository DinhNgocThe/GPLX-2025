package com.utc.driverxy.presentation.auth

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.utc.driverxy.R

/**
 * Helper chỉ để lấy Google ID Token
 * Không login Firebase trực tiếp (login sẽ do AuthViewModel xử lý)
 */
class GoogleSignInHelper(
    private val activity: Activity
) {
    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id)) // từ google-services.json
            .requestEmail()
            .build()
        GoogleSignIn.getClient(activity, gso)
    }

    fun getSignInIntent(): Intent = googleSignInClient.signInIntent

    /**
     * Lấy idToken từ Google Account
     */
    fun extractIdToken(account: GoogleSignInAccount?): String? {
        return account?.idToken
    }

    fun signOut() {
        googleSignInClient.signOut()
    }
}
