package com.example.recruittutorial.authscreens

import android.support.customtabs.IPostMessageService.Default
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeTxt(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "Home Page"

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    HomeTxt()
}