package com.example.educa.screens

import androidx.compose.foundation.background
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
import com.example.educa.ui.theme.BackgroundColor
import com.example.educa.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UserInformationScreen(
    navController: NavController,
    id: String,
    loggedUserId: String,
    listCardController: String,
    accountType: String,
    distance: String
) {

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
            IconButton(onClick = { navController.navigate("home/${loggedUserId}?listCardController=${listCardController}&accountType=${accountType}&distance=${distance.toInt()}") }) {

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

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(BackgroundColor)
        ) {


            if (userData.interest.isNotEmpty()) {
                InformationCardComponent(
                    title = "Minha área de interesse:",
                    list = userData.interest
                )
            }

            if (userData.academicEducation.isNotEmpty()) {
                InformationCardComponent(
                    title = "Minha formação academica:",
                    list = userData.academicEducation
                )
            }
            if (userData.skills.isNotEmpty()) {
                InformationCardComponent(title = "Minhas habilidades:", list = userData.skills)
            }
            if (userData.experiences.isNotEmpty()) {
                InformationCardComponent(
                    title = "Minhas experiencias:",
                    list = userData.experiences
                )
            }

        }

    }
}
