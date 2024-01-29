package com.example.sportshall.data.local_source.event

sealed interface SeasonTicketEvent {
	object SaveSeasonTicketEvent: SeasonTicketEvent
	object ShowDialog: SeasonTicketEvent
	object HideDialog: SeasonTicketEvent
	data class SetTerm(val term: String): SeasonTicketEvent
	data class SetIdClient(val nameClient: String): SeasonTicketEvent
	data class SetIdTrainer(val nameTrainer: String): SeasonTicketEvent
	data class SetChange(val change: String): SeasonTicketEvent
}