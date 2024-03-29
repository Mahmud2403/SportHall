buildscript {
	repositories {
		google()
		mavenCentral()
	}
	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
		classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
	}
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	id("com.android.application") version "8.1.0" apply false
	id("com.android.library") version "8.1.0" apply false
	id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}