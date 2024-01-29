package com.example.sportshall.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Converter {

	fun getDateFromMilliseconds(millis: Long, locale: Locale): String {
		return runCatching {
			val dateFormat = "dd.MM.yyyy"
			val formatter = SimpleDateFormat(dateFormat, locale)
			val calendar = Calendar.getInstance()

			calendar.timeInMillis = millis
			formatter.format(calendar.time)
		}.getOrDefault(millis.toString())
	}

	fun formatCreateAt(
		inputDate: String,
		locale: Locale,
	): String {
		val inputFormat = SimpleDateFormat("dd.MM.yyyy", locale)
		val outputFormat = SimpleDateFormat("dd.MM.yyyy", locale)

		return runCatching {
			val date = inputFormat.parse(inputDate)
			outputFormat.format(date)
		}.getOrDefault(inputDate)
	}

	fun formatDefault(
		inputDate: String,
	): String {
		val inputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

		return runCatching {
			val date = inputFormat.format(inputDate)
			date
		}.getOrDefault(inputDate)
	}

	fun formatDateWithTimeInterval(
		startDate: String?,
		endDate: String?,
		locale: Locale,
	): String {
		if (startDate.isNullOrBlank() || endDate.isNullOrBlank()) return "uncertain"
		val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
		val outputFormat = SimpleDateFormat("dd MMMM", locale)

		return runCatching {
			val date1 = inputFormat.parse(startDate)
			val date2 = inputFormat.parse(endDate)

			val date = outputFormat.format(date1)

			val timeFormat = SimpleDateFormat("HH:mm", locale)

			val time1 = timeFormat.format(date1)
			val time2 = timeFormat.format(date2)

			val result = "$date $time1-$time2"
			result
		}.getOrDefault(startDate)
	}

	fun convertLongToDate(timestamp: Long, pattern: String = "dd.MM.yyyy"): String {
		val dateFormat = SimpleDateFormat(pattern)
		val date = Date(timestamp)
		return dateFormat.format(date)
	}
}