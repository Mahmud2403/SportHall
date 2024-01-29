package com.example.sportshall.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun SportHallSearchBar(
	modifier: Modifier = Modifier,
	border: BorderStroke? = null,
	innerPaddingValues: PaddingValues = PaddingValues(0.dp),
	shapes: Shape,
	leadingIcon: @Composable (() -> Unit)? = null,
	placeholder: @Composable (() -> Unit),
	trailingIcon: @Composable (() -> Unit)? = null,
	onClick: () -> Unit
) {

	Surface(
		modifier = modifier.clickable(
			interactionSource = remember{
				MutableInteractionSource()
			},
			indication = rememberRipple(),
			onClick = onClick
		),
		border = border,
		shape = shapes,
		color = Color.White,
	) {
		Row(
			modifier = Modifier
				.padding(innerPaddingValues)
				.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(14.dp)
		) {
			leadingIcon?.invoke()
			placeholder.invoke()
			trailingIcon?.invoke()
		}
	}
}