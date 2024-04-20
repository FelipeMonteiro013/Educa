package com.example.educa.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RegisterComponent(
    titleText: String,
    descriptionText: String,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    inputValue: String = "",
    updateValue: (String) -> Unit = {},
    backStep: () -> Unit,
    nextStep: () -> Unit,
    step: String,
    updateGender: (Int) -> Unit = {},
    updateAccountType: (Int) -> Unit = {},
    updateDistance: (Int) -> Unit = {}
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight().verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.imePadding()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp)
            ) {
                IconButton(onClick = backStep) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "voltar para tela anterior",
                        tint = Secondary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }


            Column {
                TitleText(
                    text = titleText,
                    color = Primary,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = descriptionText,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                when (step) {
                    "dtNasc" -> {
                        val datePickerState =
                            rememberDatePickerState(
                                initialSelectedDateMillis = 1578096000000,
                                initialDisplayMode = DisplayMode.Picker
                            )
                        DatePicker(
                            state = datePickerState,
                            modifier = Modifier.padding(horizontal = 16.dp),

                            )
                    }

                    "gender" -> {
                        var genderSelected by remember {
                            mutableIntStateOf(-1)
                        }

                        when (genderSelected) {
                            0 -> {
                                GenderComponent(
                                    text = "Homem",
                                    isSelected = true,
                                    onClick = {
                                        genderSelected = 0
                                        updateGender(0)
                                    })
                                GenderComponent(
                                    text = "Mulher",
                                    isSelected = false,
                                    onClick = {
                                        updateGender(1)
                                        genderSelected = 1
                                    })
                                GenderComponent(
                                    text = "Além de binário",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 2
                                        updateGender(2)
                                    })
                            }

                            1 -> {

                                GenderComponent(
                                    text = "Homem",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 0
                                        updateGender(0)
                                    })
                                GenderComponent(
                                    text = "Mulher",
                                    isSelected = true,
                                    onClick = {
                                        updateGender(1)
                                        genderSelected = 1
                                    })
                                GenderComponent(
                                    text = "Além de binário",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 2
                                        updateGender(2)
                                    })
                            }

                            2 -> {
                                GenderComponent(
                                    text = "Homem",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 0
                                        updateGender(0)
                                    })
                                GenderComponent(
                                    text = "Mulher",
                                    isSelected = false,
                                    onClick = {
                                        updateGender(1)
                                        genderSelected = 1
                                    })
                                GenderComponent(
                                    text = "Além de binário",
                                    isSelected = true,
                                    onClick = {
                                        genderSelected = 2
                                        updateGender(2)
                                    })
                            }

                            else -> {
                                GenderComponent(
                                    text = "Homem",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 0
                                        updateGender(0)
                                    })
                                GenderComponent(
                                    text = "Mulher",
                                    isSelected = false,
                                    onClick = {
                                        updateGender(1)
                                        genderSelected = 1
                                    })
                                GenderComponent(
                                    text = "Além de binário",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 2
                                        updateGender(2)
                                    })
                            }
                        }
                    }

                    "accountType" -> {
                        var accountType by remember {
                            mutableIntStateOf(-1)
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AccountTypeComponent(
                                text = "Aluno",
                                icon = Icons.Outlined.Groups,
                                isSelected = accountType == 0, onClick = {
                                    accountType = 0
                                    updateAccountType(accountType)
                                }
                            )
                            AccountTypeComponent(
                                text = "Professor",
                                icon = Icons.Outlined.School,
                                isSelected = accountType == 1
                            ) {
                                accountType = 1
                                updateAccountType(accountType)
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp)
                        ) {
                            if (accountType == 0)
                                Text(text = "Me indique como aluno")
                            if (accountType == 1) Text(text = "Me indique como professor")
                        }
                    }

                    "distance" -> {
                        var sliderPosition by remember { mutableFloatStateOf(1f) }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp)
                        ) {
                            Text(text = "Preferência de distância")
                            Text(text = sliderPosition.toInt().toString() + "km")
                        }
                        Slider(
                            value = sliderPosition,
                            onValueChange = {
                                sliderPosition = it
                                updateDistance
                            },
                            valueRange = 1f..80f,
                            modifier = Modifier.padding(horizontal = 30.dp)
                        )

                    }

                    "aboutYouProfessional" -> {
                        val listAreaOfInterest = arrayOf(
                            "Técnologia",
                            "Inovação",
                            "Negócios",
                            "Artes",
                            "Cultura",
                            "Ciências",
                            "Pesquisa",
                            "Saúde",
                            "Bem-estar",
                            "Educação",
                            "Ensino",
                            "Sustentabilidade",
                            "Meio Ambiente"
                        )
                        val listAcademicEducation = arrayOf(
                            "Engenharia de Software",
                            "Administração",
                            "Nenhuma",
                            "Medicina",
                            "Psicologia",
                            "Arquitetura",
                            "Economia",
                            "Ciência da Computação",
                            "Design Gráfico",
                            "Marketing Digital",
                            "Direito",
                            "Engenharia Civil",
                            "Biologia",
                            "Outros"
                        )

                        val userListAreaOfInterest = arrayListOf<String>()

                        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                            Text(
                                text = "Áreas de interesse",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Secondary
                            )
                            FlowRow {
                                listAreaOfInterest.forEach {
                                    Box(modifier = Modifier.padding(horizontal = 2.dp)) {

                                        AssistChip(
                                            onClick = {
                                                userListAreaOfInterest.add(it)
                                            },
                                            label = {
                                                Text(
                                                    text = it,
                                                    maxLines = 1,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "Formação acadêmica",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Secondary
                            )
                            FlowRow {
                                listAcademicEducation.forEach {
                                    Box(modifier = Modifier.padding(horizontal = 2.dp)) {

                                        AssistChip(
                                            onClick = {
                                                userListAreaOfInterest.add(it)
                                            },
                                            label = {
                                                Text(
                                                    text = it,
                                                    maxLines = 1,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                    }
                                }
                            }

                        }
                    }

                    else -> {
                        OutlinedTextField(
                            value = inputValue,
                            placeholder = {
                                Text(text = placeholder)
                            },

                            singleLine = true,
                            onValueChange =
                            updateValue,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = keyboardType,
                                capitalization = KeyboardCapitalization.Words
                            ),
                        )
                    }
                }
            }


        }

        Button(
            onClick = nextStep,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(
                text = "Continuar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }

}