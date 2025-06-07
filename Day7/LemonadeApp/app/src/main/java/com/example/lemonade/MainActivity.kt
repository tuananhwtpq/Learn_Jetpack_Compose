package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Lemonade(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {

    var result by remember { mutableStateOf(1) }
    var squeezeRequired by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val imageSource = when(result){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textSource = when(result){
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                when (result){
                    1 -> {
                        result = 2
                        squeezeRequired = (2..4).random()
                        squeezeCount = 0

                    }
                    2 -> {
                        squeezeCount++
                        if(squeezeCount >= squeezeRequired){
                            result = 3
                        }
                    }
                    3 -> {
                        result = 4
                    }
                    4 -> {
                        result = 1
                    }
                }
            }
            ) {
                Image(
                    painter = painterResource(imageSource),
                    contentDescription = "!"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(textSource),
                fontSize = 18.sp,
                )

            if (result == 2){
                Text(
                    text = "Lượt vắt còn lại: ${squeezeRequired - squeezeCount}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        Lemonade()
    }
}