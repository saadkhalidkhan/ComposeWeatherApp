package com.droidgeeks.slweatherapp.presentation.details.entity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidgeeks.coreui.ui.theme.weatherTypography
import com.droidgeeks.slweatherapp.R

@Composable
fun SunriseBlockComposable(
    timeSunrise: String, timeSunset: String,
    navigateToFullScreen: (Int) -> Unit = { },
    showDetails: Boolean) {
    Card( modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .shadow(
            elevation = 50.dp, shape = RoundedCornerShape(8.dp), ambientColor = Color.Red
        )
        .clickable { navigateToFullScreen(24) },
        shape = RoundedCornerShape(19.dp),
        backgroundColor = colorResource(id = R.color.purple_332362),
        border = BorderStroke(2.dp, colorResource(id = R.color.purple_5a3e9c)),
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black.copy(alpha = 0.5f),)
                .padding(bottom = 16.dp)
                .background(color = colorResource(id = R.color.purple_332362))
                .padding(top = 16.dp)

                .clickable { navigateToFullScreen(24) }
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.wrapContentWidth()) {
                    TextWithImageComposable(
                        imageRes = R.drawable.ic_sun,
                        text = stringResource(id = R.string.sunrise),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = timeSunrise,
                        style = weatherTypography.h1,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                }
                if(showDetails){

                    Column(modifier = Modifier.wrapContentWidth()) {
                        TextWithImageComposable(
                            imageRes = R.drawable.ic_sun,
                            text = stringResource(id = R.string.sunset),
                            modifier = Modifier.padding(start = 16.dp).align(Alignment.End)
                        )
                        Text(
                            text = timeSunset,
                            style = weatherTypography.h1,
                            fontSize = 28.sp,
                            modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.End)
                        )

                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            SineCurveWithMovingDot()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Scrim(onClose: () -> Unit, modifier: Modifier = Modifier) {
    val strClose = stringResource(id = R.string.close)
    Box(modifier
        .fillMaxSize()
        .pointerInput(onClose) { detectTapGestures { onClose() } }
        .semantics {
            onClick(strClose) { onClose(); true }
        }
        .focusable()
        .onKeyEvent {
            if (it.key == Key.Escape) {
                onClose(); true
            } else {
                false
            }
        }
        .background(Color.DarkGray.copy(alpha = 0.75f))
    )
}


@Preview(showBackground = true)
@Composable
fun SunrisePreview() {
    SunriseBlockComposable("5:00 AM", "7:00 PM", showDetails = false )
}