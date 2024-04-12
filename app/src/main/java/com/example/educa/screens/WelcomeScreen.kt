package com.example.educa.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.educa.components.BackHeader
import com.example.educa.components.TitleText
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary

@Composable
fun WelcomeScreen(navController: NavHostController) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackHeader(navController = navController, redirectTo = "login")
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 0.dp, start = 30.dp, end = 30.dp, bottom = 30.dp)

        ) {
            Column {
                Row {
                    TitleText(text = "Bem vindo ao Educa", Primary)
                    TitleText(text = "+", Secondary)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Explore o nosso aplicativo de match de mentores e alunos! Conecte-se com mentores experientes ou alunos ávidos por aprender. Vamos crescer juntos!",
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = {
                    navController.navigate("register_email")
                },
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Vamos lá",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }


    }


}