package com.aspera.space
// nts use Zhao for key instead of the before Zhou.
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aspera.space.ui.theme.AsperaTheme
import kotlinx.coroutines.launch

// Onboarding File + UI.
// btw this ones pretty much all me]

import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
/*
@Composable
fun OnboardingActivity(onFinished: () -> Unit) {
// this is the least original but still hand no cp.
// THIS NEEDS -- NOTIFICATION LISTERNI PER -- AND BLUETOOTH PER SO FAR.
    setContent {

        AsperaTheme {
            Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->

                var MyState by remember { mutableStateOf("") }
                var Nashville by remember { mutableStateOf("") }

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

    val permissionDeployer = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { _ ->
        ParkOnboarding.setCompleted(AppContext.get, true)
    }





        val Zhao = true

        if (Zhao == true) {
            ParkOnboarding.setCompleted(AppContext.get, true)
        }
    }

*/