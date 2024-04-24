package com.example.educa.components

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary
import java.util.Date

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RegisterComponent(
    titleText: String,
    descriptionText: String,
    placeholder: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    inputValue: String = "",
    updateValue: (String) -> Unit = {},
    backStep: () -> Unit,
    nextStep: () -> Unit,
    step: String,
    updateGender: (String) -> Unit = {},
    updateAccountType: (Int) -> Unit = {},
    updateDistance: (Int) -> Unit = {},
    createUser: () -> Unit = {},
    updateInterestCheckedList: (List<Int>) -> Unit = {},
    updateAcademicEducationCheckedList: (List<Int>) -> Unit = {},
    updateSkillsCheckedList: (List<Int>) -> Unit = {},
    updateExperiencesCheckedList: (List<Int>) -> Unit = {},
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
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

                        val date = datePickerState.selectedDateMillis?.let { Date(it) }

                        updateValue(date.toString())

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
                                        updateGender("H")
                                    })
                                GenderComponent(
                                    text = "Mulher",
                                    isSelected = false,
                                    onClick = {
                                        updateGender("M")
                                        genderSelected = 1
                                    })
                                GenderComponent(
                                    text = "Além de binário",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 2
                                        updateGender("AB")
                                    })
                            }

                            1 -> {

                                GenderComponent(
                                    text = "Homem",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 0
                                        updateGender("H")
                                    })
                                GenderComponent(
                                    text = "Mulher",
                                    isSelected = true,
                                    onClick = {
                                        updateGender("M")
                                        genderSelected = 1
                                    })
                                GenderComponent(
                                    text = "Além de binário",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 2
                                        updateGender("AB")
                                    })
                            }

                            2 -> {
                                GenderComponent(
                                    text = "Homem",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 0
                                        updateGender("H")
                                    })
                                GenderComponent(
                                    text = "Mulher",
                                    isSelected = false,
                                    onClick = {
                                        updateGender("M")
                                        genderSelected = 1
                                    })
                                GenderComponent(
                                    text = "Além de binário",
                                    isSelected = true,
                                    onClick = {
                                        genderSelected = 2
                                        updateGender("AB")
                                    })
                            }

                            else -> {
                                GenderComponent(
                                    text = "Homem",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 0
                                        updateGender("H")
                                    })
                                GenderComponent(
                                    text = "Mulher",
                                    isSelected = false,
                                    onClick = {
                                        updateGender("M")
                                        genderSelected = 1
                                    })
                                GenderComponent(
                                    text = "Além de binário",
                                    isSelected = false,
                                    onClick = {
                                        genderSelected = 2
                                        updateGender("AB")
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
                                updateDistance(it.toInt())
                            },
                            valueRange = 1f..80f,
                            modifier = Modifier.padding(horizontal = 30.dp)
                        )

                    }

                    "aboutYouProfessional" -> {
                        val listAreaOfInterest by remember {
                            mutableStateOf(
                                arrayOf(
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
                            )
                        }
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

                        val tempList = listOf<Int>()

                        var multipleInterestChecked by remember {
                            mutableStateOf(tempList)
                        }

                        var multipleAcademicEducationChecked by remember {
                            mutableStateOf(tempList)
                        }

                        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                            Text(
                                text = "Áreas de interesse",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Secondary
                            )


                            FlowRow {
                                listAreaOfInterest.forEachIndexed { index, s ->
                                    Box(modifier = Modifier.padding(horizontal = 2.dp)) {

                                        FilterChip(
                                            selected = multipleInterestChecked.contains(index),
                                            onClick = {
                                                multipleInterestChecked =
                                                    if (multipleInterestChecked.contains(index)) {
                                                        multipleInterestChecked.minus(index)
                                                    } else {
                                                        multipleInterestChecked.plus(index)
                                                    }

                                                updateInterestCheckedList(multipleInterestChecked)
                                            },
                                            label = {
                                                Text(
                                                    text = s,
                                                    maxLines = 1,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            colors = FilterChipDefaults.filterChipColors(
                                                labelColor = Color.Gray,
                                                selectedLabelColor = Color.White,
                                                selectedContainerColor = Primary
                                            ),
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
                                listAcademicEducation.forEachIndexed { index, s ->
                                    Box(modifier = Modifier.padding(horizontal = 2.dp)) {

                                        FilterChip(
                                            selected = multipleAcademicEducationChecked.contains(
                                                index
                                            ),
                                            onClick = {
                                                multipleAcademicEducationChecked =
                                                    if (multipleAcademicEducationChecked.contains(
                                                            index
                                                        )
                                                    ) {
                                                        multipleAcademicEducationChecked.minus(index)
                                                    } else {
                                                        multipleAcademicEducationChecked.plus(index)
                                                    }
                                                updateAcademicEducationCheckedList(
                                                    multipleAcademicEducationChecked
                                                )
                                            },
                                            label = {
                                                Text(
                                                    text = s,
                                                    maxLines = 1,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            colors = FilterChipDefaults.filterChipColors(
                                                labelColor = Color.Gray,
                                                selectedLabelColor = Color.White,
                                                selectedContainerColor = Primary
                                            ),
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                    }
                                }
                            }

                        }
                    }

                    "aboutYouPersonal" -> {
                        val listOfSkills = arrayOf(
                            "Comunicação",
                            "Resolução de Problemas",
                            "Liderança",
                            "Nenhuma",
                            "Pensamento Crítico",
                            "Criatividade",
                            "Gerenciamento de Tempo",
                            "Trabalho em Equipe",
                            "Adaptação a Mudanças",
                            "Outros"
                        )
                        val listOfExperiences = arrayOf(
                            "Trabalho Voluntário",
                            "Estágios",
                            "Nenhuma",
                            "Projetos de Pesquisa",
                            "Intercâmbio",
                            "Atividades Extracurriculares",
                            "Seminários",
                            "Publicações Acadêmicas",
                            "Graduação",
                            "Pós-graduação",
                            "Publicações Acadêmicas",
                            "Iniciação científica",
                            "Conferências",
                            "Empresas",
                            "Conferências",
                            "Outros",
                        )

                        val tempList = listOf<Int>()

                        var multipleSkillsChecked by remember {
                            mutableStateOf(tempList)
                        }


                        var multipleExperiencesChecked by remember {
                            mutableStateOf(tempList)
                        }

                        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                            Text(
                                text = "Habilidades",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Secondary
                            )


                            FlowRow {
                                listOfSkills.forEachIndexed { index, s ->
                                    Box(modifier = Modifier.padding(horizontal = 2.dp)) {

                                        FilterChip(
                                            selected = multipleSkillsChecked.contains(index),
                                            onClick = {
                                                multipleSkillsChecked =
                                                    if (multipleSkillsChecked.contains(index)) {
                                                        multipleSkillsChecked.minus(index)
                                                    } else {
                                                        multipleSkillsChecked.plus(index)
                                                    }
                                                updateSkillsCheckedList(multipleSkillsChecked)
                                            },
                                            label = {
                                                Text(
                                                    text = s,
                                                    maxLines = 1,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            colors = FilterChipDefaults.filterChipColors(
                                                labelColor = Color.Gray,
                                                selectedLabelColor = Color.White,
                                                selectedContainerColor = Primary
                                            ),
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "Experiências",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Secondary
                            )
                            FlowRow {
                                listOfExperiences.forEachIndexed { index, s ->
                                    Box(modifier = Modifier.padding(horizontal = 2.dp)) {
                                        FilterChip(
                                            selected = multipleExperiencesChecked.contains(index),
                                            onClick = {
                                                multipleExperiencesChecked =
                                                    if (multipleExperiencesChecked.contains(index)) {
                                                        multipleExperiencesChecked.minus(index)
                                                    } else {
                                                        multipleExperiencesChecked.plus(index)
                                                    }

                                                updateExperiencesCheckedList(
                                                    multipleExperiencesChecked
                                                )
                                            },
                                            label = {
                                                Text(
                                                    text = s,
                                                    maxLines = 1,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            colors = FilterChipDefaults.filterChipColors(
                                                labelColor = Color.Gray,
                                                selectedLabelColor = Color.White,
                                                selectedContainerColor = Primary
                                            ),
                                            shape = RoundedCornerShape(20.dp)
                                        )

                                    }
                                }
                            }

                        }
                    }

                    "userPhoto" -> {
                        var uri by remember {
                            mutableStateOf<Uri?>(null)
                        }

                        val singlePhotoPicture =
                            rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
                                uri = it
                                updateValue(uri.toString())
                            }

                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {


                            OutlinedCard(modifier = Modifier.size(350.dp)) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            singlePhotoPicture.launch(
                                                PickVisualMediaRequest(
                                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                                )
                                            )
                                        }
                                ) {

                                    if (uri !== null) {
                                        AsyncImage(
                                            model = uri,
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Outlined.Add,
                                            contentDescription = "Adicionar foto",
                                            modifier = Modifier.size(64.dp)
                                        )
                                        Text(text = "Adicionar foto")
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
                            visualTransformation = visualTransformation,
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
                            trailingIcon = trailingIcon
                        )
                    }
                }
            }


        }

        Button(
            onClick = if (step == "userPhoto") createUser else nextStep,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(
                text = if (step == "userPhoto") "Cadastrar" else "Continuar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }

}