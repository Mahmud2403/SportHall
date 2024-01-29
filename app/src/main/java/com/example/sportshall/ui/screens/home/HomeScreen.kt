package com.example.sportshall.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportshall.R
import com.example.sportshall.common.SportHallDateRangePicker
import com.example.sportshall.common.SportHallSearchBar
import com.example.sportshall.ui.screens.home.components.FilterScreen
import com.example.sportshall.ui.screens.sport_hall_data.clients.components.ClientInformationItem
import com.example.sportshall.ui.screens.sport_hall_data.season_tickets.components.SeasonTicketInformationItem
import com.example.sportshall.ui.screens.sport_hall_data.trainers.components.TrainerInformationItem
import com.example.sportshall.ui.theme.Primary
import com.example.sportshall.utils.Converter
import com.example.sportshall.utils.buildMaskedPhoneNumber
import com.example.sportshall.utils.rememberMutableStateOf
import com.example.sportshall.utils.screenHeightPx
import com.holix.android.bottomsheetdialog.compose.BottomSheetBehaviorProperties
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
	modifier: Modifier = Modifier,
	onClickSearchBar: () -> Unit,
	viewModel: HomeViewModel = hiltViewModel(),
	onClickBack: () -> Unit,
) {

	val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()
	var datePickType by remember {
		mutableStateOf(Date.Start)
	}

	val filteredClientsByBirthday by viewModel.filterClientsByBirthday.collectAsStateWithLifecycle()
	val filteredClientsByGender by viewModel.filterClientsByGender.collectAsStateWithLifecycle()
	val filteredTrainer by viewModel.filterTrainerByTypeOfSport.collectAsStateWithLifecycle()
	val filteredSeasonTicket by viewModel.filterSeasonTicketChange.collectAsStateWithLifecycle()


	var isVisibility by remember { mutableStateOf(false) }
	val bottomSheetDialogState by rememberMutableStateOf(BottomSheetBehaviorProperties.State.Collapsed)
	var datePickerIsVisible by rememberMutableStateOf(initValue = false)

	if (datePickerIsVisible) {
		SportHallDateRangePicker(
			onDismissRequest = {
				datePickerIsVisible = false
			},
			onConfirmClick = { date ->
				datePickerIsVisible = false
				viewModel.changeDate(
					date = date,
					dateType = datePickType,
				)
			},
			onDismissClick = {
				datePickerIsVisible = false
			},
		)
	}

	if (isVisibility) {
		BottomSheetDialog(
			onDismissRequest = { isVisibility = false },
			properties = BottomSheetDialogProperties(
				dismissWithAnimation = true, behaviorProperties = BottomSheetBehaviorProperties(
					state = bottomSheetDialogState,
					peekHeight = BottomSheetBehaviorProperties.PeekHeight(screenHeightPx.toInt()),
				)
			)
		) {
			FilterScreen(
				onBackClick = onClickBack,
				onClickDate = {
					datePickType = it
					datePickerIsVisible = true
				},
				onClickApply = {
					viewModel.syncFilter()
					isVisibility = false
				},
				uiState = uiState
			)
		}
	}
	Scaffold(
		modifier = Modifier
			.fillMaxSize()
			.background(Primary),
		topBar = {
			Box(
				modifier = Modifier
					.semantics {
						isTraversalGroup = true
					}) {
				SportHallSearchBar(
					modifier = Modifier
						.clip(MaterialTheme.shapes.small),
					placeholder = {
						Text(
							modifier = Modifier,
							text = "Clients, Trainers",
							style = MaterialTheme.typography.bodyLarge,
							color = Color.Gray
						)
					},
					leadingIcon = {
						Icon(
							modifier = Modifier.size(20.dp),
							imageVector = Icons.Default.Search,
							contentDescription = null,
							tint = Color.Gray
						)
					},
					innerPaddingValues = PaddingValues(
						horizontal = 18.dp,
						vertical = 8.dp
					),
					shapes = MaterialTheme.shapes.small,
					border = BorderStroke(
						width = 1.dp,
						color = Color(0xFFEFEFEF)
					),
					onClick = onClickSearchBar
				)
			}
		}
	) {
		Column(
			modifier = modifier
				.fillMaxSize()
				.background(Primary)
				.padding(
					top = it.calculateTopPadding(),
					start = 16.dp,
					end = 16.dp,
				)
		) {
			Icon(
				modifier = modifier
					.padding(top = 12.dp)
					.clickable(
						interactionSource = remember { MutableInteractionSource() },
						indication = null,
						onClick = {
							isVisibility = true
						}
					),
				painter = painterResource(id = R.drawable.ic_setting),
				contentDescription = null,
				tint = Color.Black
			)
			Spacer(modifier = Modifier.padding(top = 8.dp))
			if (filteredClientsByBirthday.isNotEmpty() || filteredClientsByGender.isNotEmpty()) {
				Text(
					text = if (filteredClientsByBirthday.isNotEmpty()) "Дата рождение клиентов от " +
							"${uiState.birthDay.startDate?.let { Converter.convertLongToDate(it) }} " +
							"до ${uiState.birthDay.endData?.let { Converter.convertLongToDate(it) }}" else "",
					color = Color.Black
				)
				Spacer(modifier = Modifier.padding(horizontal = 8.dp))
				Text(
					text = if (filteredClientsByGender.isNotEmpty()) "Клиент с полом: ${uiState.gender}" else "",
					color = Color.Black
				)
				Spacer(modifier = Modifier.padding(top = 8.dp))
				uiState.clientEntities.forEach { client ->
					ClientInformationItem(
						id = "${client.id}. ",
						name = "${client.firstName} ${client.lastName}",
						phoneNumber = buildMaskedPhoneNumber(client.phoneNumber),
						onDelete = {},
						onClickClient = {},
						isVisibleDelete = false
					)
				}
				Spacer(modifier = Modifier.padding(top = 8.dp))
				Divider(modifier = Modifier.fillMaxWidth())
			}
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			if (filteredTrainer.isNotEmpty()){
				Text(
					text ="Тренеры, которые занимаются обучением: ${uiState.typeOfSport}",
					color = Color.Black,
				)
				uiState.trainerEntities.forEach{ trainer ->
					TrainerInformationItem(
						id = "${trainer.id}. ",
						name = "${trainer.firstName} ${trainer.lastName}",
						phoneNumber = buildMaskedPhoneNumber(trainer.phoneNumber),
						onClickTrainer = {},
						onDelete = {},
						isVisibleDelete = false
					)
				}
				Spacer(modifier = Modifier.padding(top = 8.dp))
				Divider(modifier = Modifier.fillMaxWidth())
			}
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			if (filteredSeasonTicket.isNotEmpty()){
				Text(
					text = "${uiState.changeType} смена",
					color = Color.Black,
				)
				uiState.seasonTicketEntity.forEach { seasonTicket ->
					SeasonTicketInformationItem(
						onClickSeasonTicket = {},
						change = seasonTicket.change,
						id = "${seasonTicket.id}",
						nameClient = seasonTicket.nameClient,
						nameTrainer = seasonTicket.nameTrainer
					)
				}
				Spacer(modifier = Modifier.padding(top = 8.dp))
				Divider(modifier = Modifier.fillMaxWidth())
			}
			Spacer(modifier = Modifier.height(60.dp))
		}
	}
}