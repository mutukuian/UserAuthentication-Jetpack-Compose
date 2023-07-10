package com.example.recruittutorial.commoncomponents

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import com.example.recruittutorial.ui.theme.focusedTextFieldText
import com.example.recruittutorial.ui.theme.textFieldContainer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginRegisterEmailTextField(
    modifier: Modifier = Modifier,
    label : String,
    trailing: String,

){
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black

    var emailState by remember  { mutableStateOf("") }


    TextField(
        modifier = modifier,
        value = emailState,
        onValueChange ={ emailState = it},
        label = {
            Text(text = label, style = MaterialTheme.typography.labelMedium, color = uiColor)
        },
        colors = TextFieldDefaults.textFieldColors(

            containerColor = MaterialTheme.colorScheme.textFieldContainer,
            placeholderColor = MaterialTheme.colorScheme.focusedTextFieldText,

        ),
        trailingIcon = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = trailing,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    color = uiColor
                )
            }
        },
    )
}
















