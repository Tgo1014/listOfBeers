package tgo1014.listofbeers.ui.screens.home

import tgo1014.listofbeers.models.Beer
import java.util.Date

data class HomeState(
    val beerList: List<Beer> = emptyList(),
    val afterFilter: Date? = null,
    val beforeFilter: Date? = null,
    val isCalendarAfterOpen: Boolean = false,
    val isCalendarBeforeOpen: Boolean = false,
)