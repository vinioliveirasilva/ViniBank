package com.vini.bank

import com.vini.storage.SessionStorage
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description


@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class LauncherViewModelTest {
    private var sessionStorage: SessionStorage = mockk(relaxed = true)
    private lateinit var viewModel: LauncherViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = LauncherViewModel(
            sessionStorage = sessionStorage,
            initializers = emptyList()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should emit OpenHome when user is authenticated`() = runTest {
        every { sessionStorage.isAuthenticated() } returns true

        val events = mutableListOf<LauncherUIEvent>()
        viewModel.event.collect {
            events.add(it)
        }

        viewModel.doOnCreate()
        advanceUntilIdle()
        assertTrue(events.contains(LauncherUIEvent.OpenHome))
        cancel()
    }
    /*

    @Test
    fun `should emit OpenLogin when user is not authenticated`() = runTest {
        every { sessionStorage.isAuthenticated() } returns false

        viewModel.event.test {
            viewModel.doOnCreate()
            assertEquals(LauncherUIEvent.OpenLogin, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should emit OpenHome when login activity result is OK`() = runTest {
        val activityResult = ActivityResult(Activity.RESULT_OK, null)

        viewModel.event.test {
            viewModel.doOnLoginRouteResult(activityResult)
            assertEquals(LauncherUIEvent.OpenHome, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should emit Finish when login activity result is not OK`() = runTest {
        val activityResult = ActivityResult(Activity.RESULT_CANCELED, null)

        viewModel.event.test {
            viewModel.doOnLoginRouteResult(activityResult)
            assertEquals(LauncherUIEvent.Finish, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

     */
}