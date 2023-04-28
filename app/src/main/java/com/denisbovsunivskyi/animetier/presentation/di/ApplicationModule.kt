package com.denisbovsunivskyi.animetier.presentation.di

import com.denisbovsunivskyi.animetier.core.utils.validation.AndroidMailMatcher
import com.denisbovsunivskyi.animetier.domain.utils.MailMatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun provideMailMatcher(): MailMatcher = AndroidMailMatcher()
}