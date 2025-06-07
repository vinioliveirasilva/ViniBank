package com.vini.bank

import android.app.Activity
import androidx.activity.result.ActivityResult
import com.vini.storage.SessionStorage
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class LauncherViewModelTest {
    private var sessionStorage: SessionStorage = mockk(relaxed = true)
    private lateinit var viewModel: LauncherViewModel

    @Before
    fun setUp() {
        sessionStorage = mockk()
        viewModel = LauncherViewModel(sessionStorage)
    }

    @Test
    fun `should emit OpenHome when user is authenticated`() = runTest {
        every { sessionStorage.isAuthenticated() } returns true

        viewModel.event.test {
            viewModel.doOnCreate()
            assertEquals(LauncherUIEvent.OpenHome, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

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
}