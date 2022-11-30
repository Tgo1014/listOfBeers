package tgo1014.listofbeers.models

data class FilterState(val filter: Filter, val isSelected: Boolean) {
    companion object {
        val Empty = Filter.values().map { FilterState(it, false) }
    }
}