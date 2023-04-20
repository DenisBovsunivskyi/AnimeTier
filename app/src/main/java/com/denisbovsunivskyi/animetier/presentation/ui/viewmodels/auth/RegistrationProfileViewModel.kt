package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.Event
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.data.models.user.UserProfileModelDto
import com.denisbovsunivskyi.animetier.domain.usecase.auth.CreateUserInRemoteDbUseCase
import com.denisbovsunivskyi.animetier.presentation.model.user.ProfileRegistrationModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationProfileViewModel @Inject constructor(
    private val createUserInRemoteDbUseCase: CreateUserInRemoteDbUseCase
) :
    ViewModel() {
    var registrationProfileModel: ProfileRegistrationModel = ProfileRegistrationModel()
    private var userEmail: String = ""
    private var userId: String = ""

    private val mRegistrationProfileEventLiveData: MutableLiveData<Event<RegistrationProfileActions>> =
        MutableLiveData<Event<RegistrationProfileActions>>()


    fun getEventLiveData(): LiveData<Event<RegistrationProfileActions>> {
        return mRegistrationProfileEventLiveData
    }

    init {
        userEmail = Firebase.auth.currentUser?.email.toString()
        userId = Firebase.auth.currentUser?.uid.toString()
        if (userId.isEmpty() || userEmail.isEmpty()) {
            mRegistrationProfileEventLiveData.postValue(
                Event(RegistrationProfileActions.Failed.NoUserCredentialsAvailable())
            )
        }
    }

    private fun createNewUser() {
        viewModelScope.launch(Dispatchers.IO) {
            mRegistrationProfileEventLiveData.postValue(Event(RegistrationProfileActions.Loading))
            val result = createUserInRemoteDbUseCase.execute(
                UserProfileModelDto(
                    userId = userId,
                    nickName = registrationProfileModel.nickname.get() ?: "",
                    email = userEmail,
                    about = registrationProfileModel.about.get() ?: "",
                    photo = registrationProfileModel.photo
                )
            )

            when (result) {
                is ResponseState.Success -> mRegistrationProfileEventLiveData.postValue(
                    Event(
                        RegistrationProfileActions.Success.RegistrationSuccess
                    )
                )
                is ResponseState.Error -> mRegistrationProfileEventLiveData.postValue(
                    Event(RegistrationProfileActions.Failed.RegistrationFailed(result.rawResponse))
                )
            }
        }
    }

    fun validateFields() {
        if (registrationProfileModel.nickname.get()?.isNotEmpty() == true) {
            createNewUser()
        } else {
            mRegistrationProfileEventLiveData.postValue(Event(RegistrationProfileActions.Failed.NickNameIsEmpty()))
        }
    }
}

sealed class RegistrationProfileActions {
    object Loading : RegistrationProfileActions()
    sealed class Success : RegistrationProfileActions() {
        object RegistrationSuccess : RegistrationProfileActions()
    }

    sealed class Failed : RegistrationProfileActions() {
        data class RegistrationFailed(val message: UniversalText) : RegistrationProfileActions()
        data class NoUserCredentialsAvailable(val message: UniversalText = UniversalText.Resource(R.string.error_user_is_unauthorized)) :
            RegistrationProfileActions()

        data class NickNameIsEmpty(val message: UniversalText = UniversalText.Resource(R.string.error_nick_name_is_empty)) :
            RegistrationProfileActions()
    }
}