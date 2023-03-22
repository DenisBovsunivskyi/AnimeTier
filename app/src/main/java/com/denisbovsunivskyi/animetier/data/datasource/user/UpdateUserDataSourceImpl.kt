package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.data.util.FireStoreCollection
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UpdateUserDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : UpdateUserDataSource {
    override suspend fun updateUserWhileRegister(
        user: FirebaseUser
    ): Flow<ResponseState<FirebaseUser, String>> {
        return flow {
            val document = user.userId?.let {
                firebaseFirestore.collection(FireStoreCollection.USER).document(it)
            }
            println("I'm working in thread ${Thread.currentThread().name}")
            var result: ResponseState<FirebaseUser, String>  = ResponseState.Success(user)
            document
                ?.set(user)
                ?.addOnSuccessListener {
                    result = ResponseState.Success(user)

                }

                ?.addOnFailureListener {
                    result = ResponseState.Error(it.localizedMessage?.toString()?:"Error")

                }?.await()
            emit(result)
        }

    }
}