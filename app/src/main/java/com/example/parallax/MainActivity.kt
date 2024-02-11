package com.example.parallax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.parallax.ui.theme.ParallaxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParallaxTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main(){
    var moonScrollSpeed = 0.08f
    val midBgScrollSpeed = 0.03f

    val imageHeight = (LocalConfiguration.current.screenHeightDp * (2f/3f).dp)
    val lazyListState = rememberLazyListState()

    var moonOffset = remember {
        mutableFloatStateOf(0f)
    }
    var midBgOffset = remember {
        mutableFloatStateOf(0f)
    }
    LazyColumn(modifier = Modifier.fillMaxWidth(), state = lazyListState, content = {
        items(10){
            Text(text = "Sample Item", modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
        }
        items(count = 0){
            Box(modifier = Modifier
                .clipToBounds()
                .fillMaxWidth()
                .height(imageHeight)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFf36b21),
                            Color(0xFFf9a521),
                        )
                    )
                )) {
                Image(
                    painter = painterResource(id = R.drawable.ic_moonbg),
                    contentDescription = "moon",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize())
                Image(
                    painter = painterResource(id = R.drawable.ic_midbg),
                    contentDescription = "mid bg",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize())
                Image(
                    painter = painterResource(id = R.drawable.ic_outerbg),
                    contentDescription = "outer bg",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize())
            }
        }
        items(20){
            Text(text = "Sample Item", modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
        }
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParallaxTheme {
        Main()
    }
}