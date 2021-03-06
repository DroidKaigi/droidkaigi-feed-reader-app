package io.github.droidkaigi.feeder

import io.github.droidkaigi.feeder.data.FeedRepositoryImpl
import io.github.droidkaigi.feeder.data.fakeFeedApi
import io.github.droidkaigi.feeder.data.fakeFeedItemDao
import io.github.droidkaigi.feeder.data.fakeUserDataStore
import io.github.droidkaigi.feeder.feed.FeedViewModel
import io.github.droidkaigi.feeder.feed.FeedViewModel.Event.ChangeFavoriteFilter
import io.github.droidkaigi.feeder.feed.FeedViewModel.Event.ToggleFavorite
import io.github.droidkaigi.feeder.feed.fakeFeedViewModel
import io.github.droidkaigi.feeder.viewmodel.RealFeedViewModel
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@InternalCoroutinesApi
@RunWith(Parameterized::class)
class FeedViewModelTest(
    @Suppress("unused")
    val name: String,
    private val feedViewModelFactory: FeedViewModelFactory,
) {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun contents() = coroutineTestRule.scope.runTest {
        // Replace when it fixed https://github.com/cashapp/turbine/issues/10
        val feedViewModel = feedViewModelFactory.create()
        advanceUntilIdle()

        val firstContent = feedViewModel.state.value.filteredFeedContents

        firstContent.size shouldBeGreaterThan 1
    }

    @Test
    fun favorite_Add() = coroutineTestRule.scope.runTest {
        val feedViewModel = feedViewModelFactory.create()
        advanceUntilIdle()
        val firstContent = feedViewModel.state.value.filteredFeedContents
        firstContent.favorites shouldBe setOf()

        feedViewModel.event(ToggleFavorite(firstContent.feedItemContents[0]))
        advanceUntilIdle()

        val secondContent = feedViewModel.state.value.filteredFeedContents
        secondContent.favorites shouldBe setOf(firstContent.feedItemContents[0].id)
    }

    @Test
    fun favorite_Remove() = coroutineTestRule.scope.runTest {
        val feedViewModel = feedViewModelFactory.create()
        advanceUntilIdle()
        val firstContent = feedViewModel.state.value.filteredFeedContents
        firstContent.favorites shouldBe setOf()

        feedViewModel.event(ToggleFavorite(feedItem = firstContent.feedItemContents[0]))
        advanceUntilIdle()
        feedViewModel.event(ToggleFavorite(feedItem = firstContent.feedItemContents[0]))
        advanceUntilIdle()

        val secondContent = feedViewModel.state.value.filteredFeedContents
        secondContent.favorites shouldBe setOf()
    }

    @Test
    fun favorite_Filter() = coroutineTestRule.scope.runTest {
        val feedViewModel = feedViewModelFactory.create()
        advanceUntilIdle()
        val firstContent = feedViewModel.state.value.filteredFeedContents
        firstContent.favorites shouldBe setOf()
        val favoriteContents = firstContent.feedItemContents[1]

        feedViewModel.event(ToggleFavorite(feedItem = favoriteContents))
        advanceUntilIdle()
        feedViewModel.event(ChangeFavoriteFilter(Filters(filterFavorite = true)))
        advanceUntilIdle()

        val secondContent = feedViewModel.state.value.filteredFeedContents
        secondContent.contents[0].first.id shouldBe favoriteContents.id
    }

    @Test
    fun errorWhenFetch() = coroutineTestRule.scope.runTest {
        val feedViewModel = feedViewModelFactory.create(errorFetchData = true)
        val firstContent = feedViewModel.state.value.filteredFeedContents
        firstContent.favorites shouldBe setOf()

        val firstEffect = feedViewModel.effect.first()

        firstEffect.shouldBeInstanceOf<FeedViewModel.Effect.ErrorMessage>()
    }

    class FeedViewModelFactory(
        private val viewModelFactory: (errorFetchData: Boolean) ->
        FeedViewModel,
    ) {
        fun create(
            errorFetchData: Boolean = false,
        ): FeedViewModel {
            return viewModelFactory(errorFetchData)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data() = listOf(
            arrayOf(
                "Real ViewModel and Repository",
                FeedViewModelFactory { errorFetchData: Boolean ->
                    RealFeedViewModel(
                        feedRepository = FeedRepositoryImpl(
                            feedApi = fakeFeedApi(
                                if (errorFetchData) {
                                    AppError.ApiException.ServerException(null)
                                } else {
                                    null
                                }
                            ),
                            feedItemDao = fakeFeedItemDao(
                                if (errorFetchData) {
                                    AppError.UnknownException(Throwable("Database Exception"))
                                } else {
                                    null
                                }
                            ),
                            dataStore = fakeUserDataStore()
                        )
                    )
                }
            ),
            arrayOf(
                "FakeViewModel",
                FeedViewModelFactory { errorFetchData: Boolean ->
                    fakeFeedViewModel(errorFetchData = errorFetchData)
                }
            )
        )
    }
}
