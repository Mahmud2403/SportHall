package com.example.sportshall.common

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Title(
	text: String,
	value: String
) {
	Row {
		Text(text = text)
		Text(text = value)
	}
}