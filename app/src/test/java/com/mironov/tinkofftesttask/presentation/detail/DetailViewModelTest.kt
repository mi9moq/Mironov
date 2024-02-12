package com.mironov.tinkofftesttask.presentation.detail

import app.cash.turbine.test
import com.mironov.tinkofftesttask.domain.usecase.GetFilmInfoByIdUseCase
import com.mironov.tinkofftesttask.navigation.router.DetailRouter
import com.mironov.tinkofftesttask.utils.FilmData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.net.UnknownHostException
import java.util.stream.Stream

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private val useCase: GetFilmInfoByIdUseCase = mock()
    private val router: DetailRouter = mock()
    private val viewModel = DetailViewModel(
        getFilmInfoByIdUseCase = useCase,
        router = router
    )

    private val film = FilmData.filmDetailInfo


    @BeforeEach
    fun setup(){
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `viewModel create EXPECT Initial State`() = runTest {

        assertEquals(DetailState.Initial, viewModel.state.value)
    }

    @Test
    fun `load film EXPECT Content state`() = runTest {
        whenever(useCase(1)) doSuspendableAnswer {
            delay(1)
            film
        }

        viewModel.state.test {
            viewModel.loadFilm(1)
            assertEquals(DetailState.Initial, awaitItem())
            assertEquals(DetailState.Loading, awaitItem())
            assertEquals(DetailState.Content(film), awaitItem())
        }
    }

    @ParameterizedTest
    @MethodSource("connectionError")
    fun `load film EXPECT Error state`(e: Exception) = runTest{
        whenever(useCase(1)) doSuspendableAnswer {
            delay(1)
            throw e
        }

        viewModel.state.test {
            viewModel.loadFilm(1)
            assertEquals(DetailState.Initial, awaitItem())
            assertEquals(DetailState.Loading, awaitItem())
            assertEquals(DetailState.Error, awaitItem())
        }
    }

    @Test
    fun `back EXPECT navigate back`() {

        viewModel.back()

        verify(router).back()
    }

    private companion object{

        @JvmStatic
        fun connectionError(): Stream<Exception> =Stream.of(
            UnknownHostException(),
            RuntimeException(),
            IllegalStateException()
        )
    }
}