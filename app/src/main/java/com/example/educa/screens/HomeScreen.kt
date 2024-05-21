package com.example.educa.screens

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.educa.MatchNotificationService
import com.example.educa.components.CardMatchComponent
import com.example.educa.database.repository.LikeRepository
import com.example.educa.database.repository.UserRepository
import com.example.educa.model.Like
import com.example.educa.ui.theme.BackgroundColor
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlin.random.Random


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(navController: NavController, loggedUserId: String, listCardController: String) {

    val context = LocalContext.current

    val postNotificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    val matchNotificationService = MatchNotificationService(context)

    LaunchedEffect(key1 = true) {
        if (!postNotificationPermission.hasPermission) {
            postNotificationPermission.launchPermissionRequest()
        }

    }

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


        var listCardController by remember {
            mutableIntStateOf(if (listCardController.isNotEmpty()) listCardController.toInt() else 0)
        }

        val listUsersState by remember {
            mutableStateOf(userRepository.listUsers())
        }

        if (listCardController < listUsersState.size) {
            CardMatchComponent(user = listUsersState[listCardController], isLike = {
                val likeRepository = LikeRepository(context)
                val likeId = likeRepository.insert(
                    Like(
                        id = 0,
                        loggedUserId = loggedUser.id,
                        loggedUserLike = true,
                        userId = listUsersState[listCardController].id,
                        userLike = Random.nextBoolean()
                    )
                )

                val isMatch = likeRepository.verifyMatch(likeId)

                if (isMatch) {
                    matchNotificationService.showBasicNotification(
                        loggedUser.name,
                        listUsersState[listCardController].name
                    )
                }

                listCardController++
            }, isNotLike = {
                val likeRepository = LikeRepository(context)
                likeRepository.insert(
                    Like(
                        id = 0,
                        loggedUserId = loggedUser.id,
                        loggedUserLike = false,
                        userId = listUsersState[listCardController].id,
                        userLike = Random.nextBoolean()
                    )
                )

                listCardController++

            }, userInformation = {
                navController.navigate("user_information/${listUsersState[listCardController].id}/${loggedUserId}?listCardController=$listCardController")
            })
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
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
                    label = {
                        Text(text = "Início")
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Secondary,
                        unselectedIconColor = Primary,
                        indicatorColor = BackgroundColor,

                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.MenuBook, contentDescription = "Matches") },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    label = {
                        Text(text = "Meus Matches")
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Secondary,
                        unselectedIconColor = Primary,
                        indicatorColor = BackgroundColor,

                        )
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Outlined.Logout, contentDescription = "Sair"
                        )
                    },
                    label = {
                        Text(text = "Sair")
                    },
                    selected = selectedItem == 2,
                    onClick = { navController.navigate("login") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Secondary,
                        unselectedIconColor = Primary,
                        indicatorColor = BackgroundColor,

                        )
                )

            }
        }
    }

}
