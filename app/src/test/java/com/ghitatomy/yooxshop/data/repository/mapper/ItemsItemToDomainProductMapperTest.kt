package com.ghitatomy.yooxshop.data.repository.mapper

import com.ghitatomy.yooxshop.data.model.ItemsItem
import com.ghitatomy.yooxshop.domain.model.DomainProduct
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ItemsItemToDomainProductMapperTest{
    private lateinit var mapper: ItemsItemToDomainProductMapper

    @Before
    fun setUp() { mapper = ItemsItemToDomainProductMapper() }

    @Test
    fun `Given an item with normal values, When mapped, Then return correct result`() {
        // GIVEN
        val formattedDiscountedPrice = "1234"
        val cod = "lkj23223"
        val formattedFullPrice = "1250"
        val microCategoryPlural = "Pants"
        val brand = "DolceGabana"
        val microCategory = "Pant"

        val item = ItemsItem(formattedDiscountedPrice, cod, formattedFullPrice, microCategoryPlural, brand, microCategory)

        // WHEN
        val result = mapper.map(item)

        // THEN
        assertEquals(result.brandName, brand)
        assertEquals(result.category, microCategory)
        assertEquals(result.code, cod)
        assertEquals(result.price, formattedDiscountedPrice)
    }

    @Test
    fun `Given an item with default values, When mapped, Then return correct result`() {
        // GIVEN
        val item = ItemsItem()

        // WHEN
        val result = mapper.map(item)

        // THEN
        assertEquals(result.brandName, "")
        assertEquals(result.category, "")
        assertEquals(result.code, "")
        assertEquals(result.price, "")
    }

    @Test
    fun `Given an list of items with normal values, When mapped, Then return correct result`() {
        // GIVEN
        val formattedDiscountedPrice = "1234"
        val cod = "lkj23223"
        val formattedFullPrice = "1250"
        val microCategoryPlural = "Pants"
        val brand = "DolceGabana"
        val microCategory = "Pant"
        val item = ItemsItem(formattedDiscountedPrice, cod, formattedFullPrice, microCategoryPlural, brand, microCategory)
        val item1 = ItemsItem()
        val itemsList = listOf(item,item1)

        // WHEN
        val resultList = mapper.map(itemsList)

        // THEN
        val result = resultList.first()
        assertEquals(result.brandName, brand)
        assertEquals(result.category, microCategory)
        assertEquals(result.code, cod)
        assertEquals(result.price, formattedDiscountedPrice)

        val result1 = resultList.last()
        assertEquals(result1.brandName, "")
        assertEquals(result1.category, "")
        assertEquals(result1.code, "")
        assertEquals(result1.price, "")

        assertEquals(resultList.size, 2)
    }

    @Test
    fun `Given an list with no items, When mapped, Then return and empty list of results`() {
        // GIVEN
        val itemsList = listOf<ItemsItem>()

        // WHEN
        val listResult: List<DomainProduct> = mapper.map(itemsList)

        // THEN
        assertEquals(listResult.size, 0)
        assertEquals(listResult, emptyList<DomainProduct>())
    }
}