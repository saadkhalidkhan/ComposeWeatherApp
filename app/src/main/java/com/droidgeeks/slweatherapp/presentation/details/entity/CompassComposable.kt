package com.droidgeeks.slweatherapp.presentation.details.entity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.droidgeeks.slweatherapp.R

@Composable
fun CompassView(angle: Float) {
    ConstraintLayout {
        val (circle, north, east, south, west) = createRefs()

        // Circle background
        CircleBackground(
            modifier = Modifier
                .constrainAs(circle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            angle
        )

        Text("N",
            modifier = Modifier
                .padding(5.dp)
                .constrainAs(north) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        Text("E",
            modifier = Modifier
                .padding(5.dp)
                .constrainAs(east) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                })

        Text("S",
            modifier = Modifier
                .padding(5.dp)
                .constrainAs(south) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                })

        Text("W",
            modifier = Modifier
                .padding(5.dp)
                .constrainAs(west) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                })
    }
}

@Composable
fun CircleBackground(modifier: Modifier, angle: Float) {
    DrawCircle(modifier, color = Color.White, outlineColor = Color.Black, outlineSize = 2, angle = angle)
}

@Composable
fun DrawCircle(
    modifier: Modifier = Modifier,
    color: Color,
    outlineColor: Color,
    outlineSize: Int,
    angle: Float,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (circle, arrow) = createRefs()
        val circleSize = 100.dp

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
                .border(width = outlineSize.dp, color = outlineColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .rotate(angle)
                    .height(50.dp)
                    .width(50.dp),
                painter = painterResource(id = R.drawable.ic_compass),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCompass() {
    CompassView(0f)
}
