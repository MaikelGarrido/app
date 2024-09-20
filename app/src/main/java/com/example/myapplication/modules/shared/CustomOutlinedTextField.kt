package com.example.myapplication.modules.shared

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.modules.theme.LightGray
import com.example.myapplication.modules.theme.LimeGreen
import com.example.myapplication.modules.theme.VeryDarkGray

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    label: String? = null,
    keyboardType: KeyboardType,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = keyboardType,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: RoundedCornerShape = RoundedCornerShape(25.dp),
    onTextChanged: (String) -> Unit,
) {
    val textFieldColor = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = LightGray,
        focusedBorderColor = VeryDarkGray,
        unfocusedBorderColor = LightGray
    )

    OutlinedTextField(
        value = value,
        onValueChange = { onTextChanged(it) },
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholder,
                color = VeryDarkGray,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        readOnly = readOnly,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = textFieldColor,
        label = {
            if (label != null) {
                Text(
                    text = label,
                    color = VeryDarkGray,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
        shape = shape
    )
}

@Preview(backgroundColor = 0x00FFFFFF, showBackground = true)
@Composable
private fun CustomOutlinedTextFieldPreview() {
    CustomOutlinedTextField(
        value = "",
        placeholder = "Ingresa un texto",
        label = "Ingresa un texto",
        keyboardType = KeyboardType.Text,
        onTextChanged = { }
    )
}