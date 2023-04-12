package com.denisbovsunivskyi.animetier.presentation.di

import com.denisbovsunivskyi.animetier.domain.common.MailMatcher
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository
import com.denisbovsunivskyi.animetier.domain.usecase.auth.AuthUserUseCase
import com.denisbovsunivskyi.animetier.domain.usecase.auth.RegisterUserUseCase
import com.denisbovsunivskyi.animetier.domain.usecase.validation.ConfirmPasswordValidation
import com.denisbovsunivskyi.animetier.domain.usecase.validation.EmailValidation
import com.denisbovsunivskyi.animetier.domain.usecase.validation.PasswordValidation
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
    fun provideValidateEmailUseCase(mailMatcher: MailMatcher): EmailValidation {
        return EmailValidation(mailMatcher)
    }

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): PasswordValidation {
        return PasswordValidation()
    }
    @Provides
    @Singleton
    fun provideValidateConfirmPasswordUseCase(): ConfirmPasswordValidation {
        return ConfirmPasswordValidation()
    }
}