package com.example.educa.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.educa.components.InformationCardComponent
import com.example.educa.database.repository.UserRepository
import com.example.educa.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UserInformationScreen(navController: NavController, id: String) {


    val context = LocalContext.current
    val userRepository = UserRepository(context)

    val userData by remember {
        mutableStateOf(userRepository.getUserById(id.toLong()))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate("home") }) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Voltar",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Secondary
                )
            }
            Text(text = userData.name)
        }



        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {


//        TODO: Esse cara vai virar um componente onde eu mando o titulo e a lista para rodar o for


            if (userData.interest.size > 0) {
                InformationCardComponent(title = "Minha área de interesse:", list = userData.interest)
            }

            if (userData.academicEducation.size > 0) {
                InformationCardComponent(title = "Minha formação academica:", list = userData.academicEducation)
            }
            if (userData.skills.size > 0) {
                InformationCardComponent(title = "Minhas habilidades:", list = userData.skills)
            }
            if (userData.experiences.size > 0) {
                InformationCardComponent(title = "Minhas experiencias:", list = userData.experiences)
            }

//                Card(
//                    onClick = { /*TODO*/ }, modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 10.dp)
//                ) {
//                    Column(modifier = Modifier.padding(10.dp)) {
//                        Text(
//                            text = "Minha área de interesse:",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 18.sp
//                        )
//                        Divider(modifier = Modifier.padding(vertical = 10.dp))
//
//                        val listAreaOfInterest by remember {
//                            mutableStateOf(
//                                arrayOf(
//                                    "Técnologia",
//                                    "Inovação",
//                                    "Negócios",
//                                    "Artes",
//                                    "Cultura",
//                                    "Ciências",
//                                    "Pesquisa",
//                                    "Saúde",
//                                    "Bem-estar",
//                                    "Educação",
//                                    "Ensino",
//                                    "Sustentabilidade",
//                                    "Meio Ambiente"
//                                )
//                            )
//                        }
//
//                        FlowRow {
//                            listAreaOfInterest.forEachIndexed { index, s ->
//                                Box(modifier = Modifier.padding(horizontal = 2.dp)) {
//
//                                    FilterChip(
//                                        selected = true,
//                                        onClick = {},
//                                        label = {
//                                            Text(
//                                                text = s,
//                                                maxLines = 1,
//                                                textAlign = TextAlign.Center
//                                            )
//                                        },
//                                        colors = FilterChipDefaults.filterChipColors(
//                                            labelColor = Color.Gray,
//                                            selectedLabelColor = Color.White,
//                                            selectedContainerColor = Primary
//                                        ),
//                                        shape = RoundedCornerShape(20.dp)
//                                    )
//
//                                }
//
//                            }
//                        }
//                    }
//                }

        }

    }
}
