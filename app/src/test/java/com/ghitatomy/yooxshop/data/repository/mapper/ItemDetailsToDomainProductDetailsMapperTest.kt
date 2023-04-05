package com.ghitatomy.yooxshop.data.repository.mapper

import com.ghitatomy.yooxshop.data.model.*
import com.ghitatomy.yooxshop.domain.model.DomainProductDetails
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
class ItemDetailsToDomainProductDetailsMapperTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var mapper: ItemDetailsToDomainProductDetailsMapper

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `Given an item description with normal values, When mapped, Then return correct result`() {
        // GIVEN
        val cod = "124234"
        val brand = Brand("Gucci")
        val sizes = listOf(SizesItem("23"), SizesItem("24"))
        val colors = listOf(ColorsItem("258"))
        val category = Category("Pants")

        val description1 = "Some Title: Some description"
        val description2 = "Title: description"
        val itemDescriptions = ItemDescriptions(listOf(description1, description2))

        val fullPrice = "1242"
        val discountedPrice = "1230"
        val formattedPrice = FormattedPrice(fullPrice, discountedPrice)

        val item = ItemDetailsResponse(cod, brand, sizes, colors, category, itemDescriptions, formattedPrice)

        // WHEN
        val result: DomainProductDetails = mapper.map(item)

        assertEquals(result.brandName, brand.name)
        assertEquals(result.categoryName, category.name)
        assertEquals(result.code, cod)
        assertEquals(result.productInfo, itemDescriptions.productInfo)
        assertEquals(result.colors, colors)
        assertEquals(result.sizes, sizes)
        assertEquals(result.price, formattedPrice.discountedPrice)
    }

    @Test
    fun `Given an item description with default values, When mapped, Then return correct result`() {
        // GIVEN
        val item = ItemDetailsResponse(
            brand = Brand(),
            category = Category(),
            itemDescriptions = ItemDescriptions(emptyList()),
            formattedPrice = FormattedPrice(),
            colors = null,
            sizes = null
        )

        // WHEN
        val result: DomainProductDetails = mapper.map(item)

        assertEquals(result.brandName, "")
        assertEquals(result.categoryName, "")
        assertEquals(result.code, "")
        assertEquals(result.productInfo, emptyList<String>())
        assertEquals(result.colors, emptyList<ColorsItem>())
        assertEquals(result.sizes, emptyList<SizesItem>())
        assertEquals(result.price, "")
    }

    @Test
    fun `Given an list of items with normal values, When mapped, Then return correct result`() {
        // GIVEN
        val cod = "124234"
        val brand = Brand("Gucci")
        val sizes = listOf(SizesItem("23"), SizesItem("24"))
        val colors = listOf(ColorsItem("258"))
        val category = Category("Pants")

        val description1 = "Some Title: Some description"
        val description2 = "Title: description"
        val itemDescriptions = ItemDescriptions(listOf(description1, description2))

        val fullPrice = "1242"
        val discountedPrice = "1230"
        val formattedPrice = FormattedPrice(fullPrice, discountedPrice)

        val item1 = ItemDetailsResponse(cod, brand, sizes, colors, category, itemDescriptions, formattedPrice)
        val item2 = ItemDetailsResponse(
            brand = Brand(),
            category = Category(),
            itemDescriptions = ItemDescriptions(emptyList()),
            formattedPrice = FormattedPrice(),
            colors = null,
            sizes = null
        )
        val itemsList = listOf(item1, item2)

        // WHEN
        val listResult: List<DomainProductDetails> = mapper.map(itemsList)

        // THEN
        val result = listResult.first()
        assertEquals(result.brandName, brand.name)
        assertEquals(result.categoryName, category.name)
        assertEquals(result.code, cod)
        assertEquals(result.productInfo, itemDescriptions.productInfo)
        assertEquals(result.colors, colors)
        assertEquals(result.sizes, sizes)
        assertEquals(result.price, formattedPrice.discountedPrice)

        val result1 = listResult.last()
        assertEquals(result1.brandName, "")
        assertEquals(result1.categoryName, "")
        assertEquals(result1.code, "")
        assertEquals(result1.productInfo, emptyList<String>())
        assertEquals(result1.colors, emptyList<ColorsItem>())
        assertEquals(result1.sizes, emptyList<SizesItem>())
        assertEquals(result1.price, "")

        assertEquals(listResult.size, 2)

    }

    @Test
    fun `Given an list with no items, When mapped, Then return and empty list of results`() {
        // GIVEN
        val itemsList = listOf<ItemDetailsResponse>()

        // WHEN
        val listResult: List<DomainProductDetails> = mapper.map(itemsList)

        // THEN
        assertEquals(listResult.size, 0)
        assertEquals(listResult, emptyList<DomainProductDetails>())
    }
}