package com.denisbovsunivskyi.animetier.data.datasource.user

import android.content.Context
import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val context: Context,
) : AuthDataSource {
    override suspend fun authUserWithEmail(
        email: String,
        password: String
    ): Flow<ResponseState<FirebaseUser, String>> {
        return flow {  }
    }


    //        firebaseAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = firebaseAuth.currentUser
//                    if (user == null) {
//                        result.invoke(ResponseState.Failure(context.getString(R.string.error_user_not_found)))
//                    } else {
//                        result.invoke(ResponseState.Success(UserInfo("abc", "avc", "wweq", "sdas")))
//                    }
//                } else {
//                    // If sign in fails, display a message to the user.
//                    result.invoke(ResponseState.Failure(context.getString(R.string.error_something_went_wrong)))
//                }
//            }.addOnFailureListener {
//                Log.d(TAG, "createUserWithEmail:failed + $it")
//                result.invoke(ResponseState.Failure(context.getString(R.string.error_something_went_wrong)))
//            }

    override suspend fun registerUserWithEmail(
        user: FirebaseUser,
    ): Flow<ResponseState<FirebaseUser,String>> {
        return flow {
            try {
                val data =
                    firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
                user.userId = data.user?.uid ?: ""
                emit(ResponseState.Success(user))
            } catch (e: FirebaseAuthWeakPasswordException) {
                emit(ResponseState.Error("Authentication failed, Password should be at least 6 characters"))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                emit(ResponseState.Error("Authentication failed, Invalid email entered"))
            } catch (e: FirebaseAuthUserCollisionException) {
                emit(ResponseState.Error("Authentication failed, Email already registered."))
            } catch (e: Exception) {
                emit(ResponseState.Error(e.message.toString()))
            }

        }
    }

    companion object {
        const val TAG = "AuthDataSource"
    }
}