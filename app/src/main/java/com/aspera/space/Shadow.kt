package com.aspera.space

import android.app.KeyguardManager
import android.content.Context
import android.media.AudioManager
import android.view.KeyEvent
import android.media.MediaMetadata
import android.media.session.MediaSessionManager
import android.content.ComponentName
import android.provider.MediaStore
import com.aspera.space.divide.Media_Notification_Listener
// for settings stuff like accesssablialiyy ora notify listener. deeper sys level ints.
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
//Bluetooth
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothAdapter
//biometrics
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

//timedate
import java.time.LocalTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter


//region SECURITY
// ai fucked it up so this is all me.
var Zhou: Boolean = false // will read at end then edecute if it equesls true. will be chatgned when verification is complete.
// suspend function makes sure the app hangs on it and waits becfore checking auth.
suspend fun AuthPrompt(activity: FragmentActivity): Boolean = suspendCancellableCoroutine { continuation ->
    Zhou = false // just in case... //also named after Zhou Shen.

    val executor /*that's such a cool name*/= ContextCompat.getMainExecutor(activity)/*go back to mainactivity basically*/
    val listener = object : BiometricPrompt.AuthenticationCallback() {
        // IF AUTH WORKS
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result) // now will set it to true. dangerous but done.
            Zhou = true
            Toast.makeText(activity, "Identity Verified", Toast.LENGTH_SHORT).show() // ya lets tell em about that
            if (continuation.isActive) continuation.resume(true)
        }
// IF AUTH FAILS
        override fun onAuthenticationError(errorCode: Int, errorString: CharSequence) {
            super.onAuthenticationError(errorCode, errorString)
            Zhou = false // just in case again
            Toast.makeText(activity, "Verification Failed. Engaging Lockdown protocols."/*I SHOULD PROBABLY FOLOLOW THROUHG ON THAT!*/, Toast.LENGTH_SHORT).show() // ya lets tell em about that AGAIN
            // vim keys is so nice
            if (continuation.isActive) continuation.resume(false)
        }

    }

    // HOW IT LOOKS

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
    //now make it Aspera style...
        .setTitle("Aspera Authentication")
        .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL) // finger or face or pin.
        .build() // idk wtf that does.

    val biometricPrompt = BiometricPrompt(activity, executor, listener)
    biometricPrompt.authenticate(promptInfo)

}

// UNLOCK CHECK

inline fun Context.DeviceisUnlocked(): Boolean {
    val IsOn = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
    return !IsOn.isKeyguardLocked
}

//endregion SECURITY
//region CONFIRMATION SOUNDS

//endregion CONFIRMATION SOUNDS
//region MEDIA

// inline function makes faster here? for exec="" calls in fleet
inline fun Context.CastleofGlass(crawling: Int) {

    val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    am.dispatchMediaKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, crawling))
    am.dispatchMediaKeyEvent(KeyEvent(KeyEvent.ACTION_UP, crawling))
    //ps action up and down for onbuttonup and on button down would be html equiv

}

inline fun Context.getCurrentPlayingTrack(): String {
    val manager = getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
    return try {// PARTIALLY WRITTEN BY AI. ////////// THIWS IS FOR THE MOST PART THE ONLY AI PART //
        val listenerService = ComponentName(this, Media_Notification_Listener::class.java)
        val activeSession = manager.getActiveSessions(listenerService).firstOrNull()
        val metadata = activeSession?.metadata
        val breakingthehabit = metadata?.getString(MediaMetadata.METADATA_KEY_TITLE)
        val LinkinPark = metadata?.getString(MediaMetadata.METADATA_KEY_ARTIST)

        if (!breakingthehabit.isNullOrEmpty() && !LinkinPark.isNullOrEmpty()) {
            "Now playing $breakingthehabit by $LinkinPark"
        } else if (!breakingthehabit.isNullOrEmpty()) {
            "Now playing $breakingthehabit"
        } else {
            "No song currently playing"
        }
    } catch (e: SecurityException) {
        println("MEDIA -- MINOR 1: Notification Listener Permission Required")
        val OpenSettings = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(OpenSettings)
        "Notification Access Settings Opening."

    }

    catch (e: Exception) {
        "MEDIA -- MINOR 2: Unable to read current track"
    }
}

//endregion
//region MUTE

inline fun Context.AudioMute() {
    val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0)
}

inline fun Context.AudioUnmute() {
    val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0)
}


//endregion MUTE
//region TIME/DATE
// im pround of this this was almost all ortiginal + it was super fun and easy lol except sdk was updated from iin 24 to min 26.
val prettynow = DateTimeFormatter.ofPattern("HH:mm")
val prettydate = DateTimeFormatter.ofPattern("EEEE, MMMM d")

fun TimeNow(): String {
    return LocalTime.now().format(prettynow)
}

fun DateNow(): String {
    return LocalDate.now().format(prettydate)
}

//endregion TIME/DATE

//region BLUETOOTH
//pretty pround here as welll.
/*
fun Context.bluetoothCTL(argument: String) {
    // could be optional

    val blueMan = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val btadapter = blueMan.adapter ?: return
// end optional
        if (argument == "enable") {
            startActivity(
                Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            )
        }

        else if (argument == "disable") {
        btadapter.disable()
    }

        else {
            println("BLUETOOTH -- MINOR 3: ")
        }
}*/

//endregion BLUETOOTH







// WEATHER //