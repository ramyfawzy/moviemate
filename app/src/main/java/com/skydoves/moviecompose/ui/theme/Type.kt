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

package com.skydoves.moviecompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// set of dark material typography styles to start with.
val DarkTypography = Typography(
  h1 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.ExtraBold,
    color = Color.White,
    fontSize = 32.sp,
    letterSpacing = 0.sp
  ),
  h2 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold,
    color = Color.White,
    fontSize = 24.sp,
    letterSpacing = 0.15.sp
  ),
  h3 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.SemiBold,
    color = Color.White,
    fontSize = 20.sp
  ),
  body1 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    color = Slate200,
    fontSize = 16.sp,
    lineHeight = 24.sp
  ),
  body2 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    color = Slate400,
    fontSize = 14.sp,
    lineHeight = 20.sp
  ),
  button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    letterSpacing = 1.25.sp
  ),
  caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    color = Slate400,
    fontSize = 12.sp
  )
)

// set of light material typography styles to start with.
val LightTypography = Typography(
  h1 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.ExtraBold,
    color = Slate900,
    fontSize = 32.sp
  ),
  h2 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold,
    color = Slate900,
    fontSize = 24.sp
  ),
  h3 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.SemiBold,
    color = Slate900,
    fontSize = 20.sp
  ),
  body1 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    color = Slate800,
    fontSize = 16.sp,
    lineHeight = 24.sp
  ),
  body2 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    color = Slate400,
    fontSize = 14.sp,
    lineHeight = 20.sp
  ),
  button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    letterSpacing = 1.25.sp
  ),
  caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    color = Slate400,
    fontSize = 12.sp
  )
)
