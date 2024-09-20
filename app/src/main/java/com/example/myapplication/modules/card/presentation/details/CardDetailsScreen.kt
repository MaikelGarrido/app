package com.example.myapplication.modules.card.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.myapplication.R
import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.modules.card.presentation.cardlist.composables.MyIconPack
import com.example.myapplication.modules.card.presentation.cardlist.composables.myiconpack.Icon
import com.example.myapplication.modules.shared.CustomDialogMessage
import com.example.myapplication.modules.shared.CustomProgressBar
import com.example.myapplication.modules.shared.CustomTopAppBar
import com.example.myapplication.modules.theme.LimeGreen
import com.example.myapplication.modules.theme.OffWhite

private val myConstraint = ConstraintSet {
    val composable1 = createRefFor("composable1")
    val composable2 = createRefFor("composable2")

    constrain(composable1) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }

    constrain(composable2) {
        bottom.linkTo(composable1.top, margin = (-50).dp)
        start.linkTo(composable1.start)
        end.linkTo(composable1.end)
    }

}


@Composable
fun CardDetailsScreen(
    modifier: Modifier = Modifier,
    state: CardDetailsState,
    card: Card? = null,
    onEvent: (CardDetailsEvent) -> Unit = { },
    onExit: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Detalles de la tarjeta",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onClickNavigation = onExit
            )
        },
    ) { paddingValues ->
        when {
            state.isLoading -> {
                CustomProgressBar()
            }

            state.errorMessage != null -> {
                CustomDialogMessage(
                    message = state.errorMessage,
                    onDismiss = { onEvent(CardDetailsEvent.OnClear) }
                )
            }

            state.exception != null -> {
                CustomDialogMessage(
                    message = state.exception.message ?: "Hubo un problema",
                    onDismiss = { onEvent(CardDetailsEvent.OnClear) }
                )
            }

            else -> {
                Column(
                    modifier = modifier
                        .background(OffWhite)
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    ConstraintLayout(
                        constraintSet = myConstraint,
                    ) {
                        Card(
                            modifier = Modifier
                                .layoutId("composable1")
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            elevation = CardDefaults.cardElevation(5.dp),
                            colors = CardDefaults.cardColors(containerColor = OffWhite),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .height(200.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "Número de tarjeta: ${card?.cardNumber ?: "No tiene tarjeta"}",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "Perfil: ${card?.profileES ?: "No tiene perfil"}",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "Nombre de banco: ${card?.bankName ?: "No tiene banco"}",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "Nombre: ${card?.userName ?: "No tiene nombre"}",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "Apellido: ${card?.userLastName ?: "No tiene apellido"}",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "Balance: ${state.cardBalance?.balance ?: "Hubo un problema en la consulta"}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }

                        Card(
                            modifier = Modifier.layoutId("composable2"),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            Image(
                                imageVector = MyIconPack.Icon,
                                contentDescription = "Background",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
private fun CardDetailsScreenPreview() {
    CardDetailsScreen(
        state = CardDetailsState(),
        card = null,
        onEvent = {},
        onExit = {}
    )
}
