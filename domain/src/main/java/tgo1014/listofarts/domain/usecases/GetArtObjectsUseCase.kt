package tgo1014.listofarts.domain.usecases

import kotlinx.coroutines.withContext
import tgo1014.listofarts.domain.CoroutineProvider
import tgo1014.listofarts.domain.models.ArtObjectDomain
import tgo1014.listofarts.domain.repositories.ArtRepository
import javax.inject.Inject

class GetArtObjectsUseCase @Inject constructor(
    private val artRepository: ArtRepository,
    private val coroutineProvider: CoroutineProvider,
) {

    suspend operator fun invoke(
        page: Int,
        query: String? = null,
        type: String? = null,
    ): Result<List<ArtObjectDomain>> = runCatching {
        val searchQuery = if (query.isNullOrBlank()) {
            null
        } else {
            query.replace(" ", "_")
        }
        withContext(coroutineProvider.io) {
            artRepository.getArt(page = page, query = searchQuery, type = type)
        }
    }
}
