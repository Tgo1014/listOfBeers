package tgo1014.listofbeers.presentation.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tgo1014.listofbeers.domain.usecases.GetBeerByIdUseCase
import tgo1014.listofbeers.presentation.models.mappers.toUi
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBeerByIdUseCase: GetBeerByIdUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<DetailsState> = MutableStateFlow(DetailsState.Loading)
    val state = _state.asStateFlow()

    fun getBeerById(id: Int) = viewModelScope.launch {
        _state.update { DetailsState.Loading }
        getBeerByIdUseCase(id)
            .onSuccess { beer -> _state.update { DetailsState.Success(beer.toUi()) } }
            .onFailure { _state.update { DetailsState.Error } }
    }

}