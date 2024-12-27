package com.example.lullaby.custom_ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow

@Composable
fun Logo(modifier: Modifier = Modifier, fontSize: Int = 32) {
    Text(
       text = "Lullaby",
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        color = TitleYellow,
        fontFamily = SofiaPro
    )
}