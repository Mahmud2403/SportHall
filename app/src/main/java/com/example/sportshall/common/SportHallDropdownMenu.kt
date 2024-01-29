package com.example.sportshall.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportshall.ui.screens.sport_hall_data.clients.Gender

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportHallDropdownMenu(
	value: String,
	placeHolder: String,
	list: List<String>
) {
	var expanded by remember { mutableStateOf(false) }

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
			value = value,
			onValueChange = {},
			placeholder = {
				Text(
					text = placeHolder
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
			list.forEach {
				DropdownMenuItem(
					modifier = Modifier
						.background(Color.White)
						.fillMaxWidth(),
					text = {
						Text(
							text = list.component1()
						)
					},
					onClick = {
//						value = it.name
						expanded = false
					},
					colors = MenuDefaults.itemColors(
						textColor = Color.Black
					)
				)
			}
			Divider(modifier = Modifier.fillMaxWidth())
		}
	}
}