package com.example.sportshall.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SubTitle(
	modifier: Modifier = Modifier,
	name: String,
) {
	Text(
		modifier = modifier,
		text = name,
		style = MaterialTheme.typography.titleLarge,
		fontWeight = FontWeight.Medium,
		fontSize = 16.sp,
		lineHeight = 21.sp,
		color = Color.Black
	)
}