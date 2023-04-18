package com.denisbovsunivskyi.animetier.data.datasource.user

import android.net.Uri
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.data.models.user.UserProfileModelDto
import com.denisbovsunivskyi.animetier.data.util.FireStoreCollection
import com.denisbovsunivskyi.animetier.presentation.model.user.UserInfo
import com.denisbovsunivskyi.animetier.presentation.utils.constatns.STORAGE_PROFILE_IMAGE_FILENAME
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UserProfileDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val storageReference: StorageReference,
) : UserProfileDataSource {
    override suspend fun createUserWhileRegister(
        user: UserProfileModelDto
    ): ResponseState<UserInfo, String> {
        var profilePhotoUrl = ""
        if (user.photo != null) {
            when (val result = uploadProfilePhoto(user.userId,user.photo)) {
                is ResponseState.Success -> {
                    profilePhotoUrl = result.data
                }
                is ResponseState.Error -> {
                    return ResponseState.Error(result.rawResponse)
                }
            }
        }
        val document = firebaseFirestore.collection(FireStoreCollection.USER).document(user.userId)
        val userInfo = UserInfo(
            userId = user.userId,
            user.nickName,
            user.email,
            user.about,
            profilePhotoUrl
        )
        var result: ResponseState<UserInfo, String> =
            ResponseState.Success(
                userInfo
            )
        document
            .set(userInfo)
            .addOnSuccessListener {
                result = ResponseState.Success(userInfo)
            }
            .addOnFailureListener {
                result = ResponseState.Error(it.localizedMessage?.toString() ?: "Error")
            }.await()
        return result
    }

    suspend fun uploadProfilePhoto(userId:String,photoUri:Uri): ResponseState<String, String> {
        return try {
            var urlOfProfilePhoto = ""
            val imageRef =
                storageReference.child("images/${userId}/${STORAGE_PROFILE_IMAGE_FILENAME}")
            val uploadTask = imageRef.putFile(photoUri)

            uploadTask.addOnFailureListener {
            }.addOnSuccessListener { taskSnapshot ->

            }.continueWithTask {
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                urlOfProfilePhoto = task.result.toString()
            }.await()
            return ResponseState.Success(urlOfProfilePhoto)
        } catch (e: java.lang.Exception) {
            ResponseState.Error(e.toString())
        }

    }
}
