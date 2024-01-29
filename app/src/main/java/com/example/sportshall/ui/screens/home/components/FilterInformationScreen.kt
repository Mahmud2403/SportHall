package com.example.sportshall.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportshall.common.Title

@Composable
fun FilterInformationScreen(
	idHall: String,
	typeOfSport: String,
	nameClient: String,
	nameTrainer: String,
	change: String,
	term: String,
) {
	LazyColumn {
		item {
			FilterInformationItem(
				idHall = idHall,
				typeOfSport = typeOfSport,
				nameClient = nameClient,
				nameTrainer = nameTrainer,
				change = change,
				term = term
			)
		}
	}
}

@Composable
fun FilterInformationItem(
	idHall: String,
	typeOfSport: String,
	nameClient: String,
	nameTrainer: String,
	change: String,
	term: String,
) {
	Card(
		modifier = Modifier
			.background(Color.White)
			.padding(8.dp)
	) {
		Row {
			Title(text = "Hall number: ", value = idHall)
			Title(text = "Type of sport", value = typeOfSport)
			Title(text = "Change: ", value = change)
			Title(text = "Number of training days", value = term)
			Title(text = "Trainer name", value = nameTrainer)
			Title(text = "Client name", value = nameClient)
		}
	}
}