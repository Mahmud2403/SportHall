package com.example.sportshall.ui.screens.sport_hall_data.season_tickets.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportshall.R
import com.example.sportshall.common.SportHallTextField
import com.example.sportshall.data.local_source.event.SeasonTicketEvent
import com.example.sportshall.ui.screens.sport_hall_data.clients.ClientUiState
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.Change
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.SeasonTicketUiState
import com.example.sportshall.ui.screens.sport_hall_data.trainers.TrainerUiState
import com.example.sportshall.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSeasonTicketScreen(
	uiState: SeasonTicketUiState,
	onClickBack: () -> Unit,
	onClickAdd: () -> Unit,
	onSeasonTicket: (SeasonTicketEvent) -> Unit,
	onClickRandom: () -> Unit,
	clientUiState: ClientUiState,
	trainerUiState: TrainerUiState,
) {

	val topAppbarState = rememberTopAppBarState()
	val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppbarState)
	val scrollState = rememberScrollState()
	val showTopAppBarShadow by remember { derivedStateOf { topAppbarState.collapsedFraction == 1f } }

	var expandedIdTrainer by remember { mutableStateOf(false) }
	var expandedIdClient by remember { mutableStateOf(false) }
	var expandedChange by remember { mutableStateOf(false) }




	Scaffold(
		modifier = Modifier
			.fillMaxSize()
			.systemBarsPadding()
			.nestedScroll(scrollBehavior.nestedScrollConnection),
		topBar = {
			LargeTopAppBar(
				modifier = Modifier
					.graphicsLayer {
						shadowElevation = if (showTopAppBarShadow) 5f else 0f
					},
				title = {
					Text(
						modifier = Modifier,
						text = "Add season ticket",
						maxLines = 1,
						overflow = TextOverflow.Ellipsis
					)
				},
				navigationIcon = {
					IconButton(onClick = onClickBack) {
						Icon(
							imageVector = Icons.Outlined.ArrowBack,
							contentDescription = null,
							tint = Color.White
						)
					}
				},
				actions = {
					Icon(
						modifier = Modifier.clickable(
							onClick = onClickRandom
						),
						painter = painterResource(
							id = R.drawable.ic_round_auto_awesome
						),
						contentDescription = null,
					)

				},
				colors = TopAppBarDefaults.largeTopAppBarColors(
					scrolledContainerColor = Primary,
					titleContentColor = Color.White,
					containerColor = Primary,
				),
				scrollBehavior = scrollBehavior,
			)
		},
		bottomBar = {
			ElevatedButton(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 16.dp),
				onClick = onClickAdd,
//				shape = RoundedCornerShape(0.dp),
				colors = ButtonDefaults.buttonColors(
					containerColor = Primary,
					contentColor = Color.Black
				),
			) {
				Text(
					text = "Add season ticket",
					textAlign = TextAlign.Center
				)
			}
		},
	)
	{
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(Color(0xE8B7EBDF))
				.padding(top = it.calculateTopPadding(), start = 16.dp, end = 16.dp)
				.verticalScroll(scrollState),
		) {
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(top = 16.dp),
				expanded = expandedIdTrainer,
				onExpandedChange = {
					expandedIdTrainer = !expandedIdTrainer
				}
			) {
				OutlinedTextField(
					modifier = Modifier
						.menuAnchor()
						.fillMaxWidth(),
					value = uiState.nameTrainer,
					onValueChange = {},
					placeholder = {
						Text(
							text = "Specify trainer"
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
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedIdTrainer) },
				)
				ExposedDropdownMenu(
					modifier = Modifier
						.background(Color.White)
						.fillMaxWidth(),
					expanded = expandedIdTrainer,
					onDismissRequest = { expandedIdTrainer = false }
				) {
					trainerUiState.trainerEntities.forEach { trainer ->
						DropdownMenuItem(
							modifier = Modifier
								.background(Color.White)
								.fillMaxWidth(),
							text = {
								Text(
									text = "${trainer.id}. ${trainer.firstName} ${trainer.lastName}"
								)
							},
							onClick = {
								uiState.nameTrainer = trainer.firstName + " " + trainer.lastName
								expandedIdTrainer = false
							},
							colors = MenuDefaults.itemColors(
								textColor = Color.Black
							)
						)
						Divider(modifier = Modifier.fillMaxWidth())
					}
				}
			}
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(top = 16.dp),
				expanded = expandedIdClient,
				onExpandedChange = {
					expandedIdClient = !expandedIdClient
				}
			) {
				OutlinedTextField(
					modifier = Modifier
						.menuAnchor()
						.fillMaxWidth(),
					value = uiState.nameClient,
					onValueChange = {},
					placeholder = {
						Text(
							text = "Specify client"
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
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedIdClient) },
				)
				ExposedDropdownMenu(
					modifier = Modifier
						.background(Color.White)
						.fillMaxWidth(),
					expanded = expandedIdClient,
					onDismissRequest = { expandedIdClient = false }
				) {
					clientUiState.clientEntities.forEach { client ->
						DropdownMenuItem(
							modifier = Modifier
								.background(Color.White)
								.fillMaxWidth(),
							text = {
								Text(
									text = "${client.id}. ${client.firstName} ${client.lastName}"
								)
							},
							onClick = {
								uiState.nameClient = client.firstName + " " + client.lastName
								expandedIdClient = false
							},
							colors = MenuDefaults.itemColors(
								textColor = Color.Black
							)
						)
						Divider(modifier = Modifier.fillMaxWidth())
					}
				}
			}
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			SportHallTextField(
				value = if (uiState.term.isBlank()) "" else uiState.term.takeIf {
					it.endsWith(
						" day"
					)
				} ?: (uiState.term + " day"),
				onValueChange = {
					onSeasonTicket(SeasonTicketEvent.SetTerm(it))
				},
				placeholder = "Enter the number of training days",
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Number
				)
			)
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
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
					value = uiState.change,
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
								uiState.change = change.text
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
			Spacer(modifier = Modifier.height(100.dp))
		}
	}
}