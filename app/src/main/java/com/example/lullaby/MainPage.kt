package com.example.lullaby

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lullaby.custom_ui.AlbumCard
import com.example.lullaby.custom_ui.CustomTextField
import com.example.lullaby.custom_ui.Logo
import com.example.lullaby.data.AlbumModel
import com.example.lullaby.navigation_data.AccountPageDataObject
import com.example.lullaby.navigation_data.AlbumPageDataObject
import com.example.lullaby.navigation_data.MainScreenDataObject
import com.example.lullaby.ui.theme.GlobalBackground
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    navData: MainScreenDataObject,
    onNavigateToAccount: (AccountPageDataObject) -> Unit,
//    onNavigateToAnAlbum: (AlbumPageDataObject) -> Unit,
    modifier: Modifier = Modifier
) {
    val fs = Firebase.firestore

    val isAdmin = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        checkIsAdmin {
            isAdmin.value = it
        }
    }

    val search = remember {
        mutableStateOf("")
    }

    val searchResult = remember {
        mutableStateOf(ArrayList<AlbumModel>())
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
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {


                                }
                            ) {
                                Icon(
                                    Icons.Default.Home,
                                    contentDescription = "Home Icon",
                                    tint = TitleYellow,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Text(
                                text = "Home",
                                fontSize = 12.sp,
                                fontFamily = SofiaPro,
                                color = TitleYellow
                            )

                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {
                                    onNavigateToAccount(
                                        AccountPageDataObject(
                                            navData.userID,
                                            navData.email
                                        )
                                    )
                                }
                            ) {
                                Icon(
                                    Icons.Default.AccountCircle,
                                    contentDescription = "Account Icon",
                                    tint = TitleYellow.copy(alpha = 0.6f),
                                    modifier = Modifier.size(34.dp)
                                )
                            }
                            Text(
                                text = "Account",
                                fontSize = 12.sp,
                                fontFamily = SofiaPro,
                                color = TitleYellow.copy(alpha = 0.6f)
                            )
                        }
                    }
                },
                containerColor = Color.Black
            )
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(GlobalBackground)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    CustomTextField(
                        placeholder = "Search",
                        text = search.value,
                        onValueChange = {
                            search.value = it
                        },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    IconButton(
                        onClick = {
                            if (search.value != "") {
                                fs.collection("albums")
                                    .whereEqualTo("title", search.value)
                                    .get()
                                    .addOnSuccessListener { documents ->
                                        for(document in documents) {
                                            val album = document.toObject(AlbumModel::class.java)
                                            val newArrayList = ArrayList<AlbumModel>()
                                            newArrayList.add(album)
                                            searchResult.value = newArrayList
                                        }
                                    }
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = TitleYellow
                        )

                        if(isAdmin.value) {
                            Text("ADMIN STATE IS REAL!")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                if (searchResult.value.isNotEmpty()) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(searchResult.value.size) { index ->
                            val item = searchResult.value[index]
                            AlbumCard(
                                album = item,
                                onClick = {
//                                    onNavigateToAnAlbum(
//                                        AlbumPageDataObject(
//                                            userID = navData.userID,
//                                            email = navData.email,
//                                            album = item
//                                        )
//                                    )

                                    //TODO
                                }
                            )
                            if(index != searchResult.value.size) {
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.8f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Search for songs, albums or artists!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SofiaPro,
                            color = TitleYellow,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

fun checkIsAdmin(isAdmin: (Boolean) -> Unit) {
    val uid = Firebase.auth.currentUser?.uid ?: "unknown uid"
    Firebase.firestore.collection("admins")
        .document(uid).get().addOnSuccessListener { task ->
            isAdmin(task.get("isAdmin") as Boolean)
        }
}
