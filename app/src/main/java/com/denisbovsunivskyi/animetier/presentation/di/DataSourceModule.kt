package com.denisbovsunivskyi.animetier.presentation.di

import android.content.Context
import com.denisbovsunivskyi.animetier.data.api.AnimeServiceApi
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeAllPagingSource
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSource
import com.denisbovsunivskyi.animetier.data.datasource.anime.AnimeDataSourceImpl
import com.denisbovsunivskyi.animetier.data.datasource.user.AuthDataSource
import com.denisbovsunivskyi.animetier.data.datasource.user.AuthDataSourceImpl
import com.denisbovsunivskyi.animetier.data.datasource.user.UserProfileDataSource
import com.denisbovsunivskyi.animetier.data.datasource.user.UserProfileDataSourceImpl
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideAuthDataSource(@ApplicationContext context: Context): AuthDataSource {
        return AuthDataSourceImpl(
            Firebase.auth,
            context
        )
    }

    @Provides
    @Singleton
    fun provideUpdateUserDataSource(firebaseFirestore: FirebaseFirestore,storageReference: StorageReference): UserProfileDataSource {
        return UserProfileDataSourceImpl(firebaseFirestore,storageReference)
    }
    @Provides
    @Singleton
    fun provideAnimeDataSource(animeServiceApi: AnimeServiceApi): AnimeDataSource {
        return AnimeDataSourceImpl(animeServiceApi)
    }
    @Provides
    @Singleton
    fun providePagingAnimeDataSource(animeServiceApi: AnimeServiceApi): AnimeAllPagingSource {
        return AnimeAllPagingSource(animeServiceApi)
    }
}