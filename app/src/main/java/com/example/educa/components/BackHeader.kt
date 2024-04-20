package com.example.educa.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.educa.ui.theme.Secondary

@Composable
fun BackHeader (
    navController: NavController,
    redirectTo: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp)
    ) {
        IconButton(onClick = {
            navController.navigate(redirectTo)
        }) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "voltar para tela anterior",
                tint = Secondary,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}