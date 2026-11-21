package com.aspera.space

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
/**User Interface page for Home*/
fun Home(innerPadding: Dp = 3.dp) {

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
                            onValueChange = { London -> MyState = London },
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



