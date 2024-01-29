package com.example.sportshall.ui.screens.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportshall.common.DateRangePicker
import com.example.sportshall.data.local_source.data.typeOfSportList
import com.example.sportshall.ui.screens.home.Date
import com.example.sportshall.ui.screens.home.HomeUiState
import com.example.sportshall.ui.screens.sport_hall_data.clients.Gender
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.Change
import com.example.sportshall.ui.theme.Primary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
	modifier: Modifier = Modifier,
	onBackClick: () -> Unit,
	onClickDate: (Date) -> Unit,
	onClickApply: () -> Unit,
	uiState: HomeUiState
) {
	var expandedGender by remember { mutableStateOf(false) }
	var expandedSport by remember { mutableStateOf(false) }
	var expandedChange by remember { mutableStateOf(false) }

	Scaffold(
		modifier = modifier
			.fillMaxSize()
			.clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)),
		topBar = {
			CenterAlignedTopAppBar(
				title = {
					Text(
						modifier = Modifier,
						text = "Filter",
						style = MaterialTheme.typography.titleMedium,
						color = Color.Black
					)
				},
				navigationIcon = {
					IconButton(
						onClick = onBackClick
					) {
						Icon(
							imageVector = Icons.Rounded.ArrowBack,
							contentDescription = null,
							tint = Color.Black,
						)
					}
				},
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = Primary
				)
			)
		},
		bottomBar = {
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp),
				onClick = onClickApply
			) {
				Text(text = "Apply")
			}
		}
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(
					top = it.calculateTopPadding(),
					start = 10.dp,
					end = 10.dp,
				)
		) {
			DateRangePicker(
				dateStart = uiState.birthDay.startDate,
				dateEnd = uiState.birthDay.endData,
				onClickDate = onClickDate
			)
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(top = 8.dp),
				expanded = expandedSport,
				onExpandedChange = {
					expandedSport = !expandedSport
				}
			) {
				OutlinedTextField(
					modifier = Modifier
						.menuAnchor()
						.fillMaxWidth(),
					value = uiState.typeOfSport,
					onValueChange = {},
					placeholder = {
						Text(
							text = "Choose a sport"
						)
					},
					readOnly = true,
					colors = OutlinedTextFieldDefaults.colors(
						unfocusedTextColor = Color.Black.copy(0.5f),
						focusedTextColor = Color.Black,
						focusedBorderColor = Color.Black,
						unfocusedBorderColor = Color.Black.copy(0.5f),
						unfocusedContainerColor = Color.White,
						focusedContainerColor = Color.White
					),
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSport) },
				)
				ExposedDropdownMenu(
					modifier = Modifier
						.background(Color.White)
						.fillMaxWidth(),
					expanded = expandedSport,
					onDismissRequest = { expandedSport = false }
				) {
					typeOfSportList.forEach {
						DropdownMenuItem(
							modifier = Modifier
								.background(Color.White)
								.fillMaxWidth(),
							text = {
								Text(
									text = it.typeOfOccupation
								)
							},
							onClick = {
								uiState.typeOfSport = it.typeOfOccupation
								expandedSport = false
							},
							colors = MenuDefaults.itemColors(
								textColor = Color.Black
							)
						)
						Divider(modifier = Modifier.fillMaxWidth())
					}
				}
			}
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(top = 16.dp),
				expanded = expandedGender,
				onExpandedChange = {
					expandedGender = !expandedGender
				}
			) {
				OutlinedTextField(
					modifier = Modifier
						.menuAnchor()
						.fillMaxWidth(),
					value = uiState.gender,
					onValueChange = {},
					placeholder = {
						Text(
							text = "Specify gender client"
						)
					},
					readOnly = true,
					colors = OutlinedTextFieldDefaults.colors(
						unfocusedTextColor = Color.Black.copy(0.5f),
						focusedTextColor = Color.Black,
						focusedBorderColor = Color.Black,
						unfocusedBorderColor = Color.Black.copy(0.5f),
						unfocusedContainerColor = Color.White,
						focusedContainerColor = Color.White
					),
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGender) },
				)
				ExposedDropdownMenu(
					modifier = Modifier
						.background(Color.White)
						.fillMaxWidth(),
					expanded = expandedGender,
					onDismissRequest = { expandedGender = false }
				) {
					Gender.values().forEach {
						DropdownMenuItem(
							modifier = Modifier
								.background(Color.White)
								.fillMaxWidth(),
							text = {
								Text(
									text = it.value
								)
							},
							onClick = {
								uiState.gender = it.value
								expandedGender = false
							},
							colors = MenuDefaults.itemColors(
								textColor = Color.Black
							)
						)
						Divider(modifier = Modifier.fillMaxWidth())
					}
				}
			}
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(top = 8.dp),
				expanded = expandedChange,
				onExpandedChange = {
					expandedChange = !expandedChange
				}
			) {
				OutlinedTextField(
					modifier = Modifier
						.menuAnchor()
						.fillMaxWidth(),
					value = uiState.changeType,
					onValueChange = {},
					placeholder = {
						Text(
							text = "Select a shift"
						)
					},
					readOnly = true,
					colors = OutlinedTextFieldDefaults.colors(
						unfocusedTextColor = Color.Black.copy(0.5f),
						focusedTextColor = Color.Black,
						focusedBorderColor = Color.Black,
						unfocusedBorderColor = Color.Black.copy(0.5f),
						unfocusedContainerColor = Color.White,
						focusedContainerColor = Color.White
					),
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedChange) },
				)
				ExposedDropdownMenu(
					modifier = Modifier
						.background(Color.White)
						.fillMaxWidth(),
					expanded = expandedChange,
					onDismissRequest = { expandedChange = false }
				) {
					Change.values().forEach { change ->
						DropdownMenuItem(
							modifier = Modifier
								.background(Color.White)
								.fillMaxWidth(),
							text = {
								Text(
									text = "$change (${change.timeStart})"
								)
							},
							onClick = {
								uiState.changeType = change.text
								expandedChange = false
							},
							colors = MenuDefaults.itemColors(
								textColor = Color.Black
							)
						)
						Divider(modifier = Modifier.fillMaxWidth())
					}
				}
			}
		}
	}
}