package com.droidgeeks.slweatherapp.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.droidgeeks.coreui.ui.theme.WeatherAppTheme
import com.droidgeeks.slweatherapp.R
import com.droidgeeks.slweatherapp.presentation.navigation.NavScreen
import kotlin.random.Random

@Composable
fun SplashScreen(navController: NavController) {
    WeatherAppTheme() {
        Scaffold(modifier = Modifier) {padding ->
            Background(modifier = Modifier.padding())
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp+ 50.dp
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            val animatedValue = remember { Animatable(initialValue = screenWidth.value) }
            LaunchedEffect(Unit) {
                animatedValue.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 2000)
                )
            }
            Foreground(animatedValue)
        }
    }
}

@Composable
fun Foreground(animatedValue: Animatable<Float, AnimationVector1D>) {

    Box(modifier = Modifier
        .fillMaxSize()
        .graphicsLayer {
            this.translationX
        }
    ){
        Image(
            painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
                .alpha(1f)
                .align(Alignment.TopCenter)
                .offset(x = animatedValue.value.dp),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .padding(top = 180.dp)
                .width(120.dp)
                .height(120.dp)
                .offset(x = 10.dp + animatedValue.value.dp)
                .align(Alignment.TopEnd),
            contentDescription = "cloud"
        )
        Image(
            painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .padding(top = 180.dp)
                .width(150.dp)
                .height(150.dp)
                .offset(x = (-50).dp + animatedValue.value.dp)
                .align(Alignment.TopStart),
            contentDescription = "cloud"
        )
        Image(
            painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .padding(top = 0.dp)
                .width(280.dp)
                .height(280.dp)
                .align(Alignment.Center)
                .offset(x = animatedValue.value.dp),
            contentDescription = "cloud"
        )
        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .wrapContentSize()
            .padding(bottom = 100.dp)
        ) {

            Text(text = "Weather",
                modifier = Modifier
                    .offset(y = (10).dp)
                    .align(Alignment.CenterHorizontally),
                fontStyle = FontStyle.Normal,
                fontSize = 58.sp,

            )
            Text(text = "Forecasts",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 30.dp),
                fontStyle = FontStyle.Normal,
                fontSize = 58.sp,
            )
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(NavScreen.Main.route)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(58.dp)
                    .offset(y = (-100).dp)
            ) {
                Text(text = "Get Started", modifier = Modifier.padding(horizontal = 52.dp))
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

@Composable
fun Background(modifier: Modifier = Modifier){
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

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
            drawStars(this, 500f, 500f)
        }
    }
}

@Preview
@Composable
fun BackgroundPreview(){
    WeatherAppTheme {
        Background()
    }
}
@Preview
@Composable
fun ForegroundPreview(){
    WeatherAppTheme {
//        Foreground(animatedValue)
    }
}
@Preview
@Composable
fun SplashScreenPReview(){
    SplashScreen(rememberNavController())
}