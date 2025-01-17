package com.todokanai.messagetest.di

import android.content.Context
import com.todokanai.messagetest.Model
import com.todokanai.messagetest.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context):DataStoreRepository{
        return DataStoreRepository(context)
    }

    @Singleton
    @Provides
    fun provideModel():Model{
        return Model()
    }

}