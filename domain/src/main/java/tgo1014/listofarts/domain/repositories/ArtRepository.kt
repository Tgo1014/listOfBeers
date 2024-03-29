package tgo1014.listofarts.domain.repositories

import tgo1014.listofarts.domain.models.ArtObjectDomain

interface ArtRepository {

    suspend fun getArt(
        page: Int,
        query: String? = null,
        type: String? = null,
    ): List<ArtObjectDomain>

    suspend fun getArtById(
        id: String,
    ): ArtObjectDomain
}
