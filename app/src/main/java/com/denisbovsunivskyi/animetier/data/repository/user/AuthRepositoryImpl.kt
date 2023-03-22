package com.denisbovsunivskyi.animetier.data.repository.user

import com.denisbovsunivskyi.animetier.data.datasource.user.AuthDataSource
import com.denisbovsunivskyi.animetier.data.datasource.user.UpdateUserDataSource
import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val userDataSource: UpdateUserDataSource
) : AuthRepository {
    override suspend fun authUserByEmail(
        email: String,
        password: String,
    ): Flow<ResponseState<FirebaseUser,String>>{
      return authDataSource.authUserWithEmail(email, password)
    }

    override suspend fun registerUserByEmail(model: FirebaseUser): Flow<ResponseState<FirebaseUser,String>> {
        return flow {
            var result = authDataSource.registerUserWithEmail(model)
            result.collect { response ->
                when (response){
                    is ResponseState.Success -> {
                        userDataSource.updateUserWhileRegister(response.data).collect{
                            emit(it)
                        }
                    }
                    is ResponseState.Error ->{
                        emit(response)
                    }
                }
            }
        }
    }
}