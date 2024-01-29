package com.example.sportshall.ui.screens.sport_hall_data.clients.view_clients

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportshall.data.local_source.data.ClientEntity
import com.example.sportshall.ui.screens.sport_hall_data.clients.ClientUiState
import com.example.sportshall.ui.screens.sport_hall_data.clients.components.ClientInformationItem
import com.example.sportshall.ui.theme.Primary
import com.example.sportshall.utils.buildMaskedPhoneNumber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewClientsScreen(
	state: ClientUiState,
	onClickBack: () -> Unit,
	onDelete: (ClientEntity) -> Unit,
	onClickClient: (clientId: Int) -> Unit,
) {

	val topAppbarState = rememberTopAppBarState()
	val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppbarState)
	val showTopAppBarShadow by remember { derivedStateOf { topAppbarState.collapsedFraction == 1f } }

	val context = LocalContext.current.applicationContext


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
						text = "View client",
						maxLines = 1,
						overflow = TextOverflow.Ellipsis
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
			items(
				items = state.clientEntities,
				key = ClientEntity::id,
			) { client ->
				Spacer(modifier = Modifier.padding(top = 24.dp))
				val dismissState = rememberDismissState(
					confirmStateChange = { state ->
						if (state == DismissValue.DismissedToStart) {
							Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show()
							onDelete(client)
							true
						} else {
							false
						}
					}
				)

				DisposableEffect(dismissState.currentValue) {
					onDispose { }
				}
				SwipeToDismiss(
					state = dismissState,
					directions = setOf(
						DismissDirection.EndToStart,
						DismissDirection.StartToEnd,
					),

					background = {
						val backgroundColor by animateColorAsState(
							when (dismissState.targetValue) {
								DismissValue.DismissedToStart -> Color.Red.copy(
									alpha = 0.8f
								)

								else -> Primary.copy(0.5f)
							},
							label = ""
						)

						val iconScale by animateFloatAsState(
							targetValue = if (dismissState.targetValue == DismissValue.DismissedToStart) 1.3f else 0.5f,
							label = ""
						)

						Box(
							Modifier
								.fillMaxSize()
								.background(color = backgroundColor)
								.padding(end = 16.dp),
							contentAlignment = Alignment.CenterEnd
						) {
							Icon(
								modifier = Modifier.scale(iconScale),
								imageVector = Icons.Default.Delete,
								contentDescription = "Delete",
								tint = Color.White
							)
						}
					},
					dismissContent = {
						ClientInformationItem(
							modifier = Modifier.fillMaxWidth(),
							id = "${client.id}.",
							name = "${client.firstName}  ${client.lastName}",
							phoneNumber = buildMaskedPhoneNumber(client.phoneNumber),
							onDelete = { onDelete(client) },
							onClickClient = { onClickClient(client.id) },
							isVisibleDelete = true,
						)
					}
				)
			}
		}
	}
}