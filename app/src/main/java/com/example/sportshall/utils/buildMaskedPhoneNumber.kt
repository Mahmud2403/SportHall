package com.example.sportshall.utils

fun buildMaskedPhoneNumber(digits: String): String {
	val maskedPhoneNumber = buildString {
		append("+7 ")
		if (digits.length >= 1) {
			append("(${digits[0]}")
		}
		if (digits.length >= 2) {
			append(digits[1])
		}
		if (digits.length >= 3) {
			append(digits[2])
		}
		append(") ")
		if (digits.length >= 3) {
			append(digits.substring(3, minOf(digits.length, 6)))
		}
		if (digits.length >= 5) {
			append(" ")
		}
		if (digits.length >= 6) {
			append(digits.substring(6, minOf(digits.length, 8)))
		}
		if (digits.length >= 7) {
			append(" ")
		}
		if (digits.length >= 9) {
			append(digits.substring(8, minOf(digits.length, 10)))
		}
		if (digits.length >= 10) {
			append(" ")
		}
		if (digits.length >= 11) {
			append(digits.substring(10, minOf(digits.length, 12)))
		}
	}
	return maskedPhoneNumber
}