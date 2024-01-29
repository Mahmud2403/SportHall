package com.example.sportshall.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshall.domain.repository.SearchRepository
import com.example.sportshall.ui.screens.search.model.SportHallInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
	private val searchRepository: SearchRepository,
) : ViewModel() {

	private val _searchText = MutableStateFlow("")
	val searchText = _searchText.asStateFlow()

	private val _sportHallInformation = MutableStateFlow<List<SportHallInformation>>(emptyList())
	val sportHallInformation = _sportHallInformation.asStateFlow()

	init {
		viewModelScope.launch {
			_searchText
				.debounce(500)
				.collectLatest(::getSearchData)
		}
	}

	private suspend fun getSearchData(name: String) {
		searchRepository.searchSportHallInformation(name)
			.collect { data ->
				_sportHallInformation.update {
					data
				}
			}
	}

	fun changeSearchText(newValue: String) {
		_searchText.value = newValue
	}
}