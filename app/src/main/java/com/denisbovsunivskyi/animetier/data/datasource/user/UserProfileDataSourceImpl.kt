package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.UserProfileModelDto
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.presentation.model.user.UserInfo
import com.denisbovsunivskyi.animetier.data.util.FireStoreCollection
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UserProfileDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : UserProfileDataSource {
    override suspend fun createUserWhileRegister(
        user: UserProfileModelDto
    ): ResponseState<UserInfo, String> {
        val document = user.userId?.let {
            firebaseFirestore.collection(FireStoreCollection.USER).document(it)
        }
        var result: ResponseState<UserInfo, String> = ResponseState.Success(UserInfo("1","1","1","1","1"))
        document
            ?.set(user)
            ?.addOnSuccessListener {
                result = ResponseState.Success(UserInfo("1","1","1","1","1"))

            }

            ?.addOnFailureListener {
                result = ResponseState.Error(it.localizedMessage?.toString() ?: "Error")

            }?.await()
        return result
    }
}