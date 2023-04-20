package com.denisbovsunivskyi.animetier.presentation.di

import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSource
import com.denisbovsunivskyi.animetier.data.datasource.user.AuthDataSource
import com.denisbovsunivskyi.animetier.data.datasource.user.UserProfileDataSource
import com.denisbovsunivskyi.animetier.data.repository.anime.TrendingAnimeRepositoryImpl
import com.denisbovsunivskyi.animetier.data.repository.user.AuthRepositoryImpl
import com.denisbovsunivskyi.animetier.data.repository.user.ProfileRepositoryImpl
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository
import com.denisbovsunivskyi.animetier.domain.repository.ProfileRepository
import com.denisbovsunivskyi.animetier.domain.repository.TrendingAnimeRepository
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

    @Singleton
    @Provides
    fun provideProfileRepository(
        profileDataSource: UserProfileDataSource,
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileDataSource)
    }
    @Singleton
    @Provides
    fun provideTrendingAnimeRepository(
       animeDataSource: AnimeDataSource,
    ): TrendingAnimeRepository {
        return TrendingAnimeRepositoryImpl(animeDataSource)
    }
}