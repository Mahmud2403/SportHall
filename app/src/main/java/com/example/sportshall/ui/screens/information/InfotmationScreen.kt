package com.example.sportshall.ui.screens.information

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportshall.ui.screens.information.components.InformationCard

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InformationScreen(
	onClickAddClients: () -> Unit,
	onClickViewClients: () -> Unit,
	onClickAddTrainer: () -> Unit,
	onClickViewTrainer: () -> Unit,
	onClickAddSeasonTicket: () -> Unit,
	onClickViewSeasonTicket: () -> Unit,
	onClickViewHalls: () -> Unit,
	onClickViewTypeOfSport: () -> Unit,
) {

	val scrollState = rememberScrollState()
	Scaffold(
		modifier = Modifier
			.fillMaxSize()
			.systemBarsPadding()
			.background(Color.White),
		topBar = {
			TopAppBar(
				title = {
					Text(
						modifier = Modifier
							.fillMaxWidth(),
						text = "Information Sport hall",
						textAlign = TextAlign.Center,
						color = Color.Black
					)
				},
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = Color.White
				)
			)
		}
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.White)
				.padding(
					top = it.calculateTopPadding(),
					end = 16.dp,
					bottom = 60.dp,
					start = 16.dp
				)
				.systemBarsPadding()
				.verticalScroll(scrollState)
			) {
			InformationCard(
				onClickAddClients = onClickAddClients,
				onClickViewClients = onClickViewClients,
				onClickAddTrainer = onClickAddTrainer,
				onClickViewTrainer = onClickViewTrainer,
				onClickAddSeasonTicket = onClickAddSeasonTicket,
				onClickViewHalls = onClickViewHalls,
				onClickViewSeasonTicket = onClickViewSeasonTicket,
				onClickViewTypeOfSport = onClickViewTypeOfSport,
			)
		}
	}
}