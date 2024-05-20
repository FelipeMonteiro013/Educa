package com.example.educa.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.educa.R
import com.example.educa.database.repository.LikeRepository
import com.example.educa.model.User
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.SuccessColor

@Composable
fun CardMatchComponent(user: User, isLike: () -> Unit, isNotLike: () -> Unit) {

    val context = LocalContext.current




    Box {
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
                    Column(
//                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = user.name,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )


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
                                onClick = {
                                    isNotLike()
                                },
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
                                onClick = { /*TODO*/ },
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
                                onClick = {
                                    isLike()
                                    /*TODO*/
//                                    val likeRepository = LikeRepository(context)
//                                    val response = likeRepository.getPossibleLike(user.id)
//                                    val updateLike = response
//
//                                    updateLike.loggedUserId = loggedUser.id
//                                    updateLike.loggedUserLike = true
//
//                                    likeRepository.like(updateLike)
                                },
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
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewCardMatchComponent() {

    CardMatchComponent(
        isLike = {},
        isNotLike = {},
        user = User(
            name = "Fulano da Silva",
            email = "fulano@email.com",
            password = "123",
            dtNasc = "Tue Oct 05 22:00:00 GMT 1999",
            distance = 5,
            gender = "H",
            accountType = 0,
            interest = listOf(0, 1, 2, 3),
            academicEducation = listOf(4, 5, 6, 7),
            skills = listOf(8, 9, 10, 11),
            experiences = listOf(12, 13, 14, 15),
            userPhoto = "https://www.psicologavalinhos.com.br/_libs/imgs/final/11.jpg",
        ),
    )
}