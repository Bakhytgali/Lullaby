package com.example.lullaby.custom_ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import com.example.lullaby.ui.theme.BlurredContainer
import com.example.lullaby.ui.theme.BlurredWhite
import com.example.lullaby.ui.theme.SofiaPro

@Composable
fun CustomTextField(
    text: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    maxLines: Int = 1
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = BlurredWhite,
                fontFamily = SofiaPro
            )
        },
        value = text,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = SofiaPro,
            color = Color.White
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BlurredContainer,
            focusedContainerColor = BlurredContainer,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        shape = RectangleShape,
        singleLine = singleLine,
        maxLines = maxLines
    )
}
