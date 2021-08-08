package com.android.aptoide.di

import com.android.aptoide.network.AppsRetrofit
import com.android.aptoide.network.repos.AppsListRepository
import com.android.mappers.network.AppNetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        retrofit: AppsRetrofit,
        networkMapper: AppNetworkMapper
    ): AppsListRepository {
        return AppsListRepository(retrofit, networkMapper)
    }

}