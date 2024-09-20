package com.example.myapplication.modules.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.R

enum class MessageType(val animationRes: Int, val backgroundColor: Color) {
    SUCCESS(R.raw.finish, Color(0xFF4CAF50)),
    ALERT(R.raw.alert, Color(0xFFFFC107)),
    ERROR(R.raw.error, Red),
}

@Composable
fun CustomDialogMessage(
    modifier: Modifier = Modifier,
    message: String = "Mensaje de prueba",
    messageType: MessageType = MessageType.ERROR,
    onDismiss: () -> Unit = {},
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(messageType.animationRes))
    val progress by animateLottieCompositionAsState(composition)

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(
                modifier = modifier
                    .width(300.dp)
                    .wrapContentSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.size(200.dp)
                    )

                    if (messageType != MessageType.SUCCESS) {
                        Text(
                            text = "¡ Hubo un error !",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Text(
                        text = message ?: "Vuelve a intentarlo",
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        fontSize = 16.sp,
                    )

                    CustomOutlinedButton(
                        label = "OK",
                        containerColor = messageType.backgroundColor,
                        onClick = onDismiss
                    )
                }
            }
        }
    }
}
