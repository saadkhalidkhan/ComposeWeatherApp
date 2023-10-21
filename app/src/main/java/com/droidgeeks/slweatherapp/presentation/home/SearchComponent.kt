package com.droidgeeks.slweatherapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    text: String,
    onTextChanged: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = { newText ->
            onTextChanged(newText)
        },
        textStyle = TextStyle(color = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x80000000)) // Translucent black background
            .padding(16.dp),
        singleLine = true,
        cursorBrush = SolidColor(Color.White), // Cursor color
    )
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    SearchBar(text = "City", onTextChanged = {})
}
