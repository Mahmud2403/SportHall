package com.example.sportshall.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PhoneField(
	modifier: Modifier = Modifier,
	phone: String = "",
	placeholder: String = "",
	mask: String = "+7 (000) - 000 - 00 - 00",
	maskNumber: Char = '0',
	onPhoneChanged: (String) -> Unit
) {
	Box(
		modifier = Modifier
			.fillMaxWidth(1f)
			.padding(vertical = 16.dp)
	) {
		OutlinedTextField(
			modifier = modifier
				.fillMaxWidth(),
			value = phone,
			onValueChange = { it ->
				onPhoneChanged(it.take(mask.count { it == maskNumber }))
			},
			placeholder = {
				Text(
					text = placeholder,
					color = Color.Black.copy(0.5f)
				)
			},
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
			visualTransformation = PhoneVisualTransformation(mask, maskNumber),
			colors = TextFieldDefaults.outlinedTextFieldColors(
				textColor = Color.Black,
				focusedBorderColor = Color.Black,
				unfocusedBorderColor = Color.Black.copy(0.5f),
				backgroundColor = Color.White,
			),
			maxLines = 1,
		)
	}
}

class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {

	private val maxLength = mask.count { it == maskNumber }

	override fun filter(text: AnnotatedString): TransformedText {
		val trimmed = if (text.length > maxLength) text.take(maxLength) else text

		val annotatedString = buildAnnotatedString {
			if (trimmed.isEmpty()) return@buildAnnotatedString

			var maskIndex = 0
			var textIndex = 0
			while (textIndex < trimmed.length && maskIndex < mask.length) {
				if (mask[maskIndex] != maskNumber) {
					val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
					append(mask.substring(maskIndex, nextDigitIndex))
					maskIndex = nextDigitIndex
				}
				append(trimmed[textIndex++])
				maskIndex++
			}
		}

		return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is PhoneVisualTransformation) return false
		if (mask != other.mask) return false
		if (maskNumber != other.maskNumber) return false
		return true
	}

	override fun hashCode(): Int {
		return mask.hashCode()
	}
}

private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

	override fun originalToTransformed(offset: Int): Int {
		var noneDigitCount = 0
		var i = 0
		while (i < offset + noneDigitCount) {
			if (mask[i++] != numberChar) noneDigitCount++
		}
		return offset + noneDigitCount
	}

	override fun transformedToOriginal(offset: Int): Int =
		offset - mask.take(offset).count { it != numberChar }
}