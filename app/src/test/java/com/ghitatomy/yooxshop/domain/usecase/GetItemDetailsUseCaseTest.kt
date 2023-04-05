package com.ghitatomy.yooxshop.domain.usecase

import com.ghitatomy.yooxshop.domain.repository.ItemsRepository
import com.ghitatomy.yooxshop.domain.response.DomainResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
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
class GetItemDetailsUseCaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getItemDetailsUseCase: GetItemDetailsUseCase

    lateinit var getItemDetailsUseCaseWithMock: GetItemDetailsUseCase

    @MockK
    private lateinit var itemsRepository: ItemsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        hiltRule.inject()
        getItemDetailsUseCaseWithMock = GetItemDetailsUseCase(itemsRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    // This test is making a real request, so you need internet connection for test to pass
    @Test
    fun `Give a code, When real request is made, Then size of response is correct`() = runBlocking {
        // GIVEN
        val code = "14200253HS"

        // WHEN
        val result = getItemDetailsUseCase.execute(code) as DomainResponse.Success

        // THEN
        assertNotNull(result.model)

    }

    @Test
    fun `When execute is called, Then execute method gets called`() = runBlocking {
        // GIVEN
        val code = "14200253HS"

        // WHEN
        getItemDetailsUseCaseWithMock.execute(code)

        // THEN
        coVerify(exactly = 1) { itemsRepository.getItemDetails(code) }
    }
}