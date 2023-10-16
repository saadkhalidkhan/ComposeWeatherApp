package com.droidgeeks.slweatherapp.presentation.splash

import android.view.RoundedCorner
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.droidgeeks.coreui.ui.theme.WeatherAppTheme
import com.droidgeeks.slweatherapp.R
import kotlin.random.Random

@Composable
fun SplashScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.dark_blue),
                        colorResource(id = R.color.light_blue),
                    ),
                )
            )
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawStars(this, screenWidth.value, screenHeight.value)
        }
        Image(painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
                .alpha(1f)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Fit,
            contentDescription = null)
        Image(painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .padding(top = 200.dp)
                .width(120.dp)
                .height(120.dp)
                .offset(x = 10.dp)
                .align(Alignment.TopEnd),
            contentDescription = "cloud"
        )
        Image(painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .padding(top = 200.dp)
                .width(150.dp)
                .height(150.dp)
                .offset(x = (-50).dp)
                .align(Alignment.TopStart),
            contentDescription = "cloud"
        )
        Image(painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .padding(top = 0.dp)
                .width(280.dp)
                .height(280.dp)
                .align(Alignment.Center),
            contentDescription = "cloud"
        )
        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .wrapContentSize()
        ) {

            Text(text = "",
                modifier = Modifier,
                fontStyle = FontStyle.Normal,
                style = MaterialTheme.typography.h1.copy(fontWeight = FontWeight(20))
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .height(58.dp)
                    .offset(y = (-100).dp)
            ) {
                Text(text = "Get Started", modifier = Modifier.padding(horizontal = 46.dp))
            }

        }
    }
}
private fun drawStars(drawScope: DrawScope, screenWidth: Float, screenHeight: Float) {
    val screenArea = screenWidth * screenHeight
    val numStars = (screenArea / 50).toInt()
    val random = Random
    repeat(numStars) {
        val x = random.nextFloat() * drawScope.size.width
        val y = random.nextFloat() * drawScope.size.height
        val radius = random.nextFloat() * 0.8f + 0.4f
        val alpha = random.nextFloat() * 0.5f + 0.4f
        drawScope.drawCircle(
            color = Color.White.copy(alpha = alpha),
            center = Offset(x, y),
            radius = radius
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview(){
    WeatherAppTheme {
        SplashScreen()
    }
}