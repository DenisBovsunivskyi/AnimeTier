package com.denisbovsunivskyi.animetier.presentation.di

import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeAllPagingSource
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSource
import com.denisbovsunivskyi.animetier.data.datasource.user.AuthDataSource
import com.denisbovsunivskyi.animetier.data.datasource.user.UserProfileDataSource
import com.denisbovsunivskyi.animetier.data.repository.anime.AnimeRepositoryImpl
import com.denisbovsunivskyi.animetier.data.repository.anime.TrendingAnimeRepositoryImpl
import com.denisbovsunivskyi.animetier.data.repository.user.AuthRepositoryImpl
import com.denisbovsunivskyi.animetier.data.repository.user.ProfileRepositoryImpl
import com.denisbovsunivskyi.animetier.domain.repository.anime.AnimeRepository
import com.denisbovsunivskyi.animetier.domain.repository.auth.AuthRepository
import com.denisbovsunivskyi.animetier.domain.repository.auth.ProfileRepository
import com.denisbovsunivskyi.animetier.domain.repository.anime.TrendingAnimeRepository
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
    @Singleton
    @Provides
    fun provideAnimeRepository(
        animePagingDataSource: AnimeAllPagingSource,
        animeDataSource: AnimeDataSource,

        ): AnimeRepository {
        return AnimeRepositoryImpl(animePagingDataSource,animeDataSource)
    }
}