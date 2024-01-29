package com.example.sportshall.ui.screens.sport_hall_data.detail_information

import androidx.compose.runtime.Composable
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.components.DetailInformationClientBlock
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.components.DetailInformationSeasonTicketBlock
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.components.DetailInformationTrainerBlock
import com.example.sportshall.utils.Converter
import com.example.sportshall.utils.buildMaskedPhoneNumber


@Composable
fun DetailInformationScreen(
	uiState: DetailInformationUiState,
	type: TypeDetailInformation,
	onClickBack: () -> Unit,
) {
	when (type){
		TypeDetailInformation.Client -> {
			DetailInformationClientBlock(
				onClickBack = onClickBack,
				firstName = uiState.firstName,
				lastName = uiState.lastName,
				patronymic = uiState.patronymic,
				birthday = uiState.birthDay?.let { Converter.convertLongToDate(it) },
				phoneNumber = buildMaskedPhoneNumber(uiState.phoneNumber),
				gender = uiState.gender
			)
		}

		TypeDetailInformation.Trainer -> {
			DetailInformationTrainerBlock(
				onClickBack = onClickBack,
				workExperience = uiState.workExperience,
				firstName = uiState.firstName,
				lastName = uiState.lastName,
				patronymic = uiState.patronymic,
				phoneNumber = buildMaskedPhoneNumber(uiState.phoneNumber),
				typeOfOccupation = uiState.typeOfOccupation,
			)
		}

		TypeDetailInformation.SeasonTicket -> {
			DetailInformationSeasonTicketBlock(
				onClickBack = onClickBack,
				idClient = uiState.nameClient,
				idTrainer = uiState.nameTrainer,
				term = uiState.term,
				change = uiState.change,
			)
		}
	}
}