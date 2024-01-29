package com.example.sportshall.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.TrainerEntity
import com.example.sportshall.ui.screens.search.model.SportHallInformation
import com.example.sportshall.ui.screens.sport_hall_data.clients.components.ClientInformationItem
import com.example.sportshall.ui.screens.sport_hall_data.detail_information.TypeDetailInformation
import com.example.sportshall.ui.screens.sport_hall_data.trainers.components.TrainerInformationItem
import com.example.sportshall.utils.buildMaskedPhoneNumber

@Composable
fun InformationList(
	onClickInformation: (informationId: Int, informationType: String) -> Unit,
	lazyListState: LazyListState = rememberLazyListState(),
	sportHallInformationList: List<SportHallInformation>,
) {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize(),
		state = lazyListState,
		contentPadding = PaddingValues(
			bottom = 16.dp,
			top = 16.dp,
			start = 16.dp,
			end = 16.dp
		),
		verticalArrangement = Arrangement.spacedBy(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		items(
			items = sportHallInformationList,
		) { information ->
			InformationListItem(
				information = information,
				onClickInformation = onClickInformation,
			)
		}
	}
}

@Composable
fun InformationListItem(
	modifier: Modifier = Modifier,
	information: SportHallInformation?,
	onClickInformation: (informationId: Int, informationType: String) -> Unit,
) {
	when (information) {
		is SportHallInformation.ClientInformation -> {
			Text(
				modifier = modifier
					.fillMaxWidth(),
				text = "Client:",
				fontSize = 18.sp,
				fontWeight = FontWeight(800),
				color = Color.Black,
			)
			ClientInformationItem(
				id = "${information.id}.  ",
				name = "${information.firstName} ${information.lastName}",
				phoneNumber = buildMaskedPhoneNumber(information.phoneNumber),
				onDelete = {  },
				onClickClient = {
					onClickInformation(
						information.id,
						TypeDetailInformation.Client.name,
					)
				},
				isVisibleDelete = false,
			)
		}

		is SportHallInformation.TrainerInformation -> {
			Text(
				modifier = modifier
					.fillMaxWidth(),
				text = "Trainer:",
				fontSize = 18.sp,
				fontWeight = FontWeight(800),
				color = Color.Black,
			)
			TrainerInformationItem(
				id = "${information.id}.  ",
				name = "${information.firstName} ${information.lastName}",
				phoneNumber = buildMaskedPhoneNumber(information.phoneNumber),
				onDelete = {},
				onClickTrainer = {
					onClickInformation(
						information.id,
						TypeDetailInformation.Trainer.name,
					)
				},
				isVisibleDelete = false
			)
		}

		else -> Unit
	}
}