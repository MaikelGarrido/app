package com.example.myapplication.modules.card.presentation.addcard

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.modules.card.presentation.cardlist.composables.MyIconPack
import com.example.myapplication.modules.card.presentation.cardlist.composables.myiconpack.Icon
import com.example.myapplication.modules.shared.CustomDialogMessage
import com.example.myapplication.modules.shared.CustomOutlinedButton
import com.example.myapplication.modules.shared.CustomOutlinedTextField
import com.example.myapplication.modules.shared.CustomProgressBar
import com.example.myapplication.modules.shared.CustomTopAppBar
import com.example.myapplication.modules.shared.MessageType
import com.example.myapplication.modules.theme.OffWhite
import com.example.myapplication.modules.theme.VeryDarkGray
import com.example.myapplication.utils.formatAsCardNumber
import com.example.myapplication.utils.rememberImeState

@Composable
fun AddCardScreen(
    modifier: Modifier = Modifier,
    state: AddCardState,
    onExit: () -> Unit = { },
    onEvent: (AddCardEvent) -> Unit = { },
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(
                value = scrollState.maxValue,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearOutSlowInEasing
                )
            )
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Agregar tarjeta",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onClickNavigation = onExit
            )
        }
    ) { paddingValues ->
        when {
            state.isLoading -> { CustomProgressBar() }

            state.errorMessage != null -> {
                CustomDialogMessage(
                    message = state.errorMessage,
                    onDismiss = { onEvent(AddCardEvent.OnClear) }
                )
            }

            state.exception != null -> {
                CustomDialogMessage(
                    message = state.exception.message ?: "Hubo un problema",
                    onDismiss = { onEvent(AddCardEvent.OnClear) }
                )
            }

            state.isValid == true -> {
                CustomDialogMessage(
                    message = "Tarjeta autorizada. Se ha guardado correctamente",
                    messageType = MessageType.SUCCESS,
                    onDismiss = { onEvent(AddCardEvent.OnClear) }
                )
            }
        }

        Column(
            modifier = modifier
                .background(OffWhite)
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                onClick = { },
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        imageVector = MyIconPack.Icon,
                        contentDescription = "Background",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = state.cardNumber.formatAsCardNumber(),
                            color = VeryDarkGray,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            CustomOutlinedTextField(
                value = state.cardNumber,
                label = "Número de tarjeta",
                placeholder = "",
                keyboardType = KeyboardType.NumberPassword,
                onTextChanged = {
                    if (it.length <= 16) {
                        onEvent(AddCardEvent.OnChanged(it))
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomOutlinedButton(
                modifier = Modifier.width(200.dp),
                isEnabled = state.isEnabled,
                label = "Validar tarjeta",
                onClick = { onEvent(AddCardEvent.ValidateCard) }
            )
        }
    }
}

@Preview
@Composable
private fun AddCardScreenPreview() {
    AddCardScreen(
        state = AddCardState(),
        onEvent = {}
    )
}