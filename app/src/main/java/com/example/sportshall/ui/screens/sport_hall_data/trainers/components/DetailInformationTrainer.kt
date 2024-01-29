package com.example.sportshall.ui.screens.sport_hall_data.trainers.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportshall.ui.theme.Primary

@Composable
fun TrainerInformationItem(
	modifier: Modifier = Modifier,
	id: String,
	name: String,
	phoneNumber: String,
	onClickTrainer: () -> Unit,
	onDelete: () -> Unit,
	isVisibleDelete: Boolean,
) {
	Card(
		modifier = modifier
			.fillMaxWidth(),
		backgroundColor = Primary,
	) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.padding(
					vertical = 8.dp,
					horizontal = 16.dp,
				)
				.clickable(
					onClick = onClickTrainer
				),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically,
		) {
			Text(
				modifier = Modifier,
				text = id,
				fontSize = 40.sp,
				color = Color.Black
			)
			Column(
				modifier = Modifier
					.weight(1f)
					.fillMaxWidth(),
				verticalArrangement = Arrangement.SpaceBetween,
			) {
				Text(
					text = name,
					fontSize = 16.sp,
					color = Color.Black,
				)
				Text(
					text = phoneNumber,
					fontSize = 12.sp,
					color = Color.Black,
				)
			}
			if (isVisibleDelete) {
				IconButton(onClick = onDelete) {
					Icon(
						imageVector = Icons.Default.Delete,
						contentDescription = null
					)
				}
			}
		}
	}
}