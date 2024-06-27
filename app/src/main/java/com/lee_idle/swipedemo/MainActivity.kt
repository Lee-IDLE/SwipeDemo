package com.lee_idle.swipedemo

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.lee_idle.swipedemo.ui.theme.SwipeDemoTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwipeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)){
                        MainScreen()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun MainScreen() {
    val parentBoxWidth = 320.dp
    val childBoxSides = 30.dp

    val swipeableState = rememberSwipeableState("L")
    val widthPx = with(LocalDensity.current){
        (parentBoxWidth - childBoxSides).toPx()
    }

    val anchors = mapOf(0f to "L", widthPx / 2 to "C", widthPx to "R")

    Box(
        modifier = Modifier
            .padding(20.dp)
            .height(childBoxSides)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
    ){
        // 선
        Box(Modifier.fillMaxWidth().height(5.dp)
            .background(Color.DarkGray).align(Alignment.CenterStart))
        // 왼쪽 동그라미
        Box(Modifier.size(10.dp).background(color = Color.DarkGray,
            shape = CircleShape).align(Alignment.CenterStart))
        // 가운데 동그라미
        Box(Modifier.size(10.dp).background(Color.DarkGray,
            shape = CircleShape).align(Alignment.Center))
        // 오른쪽 동그라미
        Box(Modifier.size(10.dp).background(Color.DarkGray,
            shape = CircleShape).align(Alignment.CenterEnd))

        // 네모 박스
        Box(modifier = Modifier
            .offset{ IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .size(childBoxSides)
            .background(Color.Blue),
            contentAlignment = Alignment.Center
        ){
            Text(swipeableState.currentValue,
                color = Color.White,
                fontSize = 22.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwipeDemoTheme {
        MainScreen()
    }
}