package com.example.educa.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.educa.components.RegisterComponent
import com.example.educa.ui.theme.Primary

@SuppressLint("UnrememberedMutableState")
@Composable
fun RegisterEmailScreen(navController: NavHostController) {

    var email by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var dtNasc by remember {
        mutableStateOf("")
    }
    val gender by remember {
        mutableStateOf("")
    }

    var controllerPage by remember {
        mutableStateOf("email")
    }

    var currentProgress by remember { mutableFloatStateOf(0f) }

    Column {
        LinearProgressIndicator(
            progress = currentProgress / 90,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp),
            color = Primary,
            strokeCap = StrokeCap.Round
        )

        Column {
            when (controllerPage) {
                "email" -> {
                    RegisterComponent(
                        titleText = "Qual é seu e-mail?",
                        descriptionText = "Por favor, forneça o seu e-mail para continuar.",
                        placeholder = "Digite o seu e-mail",
                        keyboardType = KeyboardType.Email,
                        inputValue = email,
                        updateValue = {
                            email = it
                        },
                        backStep = {
                            currentProgress -= 0F
                            navController.navigate("welcome")
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = "name"
                        },
                        step = controllerPage
                    )
                }
                "name" -> {
                    RegisterComponent(
                        titleText = "Qual é seu nome?",
                        descriptionText = "Este será o nome que será exibido para os outros usuários.",
                        placeholder = "Digite o seu nome",
                        keyboardType = KeyboardType.Text,
                        inputValue = name,
                        updateValue = {
                            name = it
                        },
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "email"
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = "dtNasc"
                        },
                        step = controllerPage
                    )
                }
                "dtNasc" -> {
                    RegisterComponent(
                        titleText = "Data de nascimento?",
                        descriptionText = "Seu perfil mostra sua idade, não data de nascimento",
                        inputValue = dtNasc,
                        updateValue = {
                            dtNasc = it
                        },
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "name"
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = "gender"
                        },
                        step = controllerPage
                    )
                }
                "gender" -> {
                    RegisterComponent(
                        titleText = "Qual é o seu gênero?",
                        descriptionText = "Selecione a opção que melhor te descreve",
                        inputValue = gender,
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "dtNasc"
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = ""
                        },
                        step = controllerPage
                    )
                }
                else -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = "Em desenvolvimento...", fontWeight = FontWeight.Bold)
                    }
                }
            }

        }
    }
}