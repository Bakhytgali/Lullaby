package com.example.lullaby

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.sp
import com.example.lullaby.custom_ui.AddingASongDialog
import com.example.lullaby.custom_ui.CustomTextField
import com.example.lullaby.custom_ui.Logo
import com.example.lullaby.custom_ui.SongCard
import com.example.lullaby.data.Song
import com.example.lullaby.ui.theme.BlurredContainer
import com.example.lullaby.ui.theme.BlurredWhite
import com.example.lullaby.ui.theme.GlobalBackground
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AdminPage(modifier: Modifier = Modifier) {
    val albumName = remember {
        mutableStateOf("")
    }
    val artist = remember {
        mutableStateOf("")
    }
    val releaseYear = remember {
        mutableStateOf("")
    }
    val albumCategory = remember {
        mutableStateOf("")
    }
    
    val listOfSongs = remember {
        mutableStateOf(
            arrayListOf<Song>()
        )
    }

    val openTheDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Logo(fontSize = 24)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                ),
            )
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(GlobalBackground)
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight()
            ) {
                if(openTheDialog.value) {
                    AddingASongDialog(
                        openTheDialog = openTheDialog,
                        onAddSong = { song ->
                            val newList: ArrayList<Song> = listOfSongs.value
                            newList.add(song)

                            listOfSongs.value = newList
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "This is an Admin Page\n " +
                            "You are adding an Album",
                    fontFamily = SofiaPro,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "General Info",
                    fontFamily = SofiaPro,
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp,
                    color = BlurredWhite,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    text = albumName.value,
                    placeholder = "Album Name",
                    onValueChange = {
                        albumName.value = it
                    },
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    text = artist.value,
                    placeholder = "Artist",
                    onValueChange = {
                        artist.value = it
                    },
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    text = releaseYear.value,
                    placeholder = "Album Release Year",
                    onValueChange = {
                        releaseYear.value = it
                    },
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    text = albumCategory.value,
                    placeholder = "Album Category",
                    onValueChange = {
                        albumCategory.value = it
                    },
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Songs",
                            fontFamily = SofiaPro,
                            textAlign = TextAlign.Left,
                            fontSize = 20.sp,
                            color = BlurredWhite,
                            fontWeight = FontWeight.Medium,
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Press the button to add a song",
                            fontFamily = SofiaPro,
                            textAlign = TextAlign.Left,
                            fontSize = 14.sp,
                            color = BlurredWhite,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = TitleYellow,
                        modifier = Modifier.size(50.dp)
                    ) {
                        IconButton(
                            onClick = {
                                openTheDialog.value = true
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = GlobalBackground
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add a song button"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                if(listOfSongs.value.isNotEmpty()) {
                    ColumnOfSongs(
                        songs = listOfSongs
                    )
                } else {
                    Text(
                        text = "No songs added",
                        fontFamily = SofiaPro,
                        fontWeight = FontWeight.Bold,
                        color = BlurredContainer,
                        fontSize = 28.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ColumnOfSongs(
    songs: MutableState<ArrayList<Song>>,
    modifier: Modifier = Modifier
) {
    LazyColumn() {
        items(songs.value) {song ->
            SongCard(
                song = song
            )
        }
    }
}