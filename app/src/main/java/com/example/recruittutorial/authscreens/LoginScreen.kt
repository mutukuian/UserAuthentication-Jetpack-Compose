package com.example.recruittutorial.authscreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import androidx.navigation.NavHostController

import com.example.recruittutorial.R

import com.example.recruittutorial.commoncomponents.SocialMediaLogin

import com.example.recruittutorial.ui.theme.Roboto
import com.example.recruittutorial.ui.theme.focusedTextFieldText
import com.example.recruittutorial.ui.theme.textFieldContainer
import com.example.recruittutorial.viewmodels.LoginViewModel

import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavHostController,
//    viewModel: LoginViewModel
    viewModel: LoginViewModel = hiltViewModel()
){
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            //helper variable to support dark and light mode themes
            TopSection()

            Spacer(modifier = Modifier.height(36.dp))

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)) {

                LoginSection()
                val state = viewModel.logInState.collectAsState(initial = null)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    if (state.value?.isLoading == true){
                        CircularProgressIndicator()
                    }

                }
                val  scope = rememberCoroutineScope()
                val context = LocalContext.current

                Spacer(modifier = Modifier.height(30.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SocialMediaSection()

                    LaunchedEffect(key1 = state.value?.isSuccess){
                        scope.launch {
                            if (state.value?.isSuccess?.isNotEmpty() == true){
                                val success = state.value?.isSuccess
                                Toast.makeText(context,"$success",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    LaunchedEffect(key1 = state.value?.isError){
                        scope.launch {
                            if (state.value?.isError?.isNotEmpty() == true){
                                val error = state.value?.isError
                                Toast.makeText(context,"$error",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    CreateAccTxt(navController)

                }
            }
        }
    }
}

@Composable
private fun CreateAccTxt(navController: NavController) {
    //val uiColor = if (isSystemInDarkTheme()) White else Black
    Box(
        modifier = Modifier
            .fillMaxHeight(fraction = 0.8f)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(

            modifier = Modifier.clickable {
                navController.popBackStack()
            },
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF94A3B8),
                        fontSize = 14.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Normal
                    )
                )
                {
                    append("Don't Have  Account?")
                }
                withStyle(
                    style = SpanStyle(
//                        color = uiColor,
                        fontSize = 14.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium
                    )
                )
                {

                    append(" ")
                    append("Create Now")
                }
            }
        )
    }
}

@Composable
private fun SocialMediaSection() {
    Text(
        text = "Or Continue With",
        style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B))
    )
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        SocialMediaLogin(
            icon = R.drawable.google,
            text = "Google",
            modifier = Modifier.weight(1f)
        ) {

        }
        Spacer(modifier = Modifier.width(20.dp))

        SocialMediaLogin(
            icon = R.drawable.facebook,
            text = "Google",
            modifier = Modifier.weight(1f)
        ) {

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun LoginSection(
    viewModel: LoginViewModel = hiltViewModel()
 ) {

    val uiColor = if (isSystemInDarkTheme()) White else Black

    val scope = rememberCoroutineScope()
    var emailState by remember  { mutableStateOf("") }

    var passwordState by remember { mutableStateOf("") }


    Spacer(modifier = Modifier.height(15.dp))

//    LoginRegisterEmailTextField(
//        label = "Email",
//        trailing = "",
//        modifier = Modifier.fillMaxWidth()
//    )


    TextField(
//        modifier = modifier,
        value = emailState,
        onValueChange = {emailState = it},
        label = {
            Text(text = "Email", style = MaterialTheme.typography.labelMedium, color = uiColor)
        },
        colors = TextFieldDefaults.textFieldColors(

            containerColor = MaterialTheme.colorScheme.textFieldContainer,
            placeholderColor = MaterialTheme.colorScheme.focusedTextFieldText,

            ),
        trailingIcon = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    color = uiColor
                )
            }
        },
    )

    Spacer(modifier = Modifier.height(15.dp))


//    LoginRegisterPasswordTextField(
//        label = "Password",
//        trailing = "",
//        modifier = Modifier.fillMaxWidth()
//    )

    TextField(
        value = passwordState,
        onValueChange ={ passwordState = it },
        label = {
            Text(text = "Password", style = MaterialTheme.typography.labelMedium, color = uiColor)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.textFieldContainer,
            placeholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
        ),
        trailingIcon = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    color = uiColor
                )
            }
        },

        )



    Spacer(modifier = Modifier.height(15.dp))
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = {
            scope.launch {
                viewModel.loginUser(email = emailState, password = passwordState)
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSystemInDarkTheme()) Blue else Black,
            contentColor = White
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        Text(
            text = " Login ",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
        )
    }

}

@Composable
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) White else Black

    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.46f),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = uiColor
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.the_jobplacer),
                    style = MaterialTheme.typography.headlineMedium,
                    color = uiColor
                )
                Text(
                    text = stringResource(id = R.string.find_job),
                    style = MaterialTheme.typography.titleMedium,
                    color = uiColor
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.headlineLarge,
            color = Black
        )
    }
}
