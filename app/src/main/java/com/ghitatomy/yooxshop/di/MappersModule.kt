package com.ghitatomy.yooxshop.di

import com.ghitatomy.yooxshop.data.repository.mapper.ItemDetailsToDomainProductDetailsMapper
import com.ghitatomy.yooxshop.data.repository.mapper.ItemsItemToDomainProductMapper
import com.ghitatomy.yooxshop.domain.repository.mappers.DomainProductDetailsToProductDetailsUIMapper
import com.ghitatomy.yooxshop.domain.repository.mappers.DomainProductsToProductsUIMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {
    @Singleton
    @Provides
    fun provideItemsItemToDomainProductMapper() : ItemsItemToDomainProductMapper =
        ItemsItemToDomainProductMapper()

    @Singleton
    @Provides
    fun provideItemDetailsToDomainProductDetailsMapper() : ItemDetailsToDomainProductDetailsMapper =
        ItemDetailsToDomainProductDetailsMapper()

    @Singleton
    @Provides
    fun provideDomainProductsToProductsUIMapper() : DomainProductsToProductsUIMapper =
        DomainProductsToProductsUIMapper()

    @Singleton
    @Provides
    fun provideDomainProductDetailsToProductDetailsUIMapper() : DomainProductDetailsToProductDetailsUIMapper =
        DomainProductDetailsToProductDetailsUIMapper()
}