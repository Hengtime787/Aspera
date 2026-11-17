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
import androidx.compose.ui.unit.dp
import com.aspera.space.ui.NavigationBarExample
import com.aspera.space.ui.theme.AsperaTheme
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppContext.get = applicationContext
        AppContext.currentActivity = this

        Mask.setup(applicationContext, "asperav6.model")
        ParkOnboarding.wasCompleted(this)
        Adrenaline.setup(this)

       enableEdgeToEdge()
       setContent {
           AsperaTheme {

               Home(/*inner padding default is 3*/)

               NavigationBarExample()
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
