package com.aspera.space

// All Aboard //

import android.os.Build
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.speech.SpeechRecognizer.createSpeechRecognizer
import androidx.annotation.RequiresApi
import android.os.Bundle

fun Shells() {

}

@RequiresApi(Build.VERSION_CODES.S)
suspend fun main() {
/*
    val Burning = SpeechRecognizer.createOnDeviceSpeechRecognizer(this)
    Burning.setRecognitionListener(object: RecognitionListener {
        fun onResults(results: Bundle?)
    })
*/
    val command: String = SpeechRecognizer.RESULTS_RECOGNITION

 // sound saying 'im listening"

val burn = Fly(command)
    
println("burn it down")


}