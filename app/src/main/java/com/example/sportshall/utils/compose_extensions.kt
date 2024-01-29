package com.example.sportshall.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.sportshall.ui.theme.Primary
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun <T> rememberMutableStateOf(initValue: T) = remember {
	mutableStateOf(initValue)
}

fun Modifier.backgroundHighlightRange(
	day: CalendarDay,
	today: LocalDate,
	selection: DateSelection,
	selectionColor: Color,
	continuousSelectionColor: Color,
	textColor: (Color) -> Unit,
): Modifier = composed {
	val (startDate, endDate) = selection
	val padding = 4.dp
	when (day.position) {
		DayPosition.MonthDate -> {
			when {
				day.date.isBefore(today) -> {
					textColor(Color.LightGray)
					this
				}

				startDate == day.date && endDate == null -> {
					textColor(Color.White)
					padding(padding)
						.background(color = selectionColor, shape = CircleShape)
				}

				day.date == startDate -> {
					textColor(Color.White)
					padding(vertical = padding)
						.background(
							color = continuousSelectionColor,
							shape = HalfSizeShape(clipStart = true),
						)
						.padding(horizontal = padding)
						.background(color = selectionColor, shape = CircleShape)
				}

				startDate != null && endDate != null && (day.date > startDate && day.date < endDate) -> {
					textColor(Color.White)
					padding(vertical = padding)
						.background(color = continuousSelectionColor)
				}

				day.date == endDate -> {
					textColor(Color.White)
					padding(vertical = padding)
						.background(
							color = continuousSelectionColor,
							shape = HalfSizeShape(clipStart = false),
						)
						.padding(horizontal = padding)
						.background(color = selectionColor, shape = CircleShape)
				}

				day.date == today -> {
					textColor(Primary)
					padding(padding)
						.border(
							width = 1.dp,
							shape = CircleShape,
							color = Primary,
						)
				}

				else -> {
					textColor(Color.Black)
					this
				}
			}
		}

		DayPosition.InDate -> {
			textColor(Color.Transparent)
			if (startDate != null && endDate != null &&
				ContinuousSelectionHelper.isInDateBetweenSelection(day.date, startDate, endDate)
			) {
				padding(vertical = padding)
					.background(color = continuousSelectionColor)
			} else this
		}

		DayPosition.OutDate -> {
			textColor(Color.Transparent)
			if (startDate != null && endDate != null &&
				ContinuousSelectionHelper.isOutDateBetweenSelection(day.date, startDate, endDate)
			) {
				padding(vertical = padding)
					.background(color = continuousSelectionColor)
			} else this
		}
	}
}

class HalfSizeShape(private val clipStart: Boolean) : Shape {
	override fun createOutline(
		size: Size,
		layoutDirection: LayoutDirection,
		density: Density,
	): Outline {
		val half = size.width / 2f
		val offset = if (layoutDirection == LayoutDirection.Ltr) {
			if (clipStart) Offset(half, 0f) else Offset.Zero
		} else {
			if (clipStart) Offset.Zero else Offset(half, 0f)
		}
		return Outline.Rectangle(Rect(offset, Size(half, size.height)))
	}
}

val screenHeightDp: Dp
	@Composable
	get() = LocalConfiguration.current.screenHeightDp.dp

val screenWidthDp: Dp
	@Composable
	get() = LocalConfiguration.current.screenWidthDp.dp

val screenHeightPx: Float
	@Composable
	get() = LocalDensity.current.run { screenHeightDp.toPx() }
