package com.denisbovsunivskyi.animetier.presentation.di

import com.denisbovsunivskyi.animetier.data.datasource.user.AuthDataSource
import com.denisbovsunivskyi.animetier.data.repository.user.AuthRepositoryImpl
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
    ): AuthRepository {
        return AuthRepositoryImpl(authDataSource)
    }
}