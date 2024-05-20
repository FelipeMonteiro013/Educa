package com.example.educa.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.educa.database.repository.UserRepository
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary
import com.example.educa.ui.theme.TextColor


@Composable
fun CheckDb() {
    val context = LocalContext.current
    val userRepository = UserRepository(context)
    userRepository.checkDb()
}


@Composable
fun LoginScreen(navController: NavController) {

    val contex = LocalContext.current
    val userRepository = UserRepository(context = contex)

    CheckDb()

    var emailField by remember {
        mutableStateOf("fulano@email.com")
    }

    var passwordField by remember {
        mutableStateOf("123")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Row {
            Text(
                text = "Educa",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Primary
            )
            Text(text = "+", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Secondary)
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        OutlinedTextField(
            value = emailField,
            singleLine = true,
            placeholder = { Text(text = "Digite o seu e-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                emailField = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        OutlinedTextField(
            value = passwordField,
            singleLine = true,
            placeholder = { Text(text = "Digite a sua senha") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                passwordField = it
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    if (passwordVisible) {
                        Icon(
                            imageVector = Icons.Outlined.VisibilityOff,
                            contentDescription = "Tornar senha visivel"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Visibility,
                            contentDescription = "Tornar senha visivel"
                        )
                    }

                }
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Button(
            onClick = {
                try {
                    val loggedUserId = userRepository.login(emailField, passwordField)

                    if (loggedUserId > 0) {
                        navController.navigate("home/${loggedUserId}")
                    }

                } catch (e: Exception) {
                    Log.e("TESTE", e.message.toString())
                }


            },
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Entrar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Divider()
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = "Ainda não possui um cadastro ?",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextColor
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = "Cadastrar",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Primary,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                navController.navigate("welcome")
            }
        )

    }
}
