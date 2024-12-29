package com.example.lullaby.custom_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lullaby.data.Artist
import com.example.lullaby.data.Song
import com.example.lullaby.ui.theme.BlurredContainer
import com.example.lullaby.ui.theme.BlurredWhite
import com.example.lullaby.ui.theme.SofiaPro

@Composable
fun SongCard(
    index: Int,
    song: Song,
    modifier: Modifier = Modifier
) {
    val listOfFeatures: String = createFeatures(song.featuringArtists)
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = index.toString(),
            fontSize = 18.sp,
            fontFamily = SofiaPro,
            color = Color.White.copy(0.85f)
        )

        Spacer(modifier = Modifier.width(15.dp))
        
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = song.title.toString(),
                fontFamily = SofiaPro,
                fontSize = 18.sp,
                color = Color.White.copy(0.85f),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "${song.artist}$listOfFeatures",
                fontFamily = SofiaPro,
                fontSize = 16.sp,
                color = BlurredWhite
            )
        }
    }
}

private fun createFeatures(features: ArrayList<Artist>?): String{
    var featureArtists: String = ""

    if (features != null) {
        for(i in features.indices) {
            if(features[i].name == "") {
                continue
            }
            else {
                featureArtists += ", ${features[i].name}"
            }
        }
    } else {
        return "Failed to join the features."
    }

    return featureArtists
}

@Preview(showBackground = true)
@Composable
private fun SongPreview() {
    val song = Song(
        "Maria I'm Drunk",
        "Travis Scott",
        arrayListOf(Artist("Justin Bieber"), Artist("Young Thug")),
        "Rodeo"
    )
    SongCard(1, song)
}