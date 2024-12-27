package com.example.lullaby.registration.login_page

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lullaby.custom_ui.CustomAlertDialog
import com.example.lullaby.custom_ui.CustomTextField
import com.example.lullaby.custom_ui.Logo
import com.example.lullaby.navigation_data.MainScreenDataObject
import com.example.lullaby.ui.theme.BlurredWhite
import com.example.lullaby.ui.theme.GlobalBackground
import com.example.lullaby.ui.theme.SofiaPro
import com.example.lullaby.ui.theme.TitleYellow
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun LoginPage(
    onNavigateToMainPage: (MainScreenDataObject) -> Unit,
    onNavigateToSignUpPage: () -> Unit
) {

    val auth = remember {
        Firebase.auth
    }

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val openErrorDialog = remember {
        mutableStateOf(false)
    }

    val errorMessage = remember {
        mutableStateOf("")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(GlobalBackground)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            Logo()

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Dive into music with us",
                fontSize = 16.sp,
                fontFamily = SofiaPro,
                color = BlurredWhite
            )

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = SofiaPro
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                text = email.value,
                placeholder = "Email",
                onValueChange = {
                    email.value = it
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            CustomTextField(
                text = password.value,
                placeholder = "Password",
                onValueChange = {
                    password.value = it
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                onClick = {
                    // TODO
                },
            ) {
                Text(
                    text = "Forgot your password?",
                    color = BlurredWhite,
                    fontFamily = SofiaPro,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            if(openErrorDialog.value) {
                CustomAlertDialog(
                    openAlertDialog = openErrorDialog,
                    title = "Failed to Log In",
                    text = errorMessage.value,
                    buttonText = "Try Again",
                    onClick = {
                        openErrorDialog.value = false
                    }
                )
            }

            Button(
                onClick = {
                    logIn(
                        auth = auth,
                        email = email.value,
                        password = password.value,
                        onLoginSuccess = { navData ->
                            onNavigateToMainPage(navData)
                        },
                        onLoginFailure = { error ->
                            errorMessage.value = error
                            openErrorDialog.value = true
                        }
                    )
                },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = TitleYellow,
                    contentColor = GlobalBackground
                ),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(
                    text = "Login",
                    fontSize = 18.sp,
                    fontFamily = SofiaPro,
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 15.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "New here?",
                    fontSize = 16.sp,
                    fontFamily = SofiaPro,
                    color = Color.White
                )

                TextButton(
                    onClick = {
                        onNavigateToSignUpPage()
                    },
                ) {
                    Text(
                        text = "Create your account!",
                        fontSize = 16.sp,
                        fontFamily = SofiaPro,
                        textDecoration = TextDecoration.Underline,
                        color = Color.White
                    )
                }
            }
        }
    }
}

fun logIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onLoginSuccess: (MainScreenDataObject) -> Unit,
    onLoginFailure: (String) -> Unit
) {
    if(email.isBlank() || password.isBlank()) {
        onLoginFailure("Email and password can not be empty")
        return
    }

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {task ->
            if(task.isSuccessful) {
                onLoginSuccess(
                    MainScreenDataObject(
                        task.result.user?.uid!!,
                        task.result.user?.email!!
                    )
                )
            }
        }
        .addOnFailureListener {error ->
            onLoginFailure(error.message ?: "Failed to Log In")
        }
}
