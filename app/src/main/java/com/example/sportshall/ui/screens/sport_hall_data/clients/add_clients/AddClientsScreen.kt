package com.example.sportshall.ui.screens.sport_hall_data.clients.add_clients

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportshall.R
import com.example.sportshall.common.DatePicker
import com.example.sportshall.common.PhoneField
import com.example.sportshall.common.SportHallDatePicker
import com.example.sportshall.common.SportHallTextField
import com.example.sportshall.data.local_source.event.ClientsEvent
import com.example.sportshall.ui.screens.sport_hall_data.clients.ClientUiState
import com.example.sportshall.ui.screens.sport_hall_data.clients.Gender
import com.example.sportshall.ui.theme.Primary
import com.example.sportshall.utils.Converter
import com.example.sportshall.utils.rememberMutableStateOf

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddClientsScreen(
	uiState: ClientUiState,
	onClickBack: () -> Unit,
	onClickAdd: () -> Unit,
	onClients: (ClientsEvent) -> Unit,
	onChangeDate: (Long) -> Unit,
	onClickRandom: () -> Unit,
) {

	var datePickerIsVisible by rememberMutableStateOf(initValue = false)

	if (datePickerIsVisible) {
		SportHallDatePicker(
			onDismissRequest = {
				datePickerIsVisible = false
			},
			onConfirmClick = { date ->
				datePickerIsVisible = false
				onChangeDate(date)
			},
			onDismissClick = {
				datePickerIsVisible = false
			}
		)
	}

	val topAppbarState = rememberTopAppBarState()
	val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppbarState)
	val scrollState = rememberScrollState()
	val showTopAppBarShadow by remember { derivedStateOf { topAppbarState.collapsedFraction == 1f } }

	var expanded by remember { mutableStateOf(false) }

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
						text = "Add client",
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
				colors = ButtonDefaults.buttonColors(
					containerColor = Primary,
					contentColor = Color.Black
				),
			) {
				Text(
					text = "Add client",
					textAlign = TextAlign.Center
				)
			}
		},
	)
	{
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(Primary)
				.padding(top = it.calculateTopPadding(), start = 16.dp, end = 16.dp)
				.verticalScroll(scrollState),
		) {
			SportHallTextField(
				modifier = Modifier,
				value = uiState.firstName,
				onValueChange = {
					onClients(ClientsEvent.SetFirstName(it))
				},
				placeholder = "Enter first name client",
			)

			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			SportHallTextField(
				modifier = Modifier.fillMaxWidth(),
				value = uiState.lastName,
				onValueChange = {
					onClients(ClientsEvent.SetLastName(it))
				},
				placeholder = "Enter last name client",
			)
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			SportHallTextField(
				value = uiState.patronymic,
				onValueChange = {
					onClients(ClientsEvent.SetPatronymic(it))
				},
				placeholder = "Enter patronymic client",
			)
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(top = 16.dp),
				expanded = expanded,
				onExpandedChange = {
					expanded = !expanded
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
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
				)
				ExposedDropdownMenu(
					modifier = Modifier
						.background(Color.White)
						.fillMaxWidth(),
					expanded = expanded,
					onDismissRequest = { expanded = false }
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
								expanded = false
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
			PhoneField(
				phone = uiState.phoneNumber,
				onPhoneChanged = {
					onClients(ClientsEvent.SetPhoneNumber(it))
				},
				placeholder = "Enter phone number client",
			)
			Spacer(modifier = Modifier.padding(horizontal = 20.dp))
			DatePicker(
				date = uiState.date?.let { Converter.convertLongToDate(it) },
				onClickDate = {
					datePickerIsVisible = true
				},
			)
			Spacer(modifier = Modifier.height(100.dp))
		}
	}
}