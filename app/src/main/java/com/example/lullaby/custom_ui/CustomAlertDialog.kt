package com.example.lullaby.custom_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.lullaby.ui.theme.AlertRed
import com.example.lullaby.ui.theme.GlobalBackground
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow

@Composable
fun CustomAlertDialog(
    openAlertDialog: MutableState<Boolean>,
    title: String,
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    when {
        openAlertDialog.value -> {
            AlertDialog(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            fontFamily = SofiaPro,
                            fontSize = 24.sp,
                            color = AlertRed
                        )
                    }
                },
                text = {
                    Text(
                        text = text,
                        fontFamily = SofiaPro,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onClick()
                        }
                    ) {
                        Text(
                            text = buttonText,
                            fontFamily = SofiaPro,
                            color = TitleYellow
                        )
                    }
                },
                onDismissRequest = {
                    openAlertDialog.value = false
                },
                containerColor = GlobalBackground
            )
        }
    }
}