package com.example.sportshall.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportshall.utils.buildMaskedPhoneNumber

@Composable
fun DetailInformationItem(
	firstName: String,
	lastName: String,
	patronymic: String,
	phoneNumber: String
) {
	Column(
		modifier = Modifier,
	) {
		Text(
			text = "First name: $firstName",
			color = Color.Black
		)
		Text(
			text = "Last name: $lastName",
			color = Color.Black
		)
		Text(
			text = "Patronymic: $patronymic",
			color = Color.Black
		)
		Text(
			text = "Phone number: $phoneNumber",
			color = Color.Black
		)
	}
}