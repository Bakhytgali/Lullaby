package com.example.lullaby

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lullaby.custom_ui.Logo
import com.example.lullaby.navigation_data.AccountPageDataObject
import com.example.lullaby.navigation_data.AlbumPageDataObject
import com.example.lullaby.navigation_data.MainScreenDataObject
import com.example.lullaby.ui.theme.AmazingAlbumPurple
import com.example.lullaby.ui.theme.BlurredWhite
import com.example.lullaby.ui.theme.GlobalBackground
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumPage(
//    onNavigateToMain: (MainScreenDataObject) -> Unit,
//    onNavigateToAccount: (AccountPageDataObject) -> Unit,
    navData: AlbumPageDataObject
) {
    val album = navData.album

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
//                                    onNavigateToMain(
//                                        MainScreenDataObject(
//                                            userID = navData.userID,
//                                            email = navData.email
//                                        )
//                                    )
                                    // TODO
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
//                                    onNavigateToAccount(
//                                        AccountPageDataObject(
//                                            userID = navData.userID,
//                                            email = navData.email
//                                        )
//                                    )

                                    // TODO
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
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(GlobalBackground),
            contentAlignment = Alignment.TopCenter
        ) {


            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top,
                ) {
                    Image(
                        painter = painterResource(R.drawable.rodeo_cover),
                        contentDescription = "Nothing",
                        modifier = Modifier.size(128.dp)
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = album.title!!,
                            color = Color.White,
                            fontFamily = SofiaPro,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )

                        Text(
                            text = album.artist!!.name!!,
                            color = BlurredWhite,
                            fontFamily = SofiaPro,
                            fontSize = 16.sp
                        )

                        Text(
                            text = album.category + ", " + album.releaseYear.toString(),
                            color = BlurredWhite,
                            fontFamily = SofiaPro,
                            fontSize = 12.sp
                        )

                        Text(
                            text = "9.1 / 10",
                            fontSize = 18.sp,
                            fontFamily = SofiaPro,
                            modifier = Modifier
                                .background(AmazingAlbumPurple)
                                .padding(vertical = 4.dp, horizontal = 7.dp)

                        )
                    }
                }
            }
        }
    }
}