package com.ghitatomy.yooxshop.di

import com.ghitatomy.yooxshop.data.api.YooxApiService
import com.ghitatomy.yooxshop.data.repository.datasource.ItemsRemoteDataSource
import com.ghitatomy.yooxshop.data.repository.datasourceimplementation.ItemsRemoteDataSourceImpl
import com.ghitatomy.yooxshop.data.repository.mapper.ItemDetailsToDomainProductDetailsMapper
import com.ghitatomy.yooxshop.data.repository.mapper.ItemsItemToDomainProductMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteDataModule {
    @Singleton
    @Provides
    fun provideItemsRemoteDataSource(
        yooxApiService: YooxApiService,
        itemsItemToDomainProductMapper: ItemsItemToDomainProductMapper,
        itemDetailsToDomainProductDetailsMapper: ItemDetailsToDomainProductDetailsMapper
    ) : ItemsRemoteDataSource =
        ItemsRemoteDataSourceImpl(
            yooxApiService,
            itemsItemToDomainProductMapper,
            itemDetailsToDomainProductDetailsMapper
        )
}