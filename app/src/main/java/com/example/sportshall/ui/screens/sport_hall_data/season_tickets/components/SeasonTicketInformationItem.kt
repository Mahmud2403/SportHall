package com.example.sportshall.ui.screens.sport_hall_data.season_tickets.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportshall.ui.theme.Primary


@Composable
fun SeasonTicketInformationItem(
	modifier: Modifier = Modifier,
	onClickSeasonTicket: () -> Unit,
	change: String,
	id: String,
	nameClient: String,
	nameTrainer: String
) {
	Card(
		modifier = modifier
			.fillMaxWidth(),
		backgroundColor = Primary,
	) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.padding(horizontal = 8.dp)
				.clickable(
					onClick = { onClickSeasonTicket() }
				),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text(
				modifier = Modifier,
				text = "$id.  ",
				fontSize = 56.sp,
				color = Color.Black
			)
			Column(
				modifier = Modifier
					.weight(1f)
			) {
				Text(
					text = "Client name: $nameClient \nTrainer name: $nameTrainer",
					fontSize = 16.sp,
					color = Color.Black,
				)
				Text(
					text = change,
					fontSize = 12.sp,
					color = Color.Black,
				)
			}
		}
	}
}

