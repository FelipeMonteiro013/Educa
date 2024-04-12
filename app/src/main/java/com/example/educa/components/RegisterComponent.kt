package com.example.educa.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterComponent(
    titleText: String,
    descriptionText: String,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    inputValue: String,
    updateValue: (String) -> Unit = {},
    backStep: () -> Unit,
    nextStep: () -> Unit,
    step: String,
    updateGender: (Int) -> Unit = {}
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
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


            Column() {
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

                if (step == "dtNasc") {
                    val datePickerState =
                        rememberDatePickerState(
                            initialSelectedDateMillis = 1578096000000,
                            initialDisplayMode = DisplayMode.Picker
                        )
                    DatePicker(
                        state = datePickerState,
                        modifier = Modifier.padding(horizontal = 16.dp),

                        )
                } else if (step == "gender") {
                    var genderSelected by remember {
                        mutableIntStateOf(-1)
                    }

                    if (genderSelected == 0) {
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
                    } else if (genderSelected == 1) {

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
                    } else if (genderSelected == 2) {
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
                    } else {
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


                } else {
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