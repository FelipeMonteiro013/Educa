package com.example.educa.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.educa.components.CardMatchComponent
import com.example.educa.database.repository.LikeRepository
import com.example.educa.database.repository.UserRepository
import com.example.educa.ui.theme.BackgroundColor
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary



@Composable
fun HomeScreen(navController: NavController, loggedUserId: String) {


    val context = LocalContext.current
    val userRepository = UserRepository(context = context)
    val loggedUser = userRepository.getUserById(loggedUserId.toLong())


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxHeight()
            .background(BackgroundColor)
    ) {


//        Header
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row {

                Text(
                    text = "Educa", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Primary
                )
                Text(
                    text = "+", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Secondary
                )
            }
            IconButton(onClick = {
                navController.navigate("discovery_tweaks/${loggedUserId}")
            }) {

                Icon(
                    tint = Primary,
                    imageVector = Icons.Outlined.Tune,
                    contentDescription = "Ajustes de descoberta"
                )
            }


        }

        val context = LocalContext.current
        val userRepository = UserRepository(context)

        var listCardController by remember {
            mutableStateOf(0)
        }

        val listUsersState by remember {
            mutableStateOf(userRepository.listUsers())
        }

        if (listCardController < listUsersState.size) {
            CardMatchComponent(user = listUsersState[listCardController], isLike = {
                val likeRepository = LikeRepository(context)
                val response =
                    likeRepository.getPossibleLike(listUsersState[listCardController].id)

                response.loggedUserId = loggedUser.id
                response.loggedUserLike = true

                likeRepository.like(response)

                listCardController++
            }, isNotLike = {
                val likeRepository = LikeRepository(context)
                val response =
                    likeRepository.getPossibleLike(listUsersState[listCardController].id)

                response.loggedUserId = loggedUser.id
                response.loggedUserLike = false

                likeRepository.like(response)

                listCardController++

            }, userInformation = {
                navController.navigate("user_information/${listUsersState[listCardController].id}/${loggedUserId}")
            })
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {

                Text(text = "Acabou!")
            }
        }

        var selectedItem by remember { mutableIntStateOf(0) }

//        Footer
        Row {


            NavigationBar(containerColor = Color.Transparent) {
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.Home, contentDescription = "Pagina inicial") },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Secondary,
                        unselectedIconColor = Primary,
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.MenuBook, contentDescription = "Matches") },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Secondary,
                        unselectedIconColor = Primary,
                        indicatorColor = Color.White,

                        )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Outlined.Notifications, contentDescription = "Notificações"
                        )
                    },
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Secondary,
                        unselectedIconColor = Primary,
                        indicatorColor = Color.White,

                        )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Outlined.AccountCircle, contentDescription = "Perfil"
                        )
                    },
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Secondary,
                        unselectedIconColor = Primary,
                        indicatorColor = Color.White,

                        )
                )

            }
        }
    }

}
