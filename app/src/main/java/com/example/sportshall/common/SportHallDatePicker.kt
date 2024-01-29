package com.example.sportshall.common

import android.annotation.SuppressLint
import android.widget.DatePicker
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePicker
import com.example.sportshall.ui.screens.home.Date
import com.example.sportshall.ui.screens.home.DateRange
import java.util.Calendar


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportHallDatePicker(
	state: DatePickerState = rememberDatePickerState(),
	onDismissRequest: () -> Unit,
	onConfirmClick: (Long) -> Unit,
	onDismissClick: () -> Unit,
) {
	val confirmEnabled = remember { derivedStateOf { state.selectedDateMillis != null } }
	DatePickerDialog(
		modifier = Modifier
			.shadow(
				elevation = 2.dp,
				shape = DatePickerDefaults.shape,
			),
		colors = DatePickerDefaults.colors(
			containerColor = Color.White,
		),
		onDismissRequest = onDismissRequest,
		confirmButton = {
			TextButton(
				onClick = {
					state.selectedDateMillis?.let(onConfirmClick)
				},
				enabled = confirmEnabled.value
			) {
				Text("OK")
			}
		},
		dismissButton = {
			TextButton(
				onClick = onDismissClick
			) {
				Text("Cancel")
			}
		}
	) {
		DatePicker(
			state = state,
			colors = DatePickerDefaults.colors(
				titleContentColor = Color.Black,
				headlineContentColor = Color.Black,
				weekdayContentColor = Color.Black,
				subheadContentColor = Color.Black,
				yearContentColor = Color.Black,
				currentYearContentColor = Color.Black,
				selectedYearContentColor = Color.Black,
				dayContentColor = Color.Black,
				disabledDayContentColor = Color.Black,
				selectedDayContentColor = Color.Black,
				disabledSelectedDayContentColor = Color.Black,
				dayInSelectionRangeContentColor = Color.Black,
				dayInSelectionRangeContainerColor = Color.White,
				disabledSelectedDayContainerColor = Color.White,
				containerColor = Color.White,
			)
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportHallDateRangePicker(
	state: DatePickerState = rememberDatePickerState(),
	onDismissRequest: () -> Unit,
	onConfirmClick: (Long) -> Unit,
	onDismissClick: () -> Unit,
) {
	val confirmEnabled = remember {
		derivedStateOf {
			state.selectedDateMillis != null
		}
	}
	DatePickerDialog(
		modifier = Modifier
			.shadow(
				elevation = 2.dp,
				shape = DatePickerDefaults.shape,
			),
		colors = DatePickerDefaults.colors(
			containerColor = Color.White,
		),
		tonalElevation = 0.dp,
		onDismissRequest = onDismissRequest,
		confirmButton = {
			TextButton(
				onClick = {
					state.selectedDateMillis?.let(onConfirmClick)
				},
				enabled = confirmEnabled.value
			) {
				Text("OK")
			}
		},
		dismissButton = {
			TextButton(
				onClick = onDismissClick
			) {
				Text("Cancel")
			}
		}
	) {
//		val restrictions = remember(datePickerType, datePickerState) {
//
//			val currentDate = Calendar.getInstance().time.time
//			val oneDayMillis = 24 * 60 * 60 * 1000
//
//			when (datePickerType) {
//				Date.Start -> {
//					(currentDate - oneDayMillis)..(datePickerState?.endData ?: Long.MAX_VALUE)
//				}
//
//				Date.End -> {
//					(datePickerState?.startDate ?: (currentDate - oneDayMillis))..Long.MAX_VALUE
//				}
//			}
//		}
		DatePicker(
			state = state,
			colors = DatePickerDefaults.colors(
				titleContentColor = Color.Black,
				headlineContentColor = Color.Black,
				weekdayContentColor = Color.Black,
				subheadContentColor = Color.Black,
				yearContentColor = Color.Black,
				currentYearContentColor = Color.Black,
				selectedYearContentColor = Color.Black,
				dayContentColor = Color.Black,
				disabledDayContentColor = Color.Black,
				selectedDayContentColor = Color.Black,
				disabledSelectedDayContentColor = Color.Black,
				dayInSelectionRangeContentColor = Color.Black,
				dayInSelectionRangeContainerColor = Color.White,
				disabledSelectedDayContainerColor = Color.White,
				containerColor = Color.White,
			)
//			dateValidator = {
//				it in restrictions
//			}
		)
	}
}