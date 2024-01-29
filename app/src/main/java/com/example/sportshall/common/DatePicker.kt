package com.example.sportshall.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportshall.ui.screens.home.Date
import com.example.sportshall.utils.Converter
import com.example.sportshall.utils.currentLocale

@Composable
fun DatePicker(
	modifier: Modifier = Modifier,
	date: String?,
	onClickDate: () -> Unit,
) {
	Box(
		modifier = modifier
			.padding(top = 8.dp)
			.fillMaxWidth()
	) {
		PickerField(
			modifier = modifier.fillMaxWidth(),
			text = date ?: "",
			onClick = onClickDate,
			placeholder = "Choose birthday client",
		)
	}
}

@Composable
fun DateRangePicker(
	modifier: Modifier = Modifier,
	dateStart: Long?,
	dateEnd: Long?,
	onClickDate: (Date) -> Unit,
) {
	val context = LocalContext.current
	Column(
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "The range of birthdays",
			color = Color.Black
		)
		Row(
			modifier = modifier
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Date.values().forEach { dateType ->

				val dateStartFormat = remember(dateStart) {
					dateStart?.let {
						Converter.getDateFromMilliseconds(
							millis = dateStart,
							locale = context.currentLocale,
						)
					}
				}

				val dateEndFormat = remember(dateEnd) {
					dateEnd?.let {
						Converter.getDateFromMilliseconds(
							millis = dateEnd,
							locale = context.currentLocale,
						)
					}
				}
				DatePickerCard(
					modifier = Modifier,
					date = if (dateType == Date.Start) dateStartFormat else dateEndFormat,
					onDateClick = {
						onClickDate(dateType)
					},
					placeholder = "Selected date",
					name = if (dateType == Date.Start) {
						"Date start"
					} else {
						"Date end"
					}
				)
			}
		}
	}
}

@Composable
fun DatePickerCard(
	modifier: Modifier = Modifier,
	date: String?,
	onDateClick: () -> Unit,
	placeholder: String,
	name: String,
) {
	Column(
		verticalArrangement = Arrangement.SpaceBetween,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		SubTitle(name = name)
		PickerField(
			modifier = modifier,
			text = date ?: "",
			onClick = onDateClick,
			placeholder = placeholder,
		)
	}
}


@Composable
fun PickerField(
	modifier: Modifier = Modifier,
	text: String,
	onClick: () -> Unit,
	placeholder: String,
) {
//	val border by animateColorAsState(targetValue = if (isError) Color.Red else CreatePublicationBorder)

	Box(
		modifier = modifier
			.background(color = Color.White)
			.clickable(
				onClick = onClick
			)
			.border(
				width = 1.dp,
				color = Color.Black.copy(0.5f),
			)
			.padding(16.dp),
	) {
		Text(
			modifier = Modifier,
			text = text.ifEmpty { placeholder },
			color = if (text.isEmpty()) Color.Black.copy(0.5f) else Color.Black,
			textAlign = TextAlign.Start,
			fontWeight = FontWeight.Normal,
			maxLines = 1,
		)
	}
}
