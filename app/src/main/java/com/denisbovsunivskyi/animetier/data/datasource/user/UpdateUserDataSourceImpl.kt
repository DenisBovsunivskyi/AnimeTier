package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.data.util.FireStoreCollection
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UpdateUserDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : UpdateUserDataSource {
    override suspend fun updateUserWhileRegister(
        user: FirebaseUser
    ): ResponseState<FirebaseUser, String> {
        val document = user.userId?.let {
            firebaseFirestore.collection(FireStoreCollection.USER).document(it)
        }
        var result: ResponseState<FirebaseUser, String> = ResponseState.Success(user)
        document
            ?.set(user)
            ?.addOnSuccessListener {
                result = ResponseState.Success(user)

            }

            ?.addOnFailureListener {
                result = ResponseState.Error(it.localizedMessage?.toString() ?: "Error")

            }?.await()
        return result
    }
}