package com.denisbovsunivskyi.animetier.presentation.di.use_case

import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository
import com.denisbovsunivskyi.animetier.domain.repository.ProfileRepository
import com.denisbovsunivskyi.animetier.domain.usecase.auth.AuthUserUseCase
import com.denisbovsunivskyi.animetier.domain.usecase.auth.CreateUserInRemoteDbUseCase
import com.denisbovsunivskyi.animetier.domain.usecase.auth.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUserUseCase {
        return AuthUserUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(authRepository: AuthRepository): RegisterUserUseCase {
        return RegisterUserUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideCreateUserInRemoteDbUseCase(profileRepository: ProfileRepository): CreateUserInRemoteDbUseCase {
        return CreateUserInRemoteDbUseCase(profileRepository)
    }


}