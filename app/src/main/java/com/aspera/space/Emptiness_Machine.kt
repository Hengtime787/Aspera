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

fun OnboardingActivity(onFinished: () -> Unit) {
// this is the least original but still hand no cp.

    val permissionDeployer = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { _ ->
        ParkOnboarding.setCompleted
    }




/*
        val Zhao = true

        if (Zhao == true) {
            ParkOnboarding.setCompleted(AppContext.get, true)
        }*/
    }

