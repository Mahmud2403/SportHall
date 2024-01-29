package com.example.sportshall.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportshall.ui.theme.Primary
import com.example.sportshall.utils.ContinuousSelectionHelper.getSelection
import com.example.sportshall.utils.DateSelection
import com.example.sportshall.utils.backgroundHighlightRange
import com.example.sportshall.utils.displayText
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.customView
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun DateDialog(
	dialogState: MaterialDialogState,
	dialogClose: () -> Unit,
	dateSelected: (startDate: LocalDate, endDate: LocalDate) -> Unit = { _, _ -> },
) {

	MaterialDialog(
		dialogState = dialogState,
		shape = RoundedCornerShape(24.dp)
	) {
		customView {
			MappaRangeCalendar(
				dateSelected = dateSelected,
				close = dialogClose
			)
		}
	}
}

@Composable
fun MappaRangeCalendar(
	close: () -> Unit = {},
	dateSelected: (startDate: LocalDate, endDate: LocalDate) -> Unit,
) {
	val currentMonth = remember { YearMonth.now() }
	val startMonth = remember { YearMonth.now() }
	val endMonth = remember { currentMonth.plusYears(7) }
	val today = remember { LocalDate.now() }
	var selection by remember { mutableStateOf(DateSelection()) }
	val daysOfWeek = remember { daysOfWeek() }
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White),
	) {
		Column {
			val state = rememberCalendarState(
				startMonth = currentMonth,
				endMonth = endMonth,
				firstVisibleMonth = startMonth,
				firstDayOfWeek = daysOfWeek.first(),
			)
			CalendarTop(
				daysOfWeek = daysOfWeek,
				selection = selection,
				close = close,
				clearDates = { selection = DateSelection() },
			)
			VerticalCalendar(
				calendarScrollPaged = false,
				state = state,
				dayContent = { value ->
					Day(
						value,
						today = today,
						selection = selection,
					) { day ->
						if (day.position == DayPosition.MonthDate &&
							(day.date == today || day.date.isAfter(today))
						) {
							selection = getSelection(
								clickedDate = day.date,
								dateSelection = selection,
							)
						}
					}
				},
				monthHeader = { month -> MonthHeader(month) },
			)
		}
		CalendarBottom(
			modifier = Modifier
				.wrapContentHeight()
				.fillMaxWidth()
				.background(Color.White)
				.align(Alignment.BottomCenter),
			selection = selection,
			save = {
				val (startDate, endDate) = selection
				if (startDate != null && endDate != null) {
					dateSelected(startDate, endDate)
				}
			},
		)
	}
}


@Composable
private fun Day(
	day: CalendarDay,
	today: LocalDate,
	selection: DateSelection,
	onClick: (CalendarDay) -> Unit,
) {
	var textColor = Color.Transparent
	Box(
		modifier = Modifier
			.aspectRatio(1f) // This is important for square-sizing!
			.clickable(
				enabled = day.position == DayPosition.MonthDate && day.date >= today,
				interactionSource = remember { MutableInteractionSource() },
				indication = rememberRipple(radius = 16.dp, bounded = true, color = Color.DarkGray),
				onClick = { onClick(day) },
			)
			.backgroundHighlightRange(
				day = day,
				today = today,
				selection = selection,
				selectionColor = Primary,
				continuousSelectionColor = Primary.copy(alpha = 0.8f),
			) { textColor = it },
		contentAlignment = Alignment.Center,
	) {
		androidx.compose.material.Text(
			text = day.date.dayOfMonth.toString(),
			color = textColor
		)
	}
}


@Composable
private fun MonthHeader(calendarMonth: CalendarMonth) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 12.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
	) {
		Text(
			textAlign = TextAlign.Center,
			text = calendarMonth.yearMonth.displayText(),
			fontSize = 18.sp,
			fontWeight = FontWeight.Bold,
		)
	}
}


@Composable
private fun CalendarBottom(
	modifier: Modifier = Modifier,
	selection: DateSelection,
	save: () -> Unit,
) {
	Column(modifier.fillMaxWidth()) {
		Divider()
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 16.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.End
		) {
			Button(
				shape = CircleShape,
				modifier = Modifier
					.height(40.dp)
					.width(100.dp),
				onClick = save,
				enabled = selection.daysBetween != null,
				colors = ButtonDefaults.buttonColors(
					contentColor = Color.White,
					disabledContentColor = Color.Gray,
					disabledBackgroundColor = Color.LightGray,
					backgroundColor = Primary
				)
			) {
				Text(
					text = "Save",
					style = MaterialTheme.typography.labelLarge
				)
			}
		}
	}
}


@Composable
private fun CalendarTop(
	modifier: Modifier = Modifier,
	daysOfWeek: List<DayOfWeek>,
	selection: DateSelection,
	close: () -> Unit,
	clearDates: () -> Unit,
) {
	Column(modifier.fillMaxWidth()) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 6.dp, bottom = 10.dp),
			verticalArrangement = Arrangement.spacedBy(10.dp),
		) {
			Row(
				modifier = Modifier
					.height(IntrinsicSize.Max)
					.fillMaxWidth()
					.padding(top = 8.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Icon(
					modifier = Modifier
						.size(22.dp)
						.clickable(onClick = close)
						.clip(CircleShape),
					imageVector = Icons.Default.Close,
					contentDescription = "Close",
				)
				Text(
					modifier = Modifier
						.clip(CircleShape)
						.clickable(onClick = clearDates),
					text = "Clear",
					style = MaterialTheme.typography.bodyLarge,
				)
			}
			val daysBetween = selection.daysBetween
			val text = if (daysBetween == null) "Select dates" else {
				// Ideally you'd do this using the strings.xml file
				"$daysBetween ${if (daysBetween == 1L) "day" else "days"}"
			}
			androidx.compose.material.Text(
				modifier = Modifier,
				text = text,
				style = MaterialTheme.typography.titleMedium
			)
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 4.dp),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				for (dayOfWeek in daysOfWeek) {
					Text(
						color = Color.DarkGray,
						text = dayOfWeek.displayText(),
						style = MaterialTheme.typography.bodyLarge
					)
				}
			}
		}
		Divider()
	}
}