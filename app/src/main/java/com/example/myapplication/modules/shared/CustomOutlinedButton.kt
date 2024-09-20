package com.example.myapplication.modules.shared

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.modules.theme.LimeGreen
import com.example.myapplication.modules.theme.PaleMint
import com.example.myapplication.modules.theme.VeryDarkGray

@Composable
fun CustomOutlinedButton(
    modifier: Modifier = Modifier,
    label: String? = null,
    icon: ImageVector? = null,
    colorText: Color = Color.White,
    containerColor: Color? = null,
    disabledContentColor: Color = Color.LightGray,
    disabledContainerColor: Color = Color.LightGray.copy(alpha = 0.4f),
    shape: CornerBasedShape = ShapeDefaults.ExtraLarge,
    border : BorderStroke = BorderStroke(0.dp, Color.Transparent),
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val animatedColor by animateColorAsState(
        if (isEnabled) colorText else Color.Gray.copy(alpha = 0.4f),
        animationSpec = tween(1000, 0, FastOutSlowInEasing),
        label = "color"
    )

    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = PaleMint,
            containerColor = containerColor ?: VeryDarkGray,
            disabledContentColor = disabledContentColor,
            disabledContainerColor = disabledContainerColor,
        ),
        shape = shape,
        border = border
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = "Icon button")
            }
            if (icon != null && label != null) {
                Spacer(modifier = Modifier.width(5.dp))
            }
            if (label != null) {
                Text(
                    text = label,
                    color = animatedColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    CustomOutlinedButton(
        label = "Enviar",
        icon = Icons.Default.Person,
        isEnabled = false
    )
}