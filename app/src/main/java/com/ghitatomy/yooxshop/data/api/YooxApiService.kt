package com.ghitatomy.yooxshop.data.api

import com.ghitatomy.yooxshop.data.model.ItemDetailsResponse
import com.ghitatomy.yooxshop.data.model.ItemsListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface YooxApiService {
    @GET("plp/page/{page}.json")
    suspend fun getAllItems(@Path("page") page: Long) : ItemsListResponse

    @GET("pdp/49724066HM.json")
    suspend fun getItemDetails(): ItemDetailsResponse

    @GET("plp/sorted/latest.json")
    suspend fun getLatestItems(): ItemsListResponse

    @GET("plp/sorted/lowest.json")
    suspend fun getLowPriceItems(): ItemsListResponse

    @GET("plp/sorted/highest.json")
    suspend fun getHighPriceItems(): ItemsListResponse

}