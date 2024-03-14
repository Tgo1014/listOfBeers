package tgo1014.listofbeers.domain.usecases

import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import tgo1014.listofbeers.domain.fakes.FakeBeerRepository
import tgo1014.listofbeers.domain.fakes.FakeCoroutineProvider
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GetArtObjectsUseCaseTest {

    private lateinit var fakeBeerRepository: FakeBeerRepository
    private lateinit var usecase: GetArtObjectsUseCase

    private val testScope = TestScope()
    private val fakeCoroutineProvider = FakeCoroutineProvider(testScope)

    @BeforeTest
    fun setup() {
        fakeBeerRepository = FakeBeerRepository()
        usecase = GetArtObjectsUseCase(
            artRepository = fakeBeerRepository,
            coroutineProvider = fakeCoroutineProvider
        )
    }

    @Test
    fun `GIVEN a beer request is made WHEN it's success THEN beer list is returned`() =
        testScope.runTest {
            fakeBeerRepository.beersToReturn = listOf(BeerDomain())
            val result = usecase(1)
            assertTrue(result.isSuccess)
            assertTrue(result.getOrThrow().size == 1)
        }

    @Test
    fun `GIVEN a beer request is made WHEN it fails THEN error is returned`() = testScope.runTest {
        fakeBeerRepository.throwException = true
        val result = usecase(1)
        assertTrue(result.isFailure)
    }

    @Test
    fun `GIVEN a input with spaces WHEN usecase executed THEN spaces are replace with _`() =
        testScope.runTest {
            val input = "This is a input"
            assert(fakeBeerRepository.search == null)
            usecase(page = 1, search = input)
            assertEquals(input.replace(" ", "_"), fakeBeerRepository.search)
        }

    @Test
    fun `GIVEN a blank input WHEN usecase executed THEN query is null`() = testScope.runTest {
        val input = "                  "
        usecase(page = 1, search = input)
        assertNull(fakeBeerRepository.search)
    }
}