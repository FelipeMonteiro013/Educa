package com.example.educa.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.educa.database.repository.UserRepository
import com.example.educa.ui.theme.BackgroundColor
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryTweaksScreen(
    navController: NavController,
    loggedUserId: String,
    listCardController: String,
    accountType: String,
    distance: String
) {

    var sliderPosition by remember {
        mutableFloatStateOf(
            if (distance == "null") {
                80f
            } else distance.toFloat()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(BackgroundColor)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(onClick = { navController.navigate("home/${loggedUserId}?listCardController=$listCardController") }) {

                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Voltar",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Secondary
                )
            }
            Text(
                text = "Ajustes de descoberta",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Primary
            )


        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var accountTypeFilter by remember {
                mutableIntStateOf(
                    if (accountType == "null") {
                        2
                    } else accountType.toInt()
                )
            }


            val userRepository = UserRepository(context = LocalContext.current)

            var listFiltered by remember {
                mutableStateOf(userRepository.listUsersDiferentLoggedUser(loggedUserId.toLong()))
            }

            Column {


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)

                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Preferência de descoberta",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Primary
                        )
                        Divider(modifier = Modifier.padding(vertical = 10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            FilterChip(
                                selected = accountTypeFilter == 2,
                                onClick = { accountTypeFilter = 2 },
                                label = {
                                    Text(text = "Todos", fontSize = 18.sp)
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    labelColor = Color.Gray,
                                    selectedLabelColor = Color.White,
                                    selectedContainerColor = Primary
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            FilterChip(
                                selected = accountTypeFilter == 0,
                                onClick = {
                                    accountTypeFilter = 0
                                    listFiltered = userRepository.listByAccountType(
                                        loggedUserId.toLong(),
                                        accountTypeFilter
                                    )
                                },
                                label = { Text(text = "Alunos", fontSize = 18.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    labelColor = Color.Gray,
                                    selectedLabelColor = Color.White,
                                    selectedContainerColor = Primary
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            FilterChip(
                                selected = accountTypeFilter == 1,
                                onClick = { accountTypeFilter = 1 },
                                label = { Text(text = "Professores", fontSize = 18.sp) },
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


//        TODO: Esse cara vai virar um componente onde eu mando o titulo e a lista para rodar o for
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)

                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "Distância máxima",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Primary
                        )
                        Divider(modifier = Modifier.padding(vertical = 10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp)
                        ) {
                            Text(text = "Preferência de distância")
                            Text(
                                text = sliderPosition.toInt().toString() + "km",
                                fontWeight = FontWeight.Bold,
                                color = Secondary
                            )
                        }
                        Slider(
                            value = sliderPosition,
                            onValueChange = {
                                sliderPosition = it
                            },
                            valueRange = 1f..80f,
                            modifier = Modifier.padding(horizontal = 30.dp)
                        )

                    }
                }
            }
            Button(onClick = {
                navController.navigate("home/${loggedUserId}?listCardController=${0}&accountType=${accountTypeFilter}&distance=${sliderPosition.toInt()}")
            }) {
                Text(text = "Aplicar filtros", fontSize = 18.sp)
            }
        }

    }
}
