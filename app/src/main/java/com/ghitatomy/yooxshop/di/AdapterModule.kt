package com.ghitatomy.yooxshop.di

import com.ghitatomy.yooxshop.presentation.adapters.ProductListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {
    @Singleton
    @Provides
    fun providesProductsListAdapter() : ProductListAdapter {
        return ProductListAdapter(mutableListOf())
    }
}