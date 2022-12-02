package tgo1014.listofbeers.data.usecases

import kotlinx.coroutines.withContext
import tgo1014.listofbeers.domain.CoroutineProvider
import tgo1014.listofbeers.domain.repositories.BeersRepository
import tgo1014.listofbeers.domain.usecases.GetBeerByIdUseCase
import javax.inject.Inject

class GetBeerByIdUseCaseImpl @Inject constructor(
    private val beersRepository: BeersRepository,
    private val coroutineProvider: CoroutineProvider,
) : GetBeerByIdUseCase {

    override suspend operator fun invoke(id: Int) = withContext(coroutineProvider.io) {
        runCatching { beersRepository.getBeerById(id) }
    }

}