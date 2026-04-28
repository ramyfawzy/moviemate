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

package com.skydoves.moviecompose.ui.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skydoves.moviecompose.models.entities.Movie
import com.skydoves.moviecompose.ui.main.MainViewModel

@Composable
fun MoviePostScreen(
  viewModel: MainViewModel,
  modifier: Modifier = Modifier,
) {
  var title by remember { mutableStateOf("") }
  var overview by remember { mutableStateOf("") }

  Column(
    modifier = modifier
      .fillMaxSize()
      .statusBarsPadding()
      .background(MaterialTheme.colors.background)
      .padding(16.dp),
  ) {
    OutlinedTextField(
      value = title,
      onValueChange = { title = it },
      label = { Text("Title") },
      modifier = Modifier.fillMaxWidth(),
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colors.primary,
        unfocusedBorderColor = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
        textColor = MaterialTheme.colors.onBackground,
        cursorColor = MaterialTheme.colors.primary,
        focusedLabelColor = MaterialTheme.colors.primary,
        unfocusedLabelColor = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
      ),
    )

    OutlinedTextField(
      value = overview,
      onValueChange = { overview = it },
      label = { Text("Overview") },
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp),
      colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colors.primary,
        unfocusedBorderColor = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
        textColor = MaterialTheme.colors.onBackground,
        cursorColor = MaterialTheme.colors.primary,
        focusedLabelColor = MaterialTheme.colors.primary,
        unfocusedLabelColor = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
      ),
    )

    Button(
      onClick = {
        if (title.isNotEmpty() && overview.isNotEmpty()) {
          val movie = Movie(
            page = 1,
            keywords = listOf(),
            videos = listOf(),
            reviews = listOf(),
            poster_path = null,
            adult = false,
            overview = overview,
            release_date = "",
            genre_ids = listOf(),
            id = System.currentTimeMillis(),
            original_title = title,
            original_language = "en",
            title = title,
            backdrop_path = null,
            popularity = 0f,
            vote_count = 0,
            video = false,
            vote_average = 0f,
          )
          viewModel.insertMovie(movie) {
            title = ""
            overview = ""
          }
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
    ) {
      Text("Insert Movie")
    }
  }
}
