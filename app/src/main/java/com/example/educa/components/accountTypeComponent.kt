package com.example.educa.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educa.ui.theme.Primary

@Composable
fun AccountTypeComponent(
    text: String,
    isSelected: Boolean,
    icon: ImageVector,
    onClick: () -> Unit

) {

    if (isSelected) {

        Button(
            onClick = onClick,
            shape = RoundedCornerShape(6),
            modifier = Modifier
                .size(140.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                modifier = Modifier.fillMaxSize(),

                ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Opção de aluno",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    } else {
        OutlinedButton(
            border = BorderStroke(1.dp, Primary),
            onClick = onClick,
            shape = RoundedCornerShape(6),
            modifier = Modifier
                .size(140.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                modifier = Modifier.fillMaxSize(),

                ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Opção de aluno",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}