package com.droidgeeks.slweatherapp.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CompassView() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (circle, north, east, south, west) = createRefs()
        val circleRadius = 100.dp

        // Circle background
        CircleBackground(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(circle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text("N",
            modifier = Modifier.constrainAs(north) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Text("E",
            modifier = Modifier.constrainAs(east) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })

        Text("S",
            modifier = Modifier.constrainAs(south) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })

        Text("W",
            modifier = Modifier.constrainAs(west) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            })
    }
}

@Composable
fun CircleBackground(modifier: Modifier) {
    DrawCircle(modifier, color = Color.White, outlineColor = Color.Black, outlineSize = 2)
}

@Composable
fun DrawCircle(
    modifier: Modifier = Modifier,
    color: Color,
    outlineColor: Color,
    outlineSize: Int,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (circle) = createRefs()
        val circleSize = 200.dp

        Box(
            modifier = Modifier
                .size(circleSize)
                .constrainAs(circle) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(color = color, shape = CircleShape)
                .clip(CircleShape)
                .border(width = outlineSize.dp, color = outlineColor, shape = CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCompass() {
    CompassView()
}
