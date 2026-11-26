package com.aspera.space.ui
// All Aboard //

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.aspera.space.License

import com.aspera.space.StartTextToSpeech

// Button (Elevated Style) -- See more at "https://developer.android.com/develop/ui/compose/components/button" //

@Composable
fun InTheEnd(onClick: () -> Unit) {
    ElevatedButton(onClick = { StartTextToSpeech() }) {
        Text("AsperaVA")
    }
}

@Composable
fun Numb(friction: () -> Unit) {
    ElevatedButton(onClick = {friction}) {
        Text("Open Source Licenses")
    }
}
