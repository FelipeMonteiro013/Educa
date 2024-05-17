package com.example.educa.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UserInformationScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Row {
            IconButton(onClick = { navController.navigate("home") }) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Voltar",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Secondary
                )
            }
        }



        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {


//        TODO: Esse cara vai virar um componente onde eu mando o titulo e a lista para rodar o for
            for (i in 1..4)
                Card(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Minha área de interesse:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Divider(modifier = Modifier.padding(vertical = 10.dp))

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

                        FlowRow {
                            listAreaOfInterest.forEachIndexed { index, s ->
                                Box(modifier = Modifier.padding(horizontal = 2.dp)) {

                                    FilterChip(
                                        selected = true,
                                        onClick = {},
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

        }

    }
}
