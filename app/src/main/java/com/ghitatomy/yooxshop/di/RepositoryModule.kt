package com.ghitatomy.yooxshop.di

import com.ghitatomy.yooxshop.data.repository.ItemsRepositoryImpl
import com.ghitatomy.yooxshop.data.repository.datasource.ItemsRemoteDataSource
import com.ghitatomy.yooxshop.domain.repository.ItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideItemsRepository(
        itemsRemoteDataSource: ItemsRemoteDataSource,
    ) : ItemsRepository {
        return ItemsRepositoryImpl(itemsRemoteDataSource)
    }
}