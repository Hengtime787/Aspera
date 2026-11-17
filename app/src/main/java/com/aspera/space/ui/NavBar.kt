package com.aspera.space.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//iconimplementation("androidx.compose.material:material-icons-extended")s
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Description
import com.aspera.space.Home
import com.aspera.space.Settings


enum class Destination(
    val title: String,
    val icon: @Composable () -> Unit
) {
    HOME("Home", {Icon(
        imageVector = Icons.Filled.Home,
        contentDescription = "Home"
    )}),
    SETTINGS("Settings", {Icon(
        imageVector = Icons.Filled.Settings,
        contentDescription = "Settings"
    )}),
    DOCS("Docs", {Icon(
        imageVector = Icons.Filled.MenuBook,
        contentDescription = "Docs"
    )})
}

@Composable
fun NavigationBarExample(modifier: Modifier = Modifier) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val destinations = Destination.entries

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                destinations.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = destination.icon,
                        label = { Text(destination.title) }
                    )
                }
            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            when (destinations[selectedIndex]) {
                Destination.HOME -> Home()
                Destination.SETTINGS -> Text("Albums Screen")
                Destination.DOCS -> Text("Playlists Screen")
            }
        }
    }
}
