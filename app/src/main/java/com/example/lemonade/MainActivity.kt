package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold (
                    topBar =  {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Lemonade",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = Color.Yellow
                            )
                        )
                    }
                )
                {
                    innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
//                        .wrapContentSize(Alignment.Center),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        LemonadeApp()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeButtonImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2, 3, 4 -> {
            R.drawable.lemon_squeeze
        }
        5 -> R.drawable.lemon_drink
        else -> {
            result = 0
            R.drawable.lemon_restart
        }
    }

    val textResource = when (result) {
        1 -> R.string.tap_lemon_tree
        2, 3, 4 -> R.string.tap_lemon
        5 -> R.string.tap_lemonade
        else -> {
            R.string.tap_empty_glass
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Button(
            onClick = {
                result += if (result == 2) {
                    (0..3).random()
                } else 1
            },
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(R.string.lemon_tree)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResource),
        )
    }

}


@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeButtonImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}