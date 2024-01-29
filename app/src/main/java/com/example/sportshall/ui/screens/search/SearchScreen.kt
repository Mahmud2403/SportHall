package com.example.sportshall.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportshall.common.InformationList
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.data.local_source.data.TrainerEntity
import com.example.sportshall.ui.theme.Primary


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
	modifier: Modifier = Modifier,
	onClickBack: () -> Unit,
	viewModel: SearchViewModel,
	onClickInformation: (informationId: Int, informationType: String) -> Unit,
) {
	val sportInformationList by viewModel.sportHallInformation.collectAsStateWithLifecycle()

	val searchText by viewModel.searchText.collectAsStateWithLifecycle()
	val focusRequester = remember { FocusRequester() }
	val focusManager = LocalFocusManager.current

	LaunchedEffect(Unit) { focusRequester.requestFocus() }

	Column(
		modifier = modifier
			.fillMaxSize()
			.background(Color.White)
			.systemBarsPadding()
	) {
		Row(
			modifier = Modifier
				.padding(start = 12.dp)
				.fillMaxWidth()
				.padding(end = 16.dp, bottom = 10.dp, top = 10.dp),
			horizontalArrangement = Arrangement.spacedBy(8.dp),
			verticalAlignment = Alignment.CenterVertically
		) {

			Icon(
				modifier = Modifier
					.size(16.dp)
					.clickable(
						interactionSource = remember { MutableInteractionSource() },
						indication = rememberRipple(
							bounded = false,
							radius = 15.dp,
						),
						onClick = onClickBack
					),
				imageVector = Icons.Default.ArrowBack,
				contentDescription = null
			)

			BasicTextField(
				value = searchText,
				onValueChange = viewModel::changeSearchText,
				modifier = Modifier
					.weight(1f)
					.focusRequester(focusRequester),
				interactionSource = remember { MutableInteractionSource() },
				enabled = true,
				visualTransformation = VisualTransformation.None,
				singleLine = true,
				keyboardActions = KeyboardActions(onSearch = {
//					viewModel.addSearchHistory(searchText.value)
					focusManager.clearFocus()
				}),
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Search
				),
				cursorBrush = SolidColor(Primary)
			) { innerTextField ->

				TextFieldDefaults.TextFieldDecorationBox(
					value = searchText,
					innerTextField = innerTextField,
					singleLine = true,
					enabled = true,
					placeholder = {
						Text(
							modifier = Modifier,
							text = "Search",
							style = MaterialTheme.typography.bodyLarge
						)
					},
					interactionSource = MutableInteractionSource(),
					contentPadding = PaddingValues(0.dp),
					visualTransformation = VisualTransformation.None,
				)
			}
			if (searchText.isNotEmpty()) {
				Icon(
					modifier = Modifier
						.size(12.dp)
						.clickable(
							interactionSource = remember { MutableInteractionSource() },
							onClick = { viewModel.changeSearchText("") },
							indication = null,
						),
					imageVector = Icons.Outlined.Close,
					tint = Color.Black,
					contentDescription = null
				)
			}
		}
		InformationList(
			sportHallInformationList = sportInformationList,
			onClickInformation = onClickInformation,
		)
	}
}