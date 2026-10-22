// Note: Fuck conventions. My thoughts are messy, so my code is messy but in a beautiful way. //
package com.aspera.space

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.aspera.space.ui.theme.AsperaTheme
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppContext.get = applicationContext // fix later for service contesxt instead of mainactivity context.
        AppContext.currentActivity = this

        // ONBOARDING CHECK //

        ParkOnboarding.wasCompleted(this)

        Adrenaline.setup(this)

        enableEdgeToEdge()

        setContent {
            AsperaTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->

                    var MyState by remember { mutableStateOf("") }
                    var Nashville by remember { mutableStateOf("") }
                    val name = "Matthew"

                    val scope = rememberCoroutineScope()

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        ElevatedButton(onClick = {
                            scope.launch {
                                Nashville = Fly(MyState)
                            }
                        }) {
                            Text(Nashville)
                        }
                        //WayLessSad()
                        OutlinedTextField(
                            value = MyState,
                            onValueChange = {London -> MyState = London},
                            label = {
                                Text("$name@Aspera_Interface")
                            }

                        )

                        //StateTwin()

                        // calling it inline ish kinda equivalent now
                    }

                    /*
                    Greeting(
                        name = "Matthew",
                        modifier = Modifier.padding(innerPadding)

                    )

                     */
                }
            }
        }
    }
}

/*
@Composable
fun StateTwin() {




}
*/
/*@Composable
fun WayLessSad() {
    ElevatedButton(onClick = { Fly(MyState) }) {
        Text("Submit to Aspera")
    }
}
*/
/*
// Top Bar//
Scaffold(
    topBar = {
        TopBar(title= "Greeting")
    },

) { innerPadding ->
Box(modifier = Modifier.padding(innerPadding)) {
    Text("MainActivity")
}
}
*/

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
    AsperaTheme {
        Greeting("Matthew")
    }
}
/*
@Preview(showBackground = true)
@Composable
fun WayLessSad() {
    ElevatedButton(onClick = { Fly("what is the time") }) {
        Text("AsperaVA")
    }
}
*/
//var MyState by remember { mutableStateOf("") }
