package com.example.educa

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.educa.screens.DiscoveryTweaksScreen
import com.example.educa.screens.HomeScreen
import com.example.educa.screens.LoginScreen
import com.example.educa.screens.RegisterEmailScreen
import com.example.educa.screens.UserInformationScreen
import com.example.educa.screens.WelcomeScreen
import com.example.educa.ui.theme.EducaTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            EducaTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("welcome") { WelcomeScreen(navController) }
                    composable("register_email") { RegisterEmailScreen(navController) }
                    composable(
                        "home/{loggedUserId}?listCardController={listCardController}&accountType={accountType}&distance={distance}",
                        arguments = listOf(
                            navArgument(name = "listCardController") { defaultValue = "0" }
                        ),

                        ) {
                        val loggedUserId = it.arguments?.getString("loggedUserId")
                        val listCardController = it.arguments?.getString("listCardController")
                        val accountType = it.arguments?.getString("accountType", "null")
                        val distance = it.arguments?.getString("distance", "null")

                        HomeScreen(
                            navController,
                            loggedUserId!!,
                            listCardController!!,
                            accountType!!,
                            distance!!
                        )
                    }
                    composable("user_information/{id}/{loggedUserId}?listCardController={listCardController}&accountType={accountType}&distance={distance}") {
                        val id = it.arguments?.getString("id")
                        val loggedUserId = it.arguments?.getString("loggedUserId")
                        val listCardController = it.arguments?.getString("listCardController")
                        val accountType = it.arguments?.getString("accountType", "null")
                        val distance = it.arguments?.getString("distance", "null")
                        UserInformationScreen(
                            navController,
                            id!!,
                            loggedUserId!!,
                            listCardController!!,
                            accountType!!,
                            distance!!
                        )
                    }
                    composable("discovery_tweaks/{loggedUserId}?listCardController={listCardController}&accountType={accountType}&distance={distance}") {
                        val loggedUserId = it.arguments?.getString("loggedUserId")
                        val listCardController = it.arguments?.getString("listCardController")
                        val accountType = it.arguments?.getString("accountType", "null")
                        val distance = it.arguments?.getString("distance", "null")
                        DiscoveryTweaksScreen(
                            navController,
                            loggedUserId!!,
                            listCardController!!,
                            accountType!!,
                            distance!!
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EducaTheme {
        Greeting("Android")
    }
}