package com.example.educa.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryTweaksScreen(navController: NavController, loggedUserId: String) {

    var sliderPosition by remember { mutableFloatStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate("home/${loggedUserId}") }) {

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



        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {


//        TODO: Esse cara vai virar um componente onde eu mando o titulo e a lista para rodar o for
            Card(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Distância máxima",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
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
                        Text(text = sliderPosition.toInt().toString() + "km")
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

    }
}
