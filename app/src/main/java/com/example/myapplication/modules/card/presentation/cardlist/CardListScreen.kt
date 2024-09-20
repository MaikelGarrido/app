package com.example.myapplication.modules.card.presentation.cardlist

import ItemCard
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.modules.card.domain.model.Card.Companion.getFullName
import com.example.myapplication.modules.card.presentation.cardlist.composables.DashedCardView
import com.example.myapplication.modules.shared.CustomTopAppBar
import com.example.myapplication.modules.shared.EmptyListAnimation
import com.example.myapplication.modules.theme.OffWhite
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CardListScreen(
    modifier: Modifier = Modifier,
    state: CardListState,
    onEvent: (CardListEvent) -> Unit = {},
    onClick: () -> Unit = {},
    onInfo: (Card) -> Unit = {},
    onMap: () -> Unit = {},
) {
    val listState = rememberLazyListState()
    var isFabVisible by remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0 }
            .collectLatest { isScrolling -> isFabVisible = !isScrolling }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Listado de tarjetas",
                actionIcon =  Icons.Default.LocationOn,
                onClickAction = onMap
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .background(OffWhite)
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            AnimatedVisibility(
                visible = isFabVisible,
                enter = slideInVertically(initialOffsetY = { -it }) + expandVertically(),
                exit = slideOutVertically(targetOffsetY = { -it }) + shrinkVertically()
            ) {
                DashedCardView(onClick = onClick)
            }

            if (state.cards.isEmpty()) {
                EmptyListAnimation()
            } else {
                LazyColumn(
                    state = listState
                ) {
                    items(state.cards, { it.cardNumber }) { card ->
                        ItemCard(
                            fullName = card.getFullName(),
                            cardNumber = card.cardNumber,
                            onInfo = { onInfo(card) },
                            onRemove = { onEvent(CardListEvent.DeleteCard(card.cardNumber)) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardListScreenPreview() {
    CardListScreen(
        state = CardListState()
    )
}