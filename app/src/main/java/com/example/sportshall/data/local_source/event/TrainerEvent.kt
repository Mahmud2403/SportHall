package com.example.sportshall.data.local_source.event

sealed interface TrainerEvent {
	object SaveTrainer: TrainerEvent
	object ShowDialog: TrainerEvent
	object HideDialog: TrainerEvent
	data class SetFirstName(val firstName: String): TrainerEvent
	data class SetLastName(val lastName: String ): TrainerEvent
	data class SetPatronymic(val patronymic: String ): TrainerEvent
	data class SetPhoneNumber(val phoneNumber: String ): TrainerEvent
	data class SetWorkExperience(val workExperience: String): TrainerEvent
	data class SetTypeOfOccupation(val typeOfOccupation: String): TrainerEvent
}