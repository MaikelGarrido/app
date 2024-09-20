package com.example.myapplication.modules.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.myapplication.modules.card.presentation.addcard.AddCardScreen
import com.example.myapplication.modules.card.presentation.addcard.AddCardViewModel
import com.example.myapplication.modules.card.presentation.cardlist.CardListScreen
import com.example.myapplication.modules.card.presentation.cardlist.CardListViewModel
import com.example.myapplication.modules.card.presentation.details.CardDetailsEvent
import com.example.myapplication.modules.card.presentation.details.CardDetailsScreen
import com.example.myapplication.modules.card.presentation.details.CardDetailsViewModel
import com.example.myapplication.modules.map.presentation.MapScreen
import com.example.myapplication.modules.map.presentation.MapViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.CardScreens,
    ) {
        navigation<Screens.CardScreens>(
            startDestination = Screens.CardScreens.CardListScreen
        ) {
            composable<Screens.CardScreens.CardListScreen> {
                val cardListViewModel = hiltViewModel<CardListViewModel>()
                val state by cardListViewModel.state.collectAsStateWithLifecycle()

                CardListScreen(
                    state = state,
                    onMap = { navController.navigate(Screens.MapsScreens) },
                    onEvent = cardListViewModel::onEvent,
                    onClick = { navController.navigate(Screens.CardScreens.AddCard) },
                    onInfo = { navController.navigate(Screens.CardScreens.CardDetails(it)) }
                )
            }

            composable<Screens.CardScreens.AddCard> {
                val addCardViewModel = hiltViewModel<AddCardViewModel>()
                val state by addCardViewModel.state.collectAsStateWithLifecycle()

                AddCardScreen(
                    state = state,
                    onExit = dropUnlessResumed { navController.navigateUp() },
                    onEvent = addCardViewModel::onEvent
                )
            }

            composable<Screens.CardScreens.CardDetails>(
                typeMap = Screens.CardScreens.CardDetails.typeMap
            ) { entry ->
                val cardDetailsViewModel = hiltViewModel<CardDetailsViewModel>()
                val state by cardDetailsViewModel.state.collectAsStateWithLifecycle()
                val route = entry.toRoute<Screens.CardScreens.CardDetails>()
                val card = route.card

                LaunchedEffect(Unit) {
                    cardDetailsViewModel.onEvent(CardDetailsEvent.GetBalance(card.cardNumber))
                }

                CardDetailsScreen(
                    state = state,
                    card = card,
                    onEvent = cardDetailsViewModel::onEvent,
                    onExit =  dropUnlessResumed { navController.navigateUp() }
                )
            }
        }

        navigation<Screens.MapsScreens>(
            startDestination = Screens.MapsScreens.MapHome
        ) {
            composable<Screens.MapsScreens.MapHome> {
                val mapViewModel = hiltViewModel<MapViewModel>()
                val state by mapViewModel.state.collectAsStateWithLifecycle()

                MapScreen(
                    state = state,
                    onEvent = mapViewModel::onEvent,
                    onExit = dropUnlessResumed { navController.navigateUp() }
                )
            }
        }

    }
}