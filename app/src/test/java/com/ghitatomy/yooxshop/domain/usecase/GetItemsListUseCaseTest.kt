package com.ghitatomy.yooxshop.domain.usecase

import com.ghitatomy.yooxshop.domain.ItemSortType
import com.ghitatomy.yooxshop.domain.repository.ItemsRepository
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*

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
class GetItemsListUseCaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getItemsListUseCase: GetItemsListUseCase

    @MockK
    private lateinit var itemsRepository: ItemsRepository

    lateinit var getItemsListUseCaseWithMock: GetItemsListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        hiltRule.inject()
        getItemsListUseCaseWithMock = GetItemsListUseCase(itemsRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    // This test is making a real request, so you need internet connection for test to pass
    @Test
    fun `Given an sort type, When real request is made, Then size of response is correct`() = runBlocking {
        // GIVEN
        val sortType = ItemSortType.LATEST_ARRIVALS

        // WHEN
        val result = getItemsListUseCase.execute(sortType) as DomainResponse.Success

        // THEN
        assertEquals(result.model.size, 40)

    }

    @Test
    fun `Given an sort type, When execute is called, Then execute method gets called`() = runBlocking {
        // GIVEN
        val sortType = ItemSortType.LATEST_ARRIVALS

        // WHEN
        getItemsListUseCaseWithMock.execute(sortType)

        // THEN
        coVerify(exactly = 1) { itemsRepository.getAllItems(sortType) }

    }

}