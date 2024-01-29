package com.example.sportshall.ui.screens.sport_hall_data.trainers.add_trainer

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
import com.example.sportshall.common.PhoneField
import com.example.sportshall.common.SportHallTextField
import com.example.sportshall.data.local_source.data.typeOfSportList
import com.example.sportshall.data.local_source.event.TrainerEvent
import com.example.sportshall.ui.screens.sport_hall_data.trainers.TrainerUiState
import com.example.sportshall.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTrainerScreen(
	uiState: TrainerUiState,
	onClickBack: () -> Unit,
	onClickAdd: () -> Unit,
	onTrainer: (TrainerEvent) -> Unit,
	onClickRandom: () -> Unit,
) {

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
						text = "Add trainer",
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
					text = "Add trainer",
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
			SportHallTextField(
				modifier = Modifier,
				value = uiState.firstName,
				onValueChange = {
					onTrainer(TrainerEvent.SetFirstName(it))
				},
				placeholder = "Enter first name trainer",
			)

			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			SportHallTextField(
				modifier = Modifier.fillMaxWidth(),
				value = uiState.lastName,
				onValueChange = {
					onTrainer(TrainerEvent.SetLastName(it))
				},
				placeholder = "Enter last name trainer",
			)
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			SportHallTextField(
				value = uiState.patronymic,
				onValueChange = {
					onTrainer(TrainerEvent.SetPatronymic(it))
				},
				placeholder = "Enter patronymic trainer",
			)
			SportHallTextField(
				value = if (uiState.workExperience.isBlank()) "" else uiState.workExperience.takeIf {
					it.endsWith(
						" year"
					)
				} ?: (uiState.workExperience + " year"),
				onValueChange = {
					onTrainer(TrainerEvent.SetWorkExperience(it))
				},
				placeholder = "Enter work experience trainer",
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Number
				)
			)
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))
			PhoneField(
				phone = uiState.phoneNumber,
				onPhoneChanged = {
					onTrainer(TrainerEvent.SetPhoneNumber(it))
				},
				placeholder = "Enter phone number trainer",
			)
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(top = 8.dp),
				expanded = expanded,
				onExpandedChange = {
					expanded = !expanded
				}
			) {
				OutlinedTextField(
					modifier = Modifier
						.menuAnchor()
						.fillMaxWidth(),
					value = uiState.typeOfOccupation,
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
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
				)
				ExposedDropdownMenu(
					modifier = Modifier
						.background(Color.White)
						.fillMaxWidth(),
					expanded = expanded,
					onDismissRequest = { expanded = false }
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
								uiState.typeOfOccupation = it.typeOfOccupation
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
			Spacer(modifier = Modifier.height(100.dp))
		}
	}
}