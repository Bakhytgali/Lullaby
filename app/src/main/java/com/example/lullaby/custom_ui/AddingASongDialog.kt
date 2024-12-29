package com.example.lullaby.custom_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lullaby.data.Song
import com.example.lullaby.ui.theme.BlurredWhite
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow

@Composable
fun AddingASongDialog(
    openTheDialog: MutableState<Boolean>,
    onAddSong: (Song) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        containerColor = Color.Black,
        onDismissRequest = {
            openTheDialog.value = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openTheDialog.value = false
                }
            ) {
                Text(
                    text = "Add",
                    fontFamily = SofiaPro,
                    color = TitleYellow
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openTheDialog.value = false
                }
            ) {
                Text(
                    text = "Cancel",
                    fontFamily = SofiaPro,
                    color = Color.White.copy(0.8f)
                )
            }
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add a song",
                    fontFamily = SofiaPro,
                    fontWeight = FontWeight.Bold,
                    color = TitleYellow
                )
            }
        },
        text = {
            Column {
                CustomTextField(
                    text = "Name",
                    placeholder = "Name",
                    onValueChange = {
                        
                    },
                )
                Spacer(modifier = Modifier.height(10.dp))

                CustomTextField(
                    text = "Features",
                    placeholder = "Features",
                    onValueChange = {
                        
                    },
                )
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Separate features by a semicolon",
                    fontFamily = SofiaPro,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    )
}