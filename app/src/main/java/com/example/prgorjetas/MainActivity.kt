package com.example.prgorjetas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.prgorjetas.ui.theme.PrGorjetasTheme
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrGorjetasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // Greeting("Android")
                    ValorTotal()
                }
            }
        }
    }
}

@Composable
fun ValorTotal() {
    var vTotal by remember { mutableStateOf("") }
    var pGorjeta by remember { mutableStateOf(18f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Valor Total",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = vTotal,
                onValueChange = { vTotal = it },
                label = { Text(text = "Digite um valor") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* implementar ação de finalização */ }
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Percentual",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${pGorjeta.toInt()}%",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, end = 16.dp)
            )
            Slider(
                value = pGorjeta.toFloat(),
                onValueChange = { pGorjeta = it.toFloat() },
                valueRange = 0f..30f,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)

            )
        }
        Column(modifier = Modifier.padding(32.dp)) {
            InfoGorjeta(pGorjeta.toInt(), vTotal)
        }
    }
    }

@Composable
fun InfoGorjeta(percentual: Int, total:String) {
    Column(Modifier.fillMaxWidth()) {
        Row() {
            Text(
                text = " ", modifier = Modifier
                    .weight(1.0f)
            )
            Text(
                text = "15 % ", modifier = Modifier
                    .weight(1.0f),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )
            Text(
                text = "$percentual %", modifier = Modifier
                    .weight(1.0f),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )
        }
        val totalGorjeta = total.toFloatOrNull() ?: 0f
        val gorjeta = totalGorjeta * 0.15
        Row() {
            Text(
                text = "Gorjeta", modifier = Modifier
                    .weight(1.0f)
            )
            Text(
                text = "R$ ${String.format("%.2f", gorjeta)}",
                modifier = Modifier
                    .weight(1.0f)
                    .background(androidx.compose.ui.graphics.Color.Gray)
                    .border(2.dp, color = androidx.compose.ui.graphics.Color.White),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "R$ ${String.format("%.2f", calculargorjeta(percentual,total))}", modifier = Modifier
                    .weight(1.0f)
                    .background(androidx.compose.ui.graphics.Color.Gray)
                    .border(2.dp, color = androidx.compose.ui.graphics.Color.White),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
            )}
            var total15 = totalGorjeta.toInt() + gorjeta
            var totalPerc = totalGorjeta.toInt() + calculargorjeta(percentual,total)
            Row() {
                Text(
                    text = "Total", modifier = Modifier
                        .weight(1.0f)
                )
                Text(
                    text = "R$ ${String.format("%.2f", total15)}", modifier = Modifier
                        .weight(1.0f)
                        .background(androidx.compose.ui.graphics.Color.Gray)
                        .border(2.dp, color = androidx.compose.ui.graphics.Color.White),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "R$ ${String.format("%.2f", totalPerc)}", modifier = Modifier
                        .weight(1.0f)
                        .background(androidx.compose.ui.graphics.Color.Gray)
                        .border(2.dp, color = androidx.compose.ui.graphics.Color.White),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
        }
        }
    }

@Composable
fun calculargorjeta(percentual: Int, valor: String): Float{
    val valorConvertido = valor.toFloatOrNull() ?: 0f
    return valorConvertido * percentual / 100f
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PrGorjetasTheme {
       // Greeting("Android")
        ValorTotal()
    }
}