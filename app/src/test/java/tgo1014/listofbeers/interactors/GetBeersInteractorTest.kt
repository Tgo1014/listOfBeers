package tgo1014.listofbeers.interactors

import app.cash.turbine.test
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import tgo1014.listofbeers.extensions.UseCaseResult
import tgo1014.listofbeers.getService
import tgo1014.listofbeers.models.Beer
import tgo1014.listofbeers.network.PunkApi
import tgo1014.listofbeers.repositories.BeersRepository
import tgo1014.listofbeers.toJsonString

class GetBeersInteractorTest {

    private val mockWebServer = MockWebServer()
    private val instagramApi by lazy { mockWebServer.getService<PunkApi>() }
    private val beerRepository by lazy { BeersRepository(instagramApi) }
    private val interactor by lazy { GetBeersInteractor(beerRepository) }

    @Test
    fun `GIVEN a beer request is made WHEN it's success THEN beer list is returned`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setBody(listOf(Beer()).toJsonString()))
        interactor(1).test {
            val result = awaitItem()
            assert(result is UseCaseResult.Success)
            assert((result as UseCaseResult.Success).value.size == 1)
            awaitComplete()
        }
    }

    @Test
    fun `GIVEN a beer request is made WHEN it fails THEN error is returned`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setResponseCode(400))
        interactor(1).test {
            val result = awaitItem()
            assert(result is UseCaseResult.Error)
            awaitComplete()
        }
    }

}