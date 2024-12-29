package com.example.lullaby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lullaby.navigation_data.AccountPageDataObject
import com.example.lullaby.navigation_data.AdminScreenObject
import com.example.lullaby.navigation_data.AlbumPageDataObject
import com.example.lullaby.navigation_data.LogInScreenObject
import com.example.lullaby.navigation_data.MainScreenDataObject
import com.example.lullaby.navigation_data.SignUpScreenObject
import com.example.lullaby.registration.login_page.LoginPage
import com.example.lullaby.registration.login_page.SignUpPage
import com.example.lullaby.ui.theme.LullabyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LullabyTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = AdminScreenObject
                ) {

                    composable<AdminScreenObject> {
                        AdminPage()
                    }

                    composable<LogInScreenObject> {
                        LoginPage(
                            onNavigateToMainPage = {navData ->
                                navController.navigate(navData)
                            },
                            onNavigateToSignUpPage = {
                                navController.navigate(SignUpScreenObject)
                            }
                        )
                    }

                    composable<SignUpScreenObject> {
                        SignUpPage (
                            onNavigateToLoginPage = {
                                navController.navigate(LogInScreenObject)
                            }
                        )
                    }

                    composable<MainScreenDataObject> { navEntry ->
                        val navData = navEntry.toRoute<MainScreenDataObject>()
                        MainPage(
                            navData,
                            onNavigateToAccount = { navAccountData ->
                                navController.navigate(navAccountData)
                            },
//                            onNavigateToAnAlbum = { navAlbumData ->
//                                navController.navigate(navAlbumData)
//                            }
                        )
                    }

                    composable<AccountPageDataObject> { navEntry ->
                        val navData = navEntry.toRoute<AccountPageDataObject>()
                        AccountPage(
                            navData,
                            onNavigateToMain = { navMainData ->
                                navController.navigate(navMainData)
                            }
                        )
                    }
                    
                }
            }
        }
    }
}