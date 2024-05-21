package com.example.educa.screens

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.educa.MatchNotificationService
import com.example.educa.R
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
fun HomeScreen(
    navController: NavController,
    loggedUserId: String,
    listCardController: String,
    accountTypeFilter: String,
    distance: String,

    ) {

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
    var selectedItem by remember { mutableIntStateOf(0) }

    var controller by remember {
        mutableIntStateOf(if (listCardController.isNotEmpty()) listCardController.toInt() else 0)
    }

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
            verticalAlignment = Alignment.CenterVertically,
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
            if (selectedItem == 0) {
                IconButton(onClick = {
                    navController.navigate(
                        "discovery_tweaks/${loggedUserId}?listCardController=$controller&accountType=${accountTypeFilter}&distance=${
                            if (distance == "null") {
                                80
                            } else distance.toInt()
                        }"
                    )
                }) {

                    Icon(
                        tint = Primary,
                        imageVector = Icons.Outlined.Tune,
                        contentDescription = "Ajustes de descoberta"
                    )
                }
            }


        }

        if (selectedItem == 0) {


            val filterAccountType by remember {
                mutableStateOf(if (accountTypeFilter != "null" && accountTypeFilter.toInt() != 2) accountTypeFilter.toInt() else null)
            }

            val filterDistance by remember {
                mutableIntStateOf(
                    if (distance == "null") {
                        80
                    } else distance.toInt()
                )
            }

            val listUsersState by remember {
                mutableStateOf(
                    userRepository.testFilter(
                        loggedUserId = loggedUser.id,
                        filterAccountType,
                        filterDistance
                    )
                )
            }

            if (controller < listUsersState.size) {
                CardMatchComponent(user = listUsersState[controller], isLike = {
                    val likeRepository = LikeRepository(context)
                    val likeId = likeRepository.insert(
                        Like(
                            id = 0,
                            loggedUserId = loggedUser.id,
                            loggedUserLike = true,
                            userId = listUsersState[controller].id,
                            userLike = Random.nextBoolean()
                        )
                    )

                    val isMatch = likeRepository.verifyMatch(likeId)

                    if (isMatch) {
                        matchNotificationService.showBasicNotification(
                            loggedUser.name,
                            listUsersState[controller].name
                        )
                    }

                    controller++
                }, isNotLike = {
                    val likeRepository = LikeRepository(context)
                    likeRepository.insert(
                        Like(
                            id = 0,
                            loggedUserId = loggedUser.id,
                            loggedUserLike = false,
                            userId = listUsersState[controller].id,
                            userLike = Random.nextBoolean()
                        )
                    )

                    controller++

                }, userInformation = {
                    navController.navigate("user_information/${listUsersState[controller].id}/${loggedUserId}?listCardController=${controller}&accountType=${accountTypeFilter}&distance=${filterDistance}")
                })
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Outlined.MenuBook,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(60.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {
                        Text(
                            text = "Não temos mais pessoas para mostrar.",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray,
                            fontSize = 20.sp,
                            minLines = 2,
                            modifier = Modifier.width(300.dp)

                        )

                    }
                    OutlinedButton(
                        modifier = Modifier.padding(vertical = 10.dp),
                        onClick = { controller = 0 }) {
                        Text(text = "Procurar novamente")
                    }

                }
            }
        }

        if (selectedItem == 1) {

            val likeRepository = LikeRepository(context)
            val listOfMatches = likeRepository.getMatches(loggedUserId = loggedUser.id)

            if (listOfMatches.isEmpty()) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Icon(
                        imageVector = Icons.Outlined.MenuBook,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(60.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {
                        Text(
                            text = "Você ainda não tem nenhum Match.",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray,
                            fontSize = 20.sp,
                            minLines = 2,
                            modifier = Modifier.width(300.dp)

                        )
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.height(650.dp)) {

                    for (item in listOfMatches) {
                        val user = userRepository.getUserById(item.userId)

                        item {
                            Row {
                                androidx.compose.material3.ListItem(
                                    headlineContent = {
                                        Text(
                                            text = user.name,
                                            fontWeight = FontWeight.Bold
                                        )
                                    },
                                    supportingContent = {
                                        Text(
                                            text = if (user.accountType == 0) "Aluno(a)" else "Professor(a)",
                                            color = Primary
                                        )
                                        Text(
                                            text = user.email,
                                            modifier = Modifier.padding(top = 5.dp)
                                        )

                                    },
                                    leadingContent = {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(user.userPhoto)
                                                .build(),
                                            contentDescription = null,
                                            imageLoader = ImageLoader(context),
                                            contentScale = ContentScale.Crop,
                                            error = painterResource(R.drawable.baseline_image_not_supported_24),
                                            modifier = Modifier
                                                .width(70.dp)
                                                .height(70.dp)
                                                .clip(CircleShape)
                                        )

                                    }

                                )
                            }
                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp)
                            )
                        }
                    }
                }
            }
        }


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
