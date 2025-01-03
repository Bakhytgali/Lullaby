package com.example.lullaby

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.lullaby.custom_ui.AddingASongDialog
import com.example.lullaby.custom_ui.CustomTextField
import com.example.lullaby.custom_ui.Logo
import com.example.lullaby.custom_ui.SongCard
import com.example.lullaby.data.AlbumModel
import com.example.lullaby.data.Artist
import com.example.lullaby.data.Song
import com.example.lullaby.ui.theme.BlurredContainer
import com.example.lullaby.ui.theme.BlurredWhite
import com.example.lullaby.ui.theme.GlobalBackground
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPage(modifier: Modifier = Modifier) {
    val fs = Firebase.firestore

    // Album Info
    val albumName = remember { mutableStateOf("") }
    val artist = remember { mutableStateOf("") }
    val releaseYear = remember { mutableStateOf("") }
    val albumCategory = remember { mutableStateOf("") }
    val listOfSongs = remember { mutableStateOf(arrayListOf<Song>()) }
    val openTheDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }

    // Choosing Album Cover
    val selectedAlbumCover = remember {
        mutableStateOf<Uri?>(null)
    }
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedAlbumCover.value = uri
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
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
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                if (openTheDialog.value) {
                    AddingASongDialog(
                        openTheDialog = openTheDialog,
                        onAddSong = { song ->
                            val newSong = Song(
                                title = song.title,
                                artist = artist.value,
                                featuringArtists = song.featuringArtists,
                                albumName = albumName.value
                            )
                            listOfSongs.value = ArrayList(listOfSongs.value).apply { add(newSong) }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "This is an Admin Page\nYou are adding an Album",
                    fontFamily = SofiaPro,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(2.dp, color = TitleYellow)
                    ) {
                        if(selectedAlbumCover.value == null) {
                            Image(
                                painter = painterResource(R.drawable.frame_1),
                                contentDescription = "Placeholder Cover",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            )
                        } else {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = selectedAlbumCover.value,
                                ),
                                contentDescription = "Chosen Album Cover",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    TextButton(
                        onClick = {
                            imageLauncher.launch("image/*")
                        }
                    ) {
                        Text(
                            text = "Add album cover",
                            fontFamily = SofiaPro,
                            fontSize = 14.sp,
                            color = Color.White.copy(0.8f),
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "General Info",
                    fontFamily = SofiaPro,
                    fontSize = 20.sp,
                    color = BlurredWhite,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Text fields for album info
                CustomTextField(
                    text = albumName.value,
                    placeholder = "Album Name",
                    onValueChange = { albumName.value = it }
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    text = artist.value,
                    placeholder = "Artist",
                    onValueChange = { artist.value = it }
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    text = releaseYear.value,
                    placeholder = "Album Release Year",
                    onValueChange = { releaseYear.value = it }
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    text = albumCategory.value,
                    placeholder = "Album Category",
                    onValueChange = { albumCategory.value = it }
                )

                if (errorMessage.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = errorMessage.value,
                        fontFamily = SofiaPro,
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
                SongListSection(
                    listOfSongs = listOfSongs,
                    onAddSongClicked = {
                        if (albumName.value.isNotEmpty() &&
                            artist.value.isNotEmpty() &&
                            releaseYear.value.isNotEmpty() &&
                            albumCategory.value.isNotEmpty()
                        ) {
                            openTheDialog.value = true
                            errorMessage.value = ""
                        } else {
                            errorMessage.value = "Make sure to fill all the fields."
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Add album button
                if(listOfSongs.value.isNotEmpty()) {
                    Button(
                        onClick = {
                            val newArtist = Artist(name = artist.value)
                            val newAlbum = AlbumModel(
                                title = albumName.value,
                                artist = newArtist,
                                releaseYear = releaseYear.value.toInt(),
                                imgUrl = null,
                                category = albumCategory.value,
                                listOfSongs = listOfSongs.value
                            )
                            Log.d("My Log", "$newAlbum")
                            addAlbum(album = newAlbum, fs = fs)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = TitleYellow)
                    ) {
                        Text(
                            text = "Add the album",
                            fontFamily = SofiaPro,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = GlobalBackground
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}

@Composable
fun SongListSection(
    listOfSongs: MutableState<ArrayList<Song>>,
    onAddSongClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Songs",
                fontFamily = SofiaPro,
                fontSize = 20.sp,
                color = Color.White.copy(0.8f),
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Press the button to add a song",
                fontFamily = SofiaPro,
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
                onClick = onAddSongClicked,
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

    Spacer(modifier = Modifier.height(20.dp))

    if (listOfSongs.value.isNotEmpty()) {
        LazyColumn(modifier = Modifier.height(200.dp)) {
            itemsIndexed(listOfSongs.value) { index, song ->
                SongCard(index = index + 1, song = song)
            }
        }
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


fun addAlbum(
    album: AlbumModel,
    fs: FirebaseFirestore
) {
    fs.collection("albums").add(album)
        .addOnSuccessListener {
            Log.d("My Tag", "ALBUM ADDED!")
        }
        .addOnFailureListener { error ->
            Log.d("My Tag", "Oops! ${error.message}")
        }
}