package io.github.droidkaigi.feeder

import io.github.droidkaigi.feeder.data.StaffRepositoryImpl
import io.github.droidkaigi.feeder.data.fakeStaffApi
import io.github.droidkaigi.feeder.staff.StaffViewModel
import io.github.droidkaigi.feeder.staff.fakeStaffViewModel
import io.github.droidkaigi.feeder.viewmodel.RealStaffViewModel
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@InternalCoroutinesApi
@RunWith(Parameterized::class)
class StaffViewModelTest(
    val name: String,
    private val staffViewModelFactory: StaffViewModelFactory,
) {

    @get:Rule
    val coroutineTestRule = DeprecatedCoroutineTestRule()

    @Test
    fun contents() = coroutineTestRule.testDispatcher.runBlockingTest {
        val staffViewModel = staffViewModelFactory.create()
        val staffContents = staffViewModel.state.value.staffContents
        staffContents.size shouldBeGreaterThan 1
    }

    @Test
    fun errorWhenFetch() = coroutineTestRule.testDispatcher.runBlockingTest {
        val staffViewModel = staffViewModelFactory.create(errorFetchData = true)
        val firstEffect = staffViewModel.effect.first()
        firstEffect.shouldBeInstanceOf<StaffViewModel.Effect.ErrorMessage>()
    }

    class StaffViewModelFactory(
        private val viewModelFactory: (errorFetchData: Boolean) ->
        StaffViewModel,
    ) {
        fun create(
            errorFetchData: Boolean = false,
        ): StaffViewModel {
            return viewModelFactory(errorFetchData)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data() = listOf(
            arrayOf(
                "Real ViewModel and Repository",
                StaffViewModelFactory { errorFetchData: Boolean ->
                    RealStaffViewModel(
                        repository = StaffRepositoryImpl(
                            staffApi = fakeStaffApi(
                                if (errorFetchData) {
                                    AppError.ApiException.ServerException(null)
                                } else {
                                    null
                                }
                            )
                        )
                    )
                }
            ),
            arrayOf(
                "FakeViewModel",
                StaffViewModelFactory { errorFetchData: Boolean ->
                    fakeStaffViewModel(errorFetchData = errorFetchData)
                }
            )
        )
    }
}
