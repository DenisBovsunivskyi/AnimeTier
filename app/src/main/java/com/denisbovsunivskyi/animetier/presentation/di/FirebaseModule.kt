package com.denisbovsunivskyi.animetier.presentation.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    @Singleton
    fun provideDatabaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}
