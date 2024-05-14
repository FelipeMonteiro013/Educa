package com.example.educa.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.educa.database.repository.UserRepository
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary


@Composable
fun ImageFromLocalUri(uri: String) {
    val painter = rememberAsyncImagePainter(uri)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}







@Composable
fun HomeScreen(navController: NavController) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
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
                Text(text = "+", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Secondary)
            }
            IconButton(onClick = { /*TODO*/ }) {

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

//        ImageFromLocalUri("https://avatars2.githubusercontent.com/u/3265208?v=3&s=100")

        Card(
            modifier = Modifier
//                .fillMaxWidth()
                .padding(15.dp)
        ) {



            val ht = "https://avatars2.githubusercontent.com/u/3265208?v=3&s=100"
            val uri = "content://com.android.providers.downloads.documents/document/msf%3A31"




            Image(
                painter = rememberAsyncImagePainter(
                    model = Uri.parse(uri)  // or ht
                )
                ,
                contentDescription = "123",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )




            Box(

                contentAlignment = Alignment.Center
            ) {

                for (user in listUsersState.value) {
                    ImageFromLocalUri(user.userPhoto)
                Text(text = user.toString())
                }
            }
//                Column(modifier = Modifier.padding(10.dp)) {
//
//                Row {
//                    Text(text = "Marcos Antônio")
//                    Text(text = "57")
//                }
//
//            Text(text = "Professor")
//            }
//            Text(text = "a 6 km de distância")
        }
        var selectedItem by remember { mutableIntStateOf(0) }

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
                icon = { Icon(Icons.Outlined.Notifications, contentDescription = "Notificações") },
                selected = selectedItem == 2,
                onClick = { selectedItem = 2 },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Secondary,
                    unselectedIconColor = Primary,
                    indicatorColor = Color.White,

                    )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Outlined.AccountCircle, contentDescription = "Perfil") },
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
