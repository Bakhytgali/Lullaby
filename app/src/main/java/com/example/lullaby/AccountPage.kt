package com.example.lullaby

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lullaby.custom_ui.Logo
import com.example.lullaby.data.AlbumModel
import com.example.lullaby.data.Artist
import com.example.lullaby.data.Song
import com.example.lullaby.navigation_data.AccountPageDataObject
import com.example.lullaby.navigation_data.MainScreenDataObject
import com.example.lullaby.ui.theme.BlurredWhite
import com.example.lullaby.ui.theme.GlobalBackground
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountPage(
    navData: AccountPageDataObject,
    onNavigateToMain: (MainScreenDataObject) -> Unit,
    modifier: Modifier = Modifier
) {
    val fs = Firebase.firestore

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
                                    onNavigateToMain(
                                        MainScreenDataObject(
                                            navData.userID,
                                            navData.email
                                        )
                                    )
                                }
                            ) {
                                Icon(
                                    Icons.Default.Home,
                                    contentDescription = "Home Icon",
                                    tint = TitleYellow.copy(alpha = 0.6f),
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Text(
                                text = "Home",
                                fontSize = 12.sp,
                                fontFamily = SofiaPro,
                                color = TitleYellow.copy(alpha = 0.6f)
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {

                                }
                            ) {
                                Icon(
                                    Icons.Default.AccountCircle,
                                    contentDescription = "Account Icon",
                                    tint = TitleYellow,
                                    modifier = Modifier.size(34.dp)
                                )
                            }
                            Text(
                                text = "Account",
                                fontSize = 12.sp,
                                fontFamily = SofiaPro,
                                color = TitleYellow
                            )
                        }
                    }
                },
                containerColor = Color.Black
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(GlobalBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.rodeo_cover),
                contentDescription = "Travis Scott Rodeo",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(128.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row {
                Text(
                    text = "Email: ",
                    fontFamily = SofiaPro,
                    fontWeight = FontWeight.Bold,
                    color = BlurredWhite,
                    fontSize = 18.sp
                )
                Text(
                    text = navData.email,
                    fontFamily = SofiaPro,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "User",
                fontFamily = SofiaPro,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(25.dp))


        }
    }
}

