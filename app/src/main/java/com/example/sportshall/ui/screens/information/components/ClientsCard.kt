package com.example.sportshall.ui.screens.information.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun InformationCard(
	onClickAddClients: () -> Unit,
	onClickViewClients: () -> Unit,
	onClickAddTrainer: () -> Unit,
	onClickViewTrainer: () -> Unit,
	onClickAddSeasonTicket: () -> Unit,
	onClickViewSeasonTicket: () -> Unit,
	onClickViewHalls: () -> Unit,
	onClickViewTypeOfSport: () -> Unit,
) {
	Column {
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = "Season Ticket",
			textAlign = TextAlign.Center,
			color = Color.Black
		)
		Spacer(modifier = Modifier.height(12.dp))
		OutlinedButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = onClickAddSeasonTicket
		) {
			Text(text = "Add")
		}
		Spacer(modifier = Modifier.height(8.dp))
		OutlinedButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = onClickViewSeasonTicket
		) {
			Text(text = "View")
		}
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = "Client",
			textAlign = TextAlign.Center,
			color = Color.Black
		)
		Spacer(modifier = Modifier.height(12.dp))
		OutlinedButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = onClickAddClients
		) {
			Text(text = "Add")
		}
		Spacer(modifier = Modifier.height(8.dp))
		OutlinedButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = onClickViewClients
		) {
			Text(text = "View")
		}
		Spacer(modifier = Modifier.height(16.dp))
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = "Trainers",
			textAlign = TextAlign.Center,
			color = Color.Black
		)
		Spacer(modifier = Modifier.height(12.dp))
		OutlinedButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = onClickAddTrainer
		) {
			Text(text = "Add")
		}
		Spacer(modifier = Modifier.height(8.dp))
		OutlinedButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = onClickViewTrainer
		) {
			Text(text = "View")
		}
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = "Halls",
			textAlign = TextAlign.Center,
			color = Color.Black
		)
		Spacer(modifier = Modifier.height(12.dp))
		OutlinedButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = onClickViewHalls
		) {
			Text(text = "View")
		}
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = "TypeOfSport",
			textAlign = TextAlign.Center,
			color = Color.Black
		)
		Spacer(modifier = Modifier.height(12.dp))
		OutlinedButton(
			modifier = Modifier.fillMaxWidth(),
			onClick = onClickViewTypeOfSport
		) {
			Text(text = "View")
		}
	}
}