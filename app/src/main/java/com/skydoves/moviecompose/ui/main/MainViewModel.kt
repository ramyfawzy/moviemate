/*
 * Designed and developed by 2021 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.moviecompose.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.skydoves.moviecompose.models.entities.Movie
import com.skydoves.moviecompose.models.entities.Person
import com.skydoves.moviecompose.models.entities.Tv
import com.skydoves.moviecompose.models.network.NetworkState
import com.skydoves.moviecompose.repository.DiscoverRepository
import com.skydoves.moviecompose.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  val imageLoader: ImageLoader,
  private val discoverRepository: DiscoverRepository,
  private val peopleRepository: PeopleRepository,
) : ViewModel() {

  private val _selectedTab: MutableState<MainScreenHomeTab> =
    mutableStateOf(MainScreenHomeTab.MOVIE)
  val selectedTab: State<MainScreenHomeTab> get() = _selectedTab

  private val _movieLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
  val movieLoadingState: State<NetworkState> get() = _movieLoadingState

  private val _movies: MutableState<List<Movie>> = mutableStateOf(listOf())
  val movies: State<List<Movie>> get() = _movies

  val moviePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val newMovieFlow = moviePageStateFlow.flatMapLatest {
    _movieLoadingState.value = NetworkState.LOADING
    discoverRepository.loadMovies(
      page = it,
      success = { _movieLoadingState.value = NetworkState.SUCCESS },
      error = { _movieLoadingState.value = NetworkState.ERROR },
    )
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  private val _tvLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
  val tvLoadingState: State<NetworkState> get() = _tvLoadingState

  private val _tvs: MutableState<List<Tv>> = mutableStateOf(listOf())
  val tvs: State<List<Tv>> get() = _tvs

  val tvPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val newTvFlow = tvPageStateFlow.flatMapLatest {
    _tvLoadingState.value = NetworkState.LOADING
    discoverRepository.loadTvs(
      page = it,
      success = { _tvLoadingState.value = NetworkState.SUCCESS },
      error = { _tvLoadingState.value = NetworkState.ERROR },
    )
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  private val _personLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
  val personLoadingState: State<NetworkState> get() = _personLoadingState

  private val _people: MutableState<List<Person>> = mutableStateOf(listOf())
  val people: State<List<Person>> get() = _people

  val peoplePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val newPeople = peoplePageStateFlow.flatMapLatest {
    _personLoadingState.value = NetworkState.LOADING
    peopleRepository.loadPeople(
      page = it,
      success = { _personLoadingState.value = NetworkState.SUCCESS },
      error = { _personLoadingState.value = NetworkState.ERROR },
    )
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  init {
    viewModelScope.launch(Dispatchers.IO) {
      newMovieFlow.collectLatest {
        _movies.value = _movies.value + it
      }
    }

    viewModelScope.launch(Dispatchers.IO) {
      newTvFlow.collectLatest {
        _tvs.value = _tvs.value + it
      }
    }

    viewModelScope.launch(Dispatchers.IO) {
      newPeople.collectLatest {
        _people.value = _people.value + it
      }
    }
  }

  fun selectTab(tab: MainScreenHomeTab) {
    _selectedTab.value = tab
  }

  fun fetchNextMoviePage() {
    if (movieLoadingState.value != NetworkState.LOADING) {
      moviePageStateFlow.value++
    }
  }

  fun fetchNextTvPage() {
    if (tvLoadingState.value != NetworkState.LOADING) {
      tvPageStateFlow.value++
    }
  }

  fun fetchNextPeoplePage() {
    if (personLoadingState.value != NetworkState.LOADING) {
      peoplePageStateFlow.value++
    }
  }

  fun insertMovie(movie: Movie, success: () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
      discoverRepository.insertMovie(
        movie = movie,
        success = {
          _movies.value = listOf(movie) + _movies.value
          viewModelScope.launch(Dispatchers.Main) {
            success()
          }
        },
        error = { }
      )
    }
  }
}
