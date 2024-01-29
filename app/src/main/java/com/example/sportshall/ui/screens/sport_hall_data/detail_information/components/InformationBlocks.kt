package com.example.sportshall.ui.screens.sport_hall_data.detail_information.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportshall.common.DetailInformationItem
import com.example.sportshall.ui.theme.Primary


@Composable
fun DetailInformationClientBlock(
	modifier: Modifier = Modifier,
	onClickBack: () -> Unit,
	firstName: String,
	lastName: String,
	patronymic: String,
	birthday: String?,
	phoneNumber: String,
	gender: String,
) {
	Scaffold(
		modifier = modifier
			.fillMaxSize(),
		topBar = {
			TopAppBar(
				modifier = Modifier,
				title = {},
				navigationIcon = {
					IconButton(onClick = onClickBack) {
						Icon(
							imageVector = Icons.Outlined.ArrowBack,
							contentDescription = null,
							tint = Color.White
						)
					}
				},
				backgroundColor = Primary,
			)
		}
	) {
		Column(
			modifier = Modifier
				.background(Primary)
				.padding(
					top = it.calculateTopPadding(),
					start = 16.dp,
					end = 16.dp,
				)
				.fillMaxSize(),
		) {
			DetailInformationItem(
				firstName = firstName,
				lastName = lastName,
				patronymic = patronymic,
				phoneNumber = phoneNumber,
			)
			Text(
				text = "Birthday: $birthday",
				color = Color.Black,
			)
			Text(
				text = "Gander: $gender",
				color = Color.Black,
			)
		}
	}
}

@Composable
fun DetailInformationTrainerBlock(
	modifier: Modifier = Modifier,
	onClickBack: () -> Unit,
	workExperience: String,
	firstName: String,
	lastName: String,
	patronymic: String,
	phoneNumber: String,
	typeOfOccupation: String,
) {
	Scaffold(
		modifier = modifier
			.fillMaxSize(),
		topBar = {
			TopAppBar(
				modifier = Modifier,
				title = {},
				navigationIcon = {
					IconButton(onClick = onClickBack) {
						Icon(
							imageVector = Icons.Outlined.ArrowBack,
							contentDescription = null,
							tint = Color.White
						)
					}
				},
				backgroundColor = Primary,
			)
		}
	) {
		Column(
			modifier = Modifier
				.background(Primary)
				.padding(
					top = it.calculateTopPadding(),
					start = 16.dp,
					end = 16.dp,
				)
				.fillMaxSize(),
		) {
			DetailInformationItem(
				firstName = firstName,
				lastName = lastName,
				patronymic = patronymic,
				phoneNumber = phoneNumber,
			)
			Text(
				text = "Work experience: $workExperience",
				color = Color.Black,
			)
			Text(
				text = "Type of sport: $typeOfOccupation",
				color = Color.Black,
			)
		}
	}
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailInformationSeasonTicketBlock(
	modifier: Modifier = Modifier,
	onClickBack: () -> Unit,
	idClient: String,
	idTrainer: String,
	term: String,
	change: String,
) {
	Scaffold(
		modifier = modifier
			.fillMaxSize(),
		topBar = {
			TopAppBar(
				modifier = Modifier,
				title = {},
				navigationIcon = {
					IconButton(onClick = onClickBack) {
						Icon(
							imageVector = Icons.Outlined.ArrowBack,
							contentDescription = null,
							tint = Color.White
						)
					}
				},
				backgroundColor = Primary,
			)
		}
	) {
		Column(
			modifier = Modifier
				.background(Primary)
				.padding(
					top = it.calculateTopPadding(),
					start = 16.dp,
					end = 16.dp,
				)
				.fillMaxSize(),
		) {
			Text(
				text = "Id client: $idClient",
				color = Color.Black
			)
			Text(
				text = "Id trainer: $idTrainer",
				color = Color.Black
			)
			Text(
				text = "Term season ticket: $term",
				color = Color.Black
			)
			Text(
				text = "Change: $change",
				color = Color.Black
			)
		}
	}
}