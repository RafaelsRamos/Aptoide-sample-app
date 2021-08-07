package com.android.aptoide.di

import com.android.aptoide.cache.databases.AppDao
import com.android.aptoide.network.AppsRetrofit
import com.android.aptoide.network.repos.AppsListRepository
import com.android.mappers.cache.AppCacheMapper
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
        postDao: AppDao,
        retrofit: AppsRetrofit,
        cacheMapper: AppCacheMapper,
        networkMapper: AppNetworkMapper
    ): AppsListRepository {
        return AppsListRepository(postDao, retrofit, cacheMapper, networkMapper)
    }

}