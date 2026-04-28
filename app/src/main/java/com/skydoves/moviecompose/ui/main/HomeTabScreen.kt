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

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.skydoves.moviecompose.ui.movie.MovieScreen
import com.skydoves.moviecompose.ui.people.PeopleScreen
import com.skydoves.moviecompose.ui.theme.MovieComposeTheme
import com.skydoves.moviecompose.ui.theme.purple200
import com.skydoves.moviecompose.ui.tv.TvScreen

@Composable
fun HomeTabScreen(
  viewModel: MainViewModel,
  tabStateHolder: HomeTabStateHolder,
  selectItem: (MainScreenHomeTab, Long) -> Unit,
) {
  val selectedTab by viewModel.selectedTab

  HomeTabScreen(
    selectedTab = selectedTab,
    onTabSelected = { viewModel.selectTab(it) },
    movieScreen = { modifier ->
      MovieScreen(
        viewModel = viewModel,
        selectPoster = selectItem,
        lazyListState = tabStateHolder.movieLazyListState,
        modifier = modifier,
      )
    },
    tvScreen = { modifier ->
      TvScreen(
        viewModel = viewModel,
        selectPoster = selectItem,
        lazyListState = tabStateHolder.tvLazyListState,
        modifier = modifier,
      )
    },
    peopleScreen = { modifier ->
      PeopleScreen(
        viewModel = viewModel,
        selectPerson = selectItem,
        lazyListState = tabStateHolder.peopleLazyListState,
        modifier = modifier,
      )
    },
  )
}

@Composable
private fun HomeTabScreen(
  selectedTab: MainScreenHomeTab,
  onTabSelected: (MainScreenHomeTab) -> Unit,
  movieScreen: @Composable (Modifier) -> Unit,
  tvScreen: @Composable (Modifier) -> Unit,
  peopleScreen: @Composable (Modifier) -> Unit,
) {
  val tabs = MainScreenHomeTab.entries.toTypedArray()

  Scaffold(
    backgroundColor = MaterialTheme.colors.primarySurface,
    topBar = { MainAppBar() },
    bottomBar = {
      BottomNavigation(
        backgroundColor = purple200,
        modifier = Modifier.navigationBarsPadding(),
      ) {
        tabs.forEach { tab ->
          BottomNavigationItem(
            icon = { Icon(imageVector = tab.icon, contentDescription = null) },
            label = { Text(text = stringResource(tab.title), color = Color.White) },
            selected = tab == selectedTab,
            onClick = { onTabSelected(tab) },
            selectedContentColor = LocalContentColor.current,
            unselectedContentColor = LocalContentColor.current,
          )
        }
      }
    },
  ) { innerPadding ->
    val modifier = Modifier.padding(innerPadding)

    Crossfade(selectedTab, label = "HomeTabCrossfade") { destination ->
      when (destination) {
        MainScreenHomeTab.MOVIE -> movieScreen(modifier)
        MainScreenHomeTab.TV -> tvScreen(modifier)
        MainScreenHomeTab.PERSON -> peopleScreen(modifier)
      }
    }
  }
}

@Preview
@Composable
private fun HomeTabScreenPreview() {
  MovieComposeTheme {
    HomeTabScreen(
      selectedTab = MainScreenHomeTab.MOVIE,
      onTabSelected = {},
      movieScreen = { Text("Movie Screen", modifier = it) },
      tvScreen = { Text("TV Screen", modifier = it) },
      peopleScreen = { Text("People Screen", modifier = it) },
    )
  }
}
