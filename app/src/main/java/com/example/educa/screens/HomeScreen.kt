package com.example.educa.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.educa.R
import com.example.educa.database.repository.UserRepository
import com.example.educa.ui.theme.BackgroundColor
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary
import com.example.educa.ui.theme.SuccessColor
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

//                        TODO: Organizar essa função
fun getAgeFormat(dtNasc: String): Long {
    try {
//        val originalDateString = dtNasc

        // Formatter para parsear a data original
//        val originalFormatter = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT' yyyy", Locale.ENGLISH)

        // Parse da data original para um Date
//        val date: Date = originalFormatter.parse(originalDateString)

        // Converte Date para LocalDate
//        val birthDate: LocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        // Data atual
//        val currentDate = LocalDate.now()

        // Calcular a idade
//        val age = ChronoUnit.YEARS.between(birthDate, currentDate)
        Log.i("TESTE", dtNasc)
        return 1

    } catch (e: Exception) {
        Log.e("TESTE", e.message.toString())
        return 0
    }
}

@Composable
fun HomeScreen(navController: NavController) {
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
                    text = "Educa",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
                Text(
                    text = "+",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Secondary
                )
            }
            IconButton(onClick = {
                navController.navigate("discovery_tweaks")
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


        val listUsersState = remember {
            mutableStateOf(userRepository.listUsers())
        }

        Box {


            for (user in listUsersState.value) {


//            TODO: Esse cara vai virar um componente
                Card(
                    modifier = Modifier
                        .height(650.dp)
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = CardDefaults.elevatedCardElevation()

                ) {


                    Box(
                        contentAlignment = Alignment.BottomStart,
                        modifier = Modifier.background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ), startY = 800.0F
                            )
                        )
                    ) {


                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(user.userPhoto)
                                .build(),
                            contentDescription = null,
                            imageLoader = ImageLoader(context),
                            contentScale = ContentScale.FillHeight,
                            error = painterResource(R.drawable.baseline_image_not_supported_24),
                            modifier = Modifier.fillMaxSize()
                        )

                        Column(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black),
                                        startY = 0.0F,
                                        endY = 400.0F
                                    )
                                )
                        ) {


                            Spacer(modifier = Modifier.padding(vertical = 5.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            ) {
                                Text(
                                    text = user.name,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp
                                )


//                            Text(
//                                text = getAgeFormat(user.dtNasc).toString(),
////                                text = dateFormat(user.dtNasc).toString(),
//                                color = Color.White,
//                                fontSize = 32.sp,
//                                modifier = Modifier.padding(start = 10.dp)
//                            )
                            }

                            Text(
                                text = if (user.accountType == 0) "Aluno(a)" else "Professor(a)",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp, modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Spacer(modifier = Modifier.padding(vertical = 5.dp))
                            Text(
                                text = "a ${user.distance} km de distância",
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Spacer(modifier = Modifier.padding(vertical = 5.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceAround,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                IconButton(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(60.dp))
                                        .background(Color.White)

                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Close,
                                        contentDescription = null,
                                        Modifier.size(40.dp),
                                        tint = Color.Red
                                    )
                                }
                                IconButton(
                                    onClick = { navController.navigate("user_information/${user.id}") },
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(60.dp))
                                        .background(Color.White)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = null,
                                        Modifier
                                            .size(40.dp),
                                        tint = Primary
                                    )
                                }
                                IconButton(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(60.dp))
                                        .background(Color.White)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.MenuBook,
                                        contentDescription = null,
                                        Modifier
                                            .size(40.dp),
                                        tint = SuccessColor
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(vertical = 10.dp))
                        }


                    }


                }

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
                            Icons.Outlined.Notifications,
                            contentDescription = "Notificações"
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
                            Icons.Outlined.AccountCircle,
                            contentDescription = "Perfil"
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
