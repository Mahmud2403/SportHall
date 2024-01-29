package com.example.sportshall.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SportHallTextField(
	modifier: Modifier = Modifier,
	value: String = "",
	placeholder: String = "",
	onValueChange: (String) -> Unit,
	isError: Boolean = false,
	keyboardOptions: KeyboardOptions = KeyboardOptions(
		keyboardType = KeyboardType.Text,
		imeAction = ImeAction.Next,
		capitalization = KeyboardCapitalization.Sentences,
	)
) {
	val focusManager = LocalFocusManager.current

	Box(
		modifier = modifier
			.fillMaxWidth()
			.padding(top = 16.dp)
	) {
		OutlinedTextField(
			modifier = modifier
				.fillMaxWidth(),
			value = value,
			onValueChange = onValueChange,
			placeholder = {
				Text(
					text = placeholder,
					color = Color.Black.copy(0.5f)
				)
			},
			keyboardActions = KeyboardActions {
				focusManager.clearFocus()
			},
			keyboardOptions = keyboardOptions,
			colors = TextFieldDefaults.outlinedTextFieldColors(
				textColor = Color.Black,
				focusedBorderColor = Color.Black,
				unfocusedBorderColor = Color.Black.copy(0.5f),
				backgroundColor = Color.White
			)
		)
	}
}