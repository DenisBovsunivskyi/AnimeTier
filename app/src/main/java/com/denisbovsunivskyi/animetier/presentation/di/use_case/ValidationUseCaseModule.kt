package com.denisbovsunivskyi.animetier.presentation.di.use_case

import com.denisbovsunivskyi.animetier.domain.utils.MailMatcher
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
class ValidationUseCaseModule {
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