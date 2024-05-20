package com.example.educa.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educa.database.repository.InterestRepository
import com.example.educa.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun InformationCardComponent(title: String, list: List<Int>) {
    Card(
        onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Divider(modifier = Modifier.padding(vertical = 10.dp))

            val context = LocalContext.current
            val interestRepository = InterestRepository(context)

            list.forEachIndexed { index, s ->
                var x = interestRepository.getInterest(1)

                Log.i("TESTE", x.toString())
            }

            FlowRow {
                list.forEachIndexed { index, s ->
                    Box(modifier = Modifier.padding(horizontal = 2.dp)) {

                        FilterChip(
                            selected = true,
                            onClick = {},
                            label = {
                                Text(
                                    text = s.toString(),
                                    maxLines = 1,
                                    textAlign = TextAlign.Center
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                labelColor = Color.Gray,
                                selectedLabelColor = Color.White,
                                selectedContainerColor = Primary
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )

                    }

                }
            }
        }
    }
}