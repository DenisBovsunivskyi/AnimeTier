package com.denisbovsunivskyi.animetier.data.datasource.user

import android.content.Context
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val context: Context,
) : AuthDataSource {
    override suspend fun authUserWithEmail(
        email: String,
        password: String
    ): ResponseState<String, String> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            ResponseState.Success("Success")
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            ResponseState.Error("Authentication failed, Invalid credentials entered")
        } catch (e: java.lang.Exception) {
            ResponseState.Error(e.message.toString())
        }
    }


    override suspend fun registerUserWithEmail(
        email: String, password: String,
    ): ResponseState<String, String> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            ResponseState.Success("Success")
        } catch (e: FirebaseAuthWeakPasswordException) {
            ResponseState.Error("Authentication failed, Password should be at least 6 characters")
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            ResponseState.Error("Authentication failed, Invalid email entered")
        } catch (e: FirebaseAuthUserCollisionException) {
            ResponseState.Error("Authentication failed, Email already registered.")
        } catch (e: Exception) {
            ResponseState.Error(e.message.toString())
        }

    }
}