package com.example.educa.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.educa.components.RegisterComponent
import com.example.educa.database.repository.UserRepository
import com.example.educa.model.User
import com.example.educa.ui.theme.Primary

@SuppressLint("UnrememberedMutableState")
@Composable
fun RegisterEmailScreen(navController: NavHostController) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }
    var dtNasc by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }
    var accountType by remember {
        mutableIntStateOf(0)
    }

    var distance by remember {
        mutableIntStateOf(0)
    }

    var tempInterestList = listOf<Int>()

    var userInterestChecked by remember {
        mutableStateOf(tempInterestList)
    }

    var userAcademicEducationChecked by remember {
        mutableStateOf(tempInterestList)
    }
    var userSkillsChecked by remember {
        mutableStateOf(tempInterestList)
    }
    var userExperiencesChecked by remember {
        mutableStateOf(tempInterestList)
    }

    var userPhoto by remember {
        mutableStateOf("")
    }


    var controllerPage by remember {
        mutableStateOf("email")
    }

    var currentProgress by remember { mutableFloatStateOf(0f) }

    val context = LocalContext.current

    val userRepository = UserRepository(context)

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
                            controllerPage = "password"
                        },
                        step = controllerPage
                    )
                }

                "password" -> {
                    var passwordVisible by remember { mutableStateOf(false) }

                    RegisterComponent(
                        titleText = "Digite uma senha",
                        descriptionText = "Por favor, forneça uma senha para poder se autenticar.",
                        placeholder = "Digite a sua senha",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                        },
                        inputValue = password,
                        updateValue = {
                            password = it
                        },
                        backStep = {
                            currentProgress -= 0F
                            controllerPage = "email"
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
                            controllerPage = "password"
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
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "dtNasc"
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = "accountType"
                        },
                        step = controllerPage,
                        updateGender = {
                            gender = it
                        }
                    )
                }

                "accountType" -> {
                    RegisterComponent(
                        titleText = "O que você deseja ser?",
                        descriptionText = "Selecione a forma que podemos te indicar",
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "gender"
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = "distance"
                        },
                        step = controllerPage,
                        updateAccountType = {
                            accountType = it
                        }
                    )
                }

                "distance" -> {
                    RegisterComponent(
                        titleText = "Distância maxima",
                        descriptionText = "Use o controle deslizante para definir a distância máxima",
                        updateDistance = {
                            distance = it
                        },
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "accountType"
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = "aboutYouProfessional"
                        },
                        step = controllerPage,
                    )
                }

                "aboutYouProfessional" -> {
                    RegisterComponent(
                        titleText = "Sobre você",
                        descriptionText = "Para encontrarmos matches mais precisos, selecione interesses que representem você.",
                        updateInterestCheckedList = {
                            userInterestChecked = it
                        },
                        updateAcademicEducationCheckedList = {
                            userAcademicEducationChecked = it
                        },
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "distance"
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = "aboutYouPersonal"
                        },
                        step = controllerPage
                    )
                }

                "aboutYouPersonal" -> {
                    RegisterComponent(
                        titleText = "Sobre você",
                        descriptionText = "Fale um pouco mais sobre você",
                        updateSkillsCheckedList = {
                            userSkillsChecked = it
                        },
                        updateExperiencesCheckedList = {
                            userExperiencesChecked = it
                        },
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "aboutYouProfessional"
                        },
                        nextStep = {
                            currentProgress += 10F
                            controllerPage = "userPhoto"
                        },
                        step = controllerPage
                    )
                }

                "userPhoto" -> {
                    RegisterComponent(
                        titleText = "Adicione sua foto",
                        descriptionText = "Carregue 1 foto para começar",
                        updateValue = {
                            userPhoto = it
                        },
                        createUser = {
                            val user = User(
                                id = 0,
                                name = name,
                                email = email,
                                password = password,
                                dtNasc = dtNasc,
                                distance = distance,
                                gender = gender,
                                accountType = accountType,
                                interest = userInterestChecked,
                                academicEducation = userAcademicEducationChecked,
                                skills = userSkillsChecked,
                                experiences = userExperiencesChecked,
                                userPhoto = userPhoto,
                            )

                            var isCreated = userRepository.create(user)

                            if (isCreated.toString().isNotEmpty()) navController.navigate("login")
                        },
                        backStep = {
                            currentProgress -= 10F
                            controllerPage = "aboutYouPersonal"
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