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

// Permissions (mostly for bluetooth)

import android.Manifest
import androidx.core.content.ContextCompat
import android.app.Activity
import android.app.NotificationManager
import android.hardware.camera2.CameraAccessException
import android.os.BatteryManager

//biometrics
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

//timedate
import java.time.LocalTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter
//flashlight

import android.hardware.camera2.CameraManager
import android.hardware.camera2.CameraCharacteristics

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
    //THIS MAY BE DUE TO NO PASSWORD?
            // vim keys is so nice
            if (continuation.isActive) continuation.resume(false)
        }

    }

    // HOW IT LOOKS

    //need support for android 9 + 10 without lag on newer devices.
    val promptBuilder = BiometricPrompt.PromptInfo.Builder()
        //now make it Aspera style...
        .setTitle("Aspera Authentication")

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        promptBuilder.setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
    } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        @Suppress("DEPRECATION")
        promptBuilder.setDeviceCredentialAllowed(true)
    } else {
        promptBuilder.setNegativeButtonText("Cancel")
    }

    val promptInfo = promptBuilder.build()

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

fun Context.TimeNow(): String {
    return LocalTime.now().format(prettynow)
}

fun Context.DateNow(): String {
    return LocalDate.now().format(prettydate)
}

//endregion TIME/DATE

//region BLUETOOTH
//pretty pround here as welll.

inline fun Context.bluetoothCTL(argument: String) {
    // could be optional
    try {
        // for nowwill just go to bluetooth setting there.
//    val blueMan = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
// end optional
        if (argument == "enable") {
            startActivity(
                Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }


            )/*
        catch (e: SecurityException) {
            println("MINOR: 5 bluetooth - bluetoot hpermission not given")
        }*/
            println("Aspera - start -- bluetooth popup activity")
        } else if (argument == "disable") {
            startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            )
        } else {
            println("BLUETOOTH -- MINOR 4: bluetooth action not found/completed.")
        }

    } catch(e: SecurityException) {
        println("MEDIA -- MINOR 3: Bluetooth Devices Permission Required")

        println("BLUETOOTH -- AppContext type: ${AppContext.currentActivity::class.java.name}")
        val ghosts = AppContext.currentActivity as? Activity
        if (ghosts !=null) {


            requestPermissions(// may have to make this not framgentatciritvty if other mainactivity type isn't fragmentactivity
                ghosts,
                arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                1
            )

            //time to actually execute now assuming that worked lol hopefully no infinite loop
            println("executing AFTER permission request")
            startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            )
        }
    }
}

/*
    catch (e: SecurityException) {
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
}*/

//endregion BLUETOOTH

//region NETWORK

inline fun Context.nmCLI(argument: String) {
    try {
        if (argument == "enable" || argument == "disable") {
            startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            )
            }
    } catch (e: SecurityException) {
        println("FLAT 1 - ANDROID ADK OLDER THAN ANDROID 10. FALLING BACK TO OLD WIFI CONTROL PANEL.")

        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })

    } catch (e: Exception) {
        println("WIFI -- MINOR 6: Wifi Settings Unavailable: ${e.message}")
    }
    }
//endregion NETWORK

//region DND

inline fun Context.shutupCTL(argument: String/*, timevalue: Int = 0*/) {
    //notification manager for dnd

    try {

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // check if notify policy access permission is allowed
            println(notificationManager.isNotificationPolicyAccessGranted)
        if (!notificationManager.isNotificationPolicyAccessGranted) {
            throw SecurityException("FLAT 2 - NOTIFICATION POLICY ACCESS REQUIRED")
        }

            if (argument == "enable") {
                // do the thing
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY)
                println("I can't breath without you")
            }


        else if (argument == "disable") {
                // undo the thing
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
            print("You are not alone")
        }

        else {
            println("Notification manager failed somehow,")
        }
/*
        else if (argument == "timed-enable" && timevalue > 0) {

        }
*/
    } catch (e: SecurityException) {
        startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

}

//endregion DND

//region BRIGHTNESS
//the idea here is an arg and so it's either a percent, for the brightness level, (maybe ill have a string parester later?
// and the it could also be a string like "ADAPT" or "UNADAPT", which will toggle adaptive brightness.

inline fun Context.brightnessCTL(argument: String) {
    try {

        if (!Settings.System.canWrite(this)) {
            throw SecurityException("FLAT 3 -- SYSTEM SETTINGS WRITE PERMISSION REQUIRED")
        }

        if (argument == "enable") {
            Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
        }

        else if (argument == "disable") {
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        }
        else {
            println("System brightness not changed due to error in system settings write.")
        }
// could a an int or just add to arg a number for arg parsing brightness control. will not implement yet because arg parsing is not implemented yet.
    } catch (e: SecurityException) {
        println("FLAT 3 -- DISPLAYING PERMISSION POPUP FOR BRIGHTNESS/SYSTEM SETTINGS CONTROL.")
        //do the permission thing
         // need this for com.aspera. etc.....

        startActivity(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
            data = android.net.Uri.parse("package:$packageName")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })

    }
}

//endregion BRIGHTNESS

//region BATTERY

inline fun Context.batteryCTL(argument: String): String {

    val batman = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    val level = batman.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

     if (argument == "GET_LEVEL") {
        return if (batman.isCharging) {
            "Battery level at $level% and charging"
        }
         else {
             "Battery level at $level%"
        }
    }

    else if (argument == "SAVER_ON" || argument == "SAVER_OFF") {

        try {
            startActivity(Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            })
                return ""



        } catch (e: Exception) {
            println("BATTERY -- MINOR: UNEXPECTED ERROR AFTER startActivity in battery saver settings.")
        }

     } else {
         println("MAJOR-- UNSPECIFIED BATTERY COMMAND. THROWING ERROR NOW.")
         throw Exception("Unspecified battery command.")
         return ""
     }
    return ""
}

//endregion BATTERY

//region LIGHT

inline fun Context.flashlightCTL(argument: String) {
    try {
        val camMan = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        val cameraID = camMan.cameraIdList.firstOrNull { id ->
            camMan.getCameraCharacteristics(id)
                .get(android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE) == true

        } ?: camMan.cameraIdList.firstOrNull()

        if (cameraID != null) {
            if (argument == "enable") {
                camMan.setTorchMode(cameraID, true)
                println("Aspera -- Light ON")
            } else if (argument == "disable") {
                camMan.setTorchMode(cameraID, false)
                println("Aspera -- Light OFF")
            }
        }
    } catch (e: CameraAccessException) {
        println("Could not access camera settings to toggle the flashlight/torch.")
    }

    catch (e: Exception) {
        println("MAJOR -- Unknown error in Camera arg, action, or pipeline.")
    }
}

//endregion LIGHT


// WEATHER //