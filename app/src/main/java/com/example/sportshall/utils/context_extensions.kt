package com.example.sportshall.utils

import android.content.Context
import android.os.Build
import androidx.annotation.StringRes
import java.util.Locale

fun Context.getStringResource(@StringRes resId: Int) = resources.getString(resId)

val Context.currentLocale: Locale
	get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
		resources.configuration.locales[0]
	} else {
		resources.configuration.locale
	} ?: Locale.getDefault()