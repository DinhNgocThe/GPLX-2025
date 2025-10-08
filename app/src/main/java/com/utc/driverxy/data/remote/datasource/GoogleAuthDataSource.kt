package com.utc.driverxy.data.remote.datasource


interface GoogleAuthDataSource {
    suspend fun signIn(): Boolean
    fun isSignedIn(): Boolean
    suspend fun signOut()
}