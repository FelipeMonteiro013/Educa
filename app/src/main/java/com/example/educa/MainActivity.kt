package com.example.educa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.educa.screens.DiscoveryTweaksScreen
import com.example.educa.screens.HomeScreen
import com.example.educa.screens.LoginScreen
import com.example.educa.screens.RegisterEmailScreen
import com.example.educa.screens.UserInformationScreen
import com.example.educa.screens.WelcomeScreen
import com.example.educa.ui.theme.EducaTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EducaTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { LoginScreen(navController) }
                        composable("welcome") { WelcomeScreen(navController) }
                        composable("register_email") { RegisterEmailScreen(navController) }
                        composable("home") { HomeScreen(navController) }
                        composable("user_information/{id}") {
                            val id = it.arguments?.getString("id")
                            UserInformationScreen(navController, id!!)
                        }
                        composable("discovery_tweaks") { DiscoveryTweaksScreen(navController) }
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