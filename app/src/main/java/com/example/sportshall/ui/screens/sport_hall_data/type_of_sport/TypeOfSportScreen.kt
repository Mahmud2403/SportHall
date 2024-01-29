package com.example.sportshall.ui.screens.sport_hall_data.type_of_sport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportshall.data.local_source.data.hallList
import com.example.sportshall.data.local_source.data.typeOfSportList
import com.example.sportshall.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeOfSportScreen(
	onClickBack: () -> Unit,
) {
	val topAppbarState = rememberTopAppBarState()
	val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppbarState)
	val showTopAppBarShadow by remember { derivedStateOf { topAppbarState.collapsedFraction == 1f } }

	Scaffold(
		modifier = Modifier
			.fillMaxSize()
			.systemBarsPadding()
			.nestedScroll(scrollBehavior.nestedScrollConnection),
		topBar = {
			LargeTopAppBar(
				modifier = Modifier
					.graphicsLayer {
						shadowElevation = if (showTopAppBarShadow) 5f else 0f
					},
				title = {
					Text(
						modifier = Modifier,
						text = "View type of sport",
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
					)
				},
				navigationIcon = {
					IconButton(onClick = onClickBack) {
						Icon(
							imageVector = Icons.Outlined.ArrowBack,
							contentDescription = null,
							tint = Color.White
						)
					}
				},
				colors = TopAppBarDefaults.largeTopAppBarColors(
					scrolledContainerColor = Primary,
					titleContentColor = Color.White,
					containerColor = Primary,
				),
				scrollBehavior = scrollBehavior,
			)
		}
	) {
		LazyColumn(
			modifier = Modifier
				.background(Primary)
				.padding(
					top = it.calculateTopPadding(),
					start = 16.dp,
					end = 16.dp,
				)
				.fillMaxSize(),
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			item {
				typeOfSportList.forEach {
					Column(
						modifier = Modifier
							.fillMaxWidth()
							.padding(vertical = 8.dp)
					) {
						Text(
							modifier = Modifier.fillMaxWidth(),
							text = "${it.id}.",
							fontSize = 18.sp,
							fontWeight = FontWeight.Bold,
							color = Color.Black,
						)
						Text(
							modifier = Modifier.padding(start = 8.dp),
							text = "Type of sport: ${it.typeOfOccupation}",
							color = Color.Black,
						)
						Text(
							modifier = Modifier.padding(start = 8.dp),
							text = "Price in sport: ${it.price}",
							color = Color.Black,
						)
					}
				}
			}
		}
	}
}
