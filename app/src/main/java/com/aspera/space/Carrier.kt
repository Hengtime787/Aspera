package com.aspera.space

import android.content.Context
import android.media.AudioManager
import android.view.KeyEvent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import kotlin.system.exitProcess
import android.os.Process


object AppContext {
    lateinit var get: Context
    lateinit var currentActivity: FragmentActivity
}
// NOTE: _[PLCHLDR]_ in comments means refer to PLCHLDR.

/*Import list:

intent_engine.kt #/#* take in Resolve()

*/

// adaption of core.py.
//heavily modified adaptation lol
// placeholders are marked by linkin park lyrics


// GET LIBRARY ENTRY //

fun Launch(hook: String): Ships {

    val frigate = Fleet[hook]
    if (frigate == null) {
        println("ERROR 1")
        println("Hook not found ")
        throw IllegalStateException("Hook not found ")
    }
    return(frigate)
}

// TTS FUNCTION HERR

// AUTHENTICATION //

suspend fun Key(vessel: Ships) {

    val isUnlocked = AppContext.get.DeviceisUnlocked()

    if (isUnlocked == true) {
        println("Dev is currently unlocked")
    }

    Zhou = false // JUST IN CASE

    if (vessel.authLevel == "MAX") {
        // pay for what you've done
        // UPDATE LATER WITH SECOND CONFIRMATION AFTER
    }

    else if (vessel.authLevel == "HIGH") {
        Zhou = false // again, just in case.
        // In the wasteland of today
        println(Zhou)
        Zhou = AuthPrompt(AppContext.currentActivity) // straight verif
    }

    else if (vessel.authLevel == "LOW" || vessel.authLevel == "MED") {

        Zhou = false // just in case
        // In the wasteland of today
        if (isUnlocked == true) {
            Zhou = true // its lareayd unlocked
        }
        else {
            Zhou = AuthPrompt(AppContext.currentActivity) // go for verirfication
        }

    }

    else if (vessel.authLevel == "NONE") {
        Zhou = true
        // In the wasteland of today
    }

    else if (vessel.authLevel == "TRIGGER_HAPPY") {
        Zhou = true
        // Back when we were still changing for the better
    }

    else {
        println("// 16 // 16 // 16 // 16 //")
        println("ERROR 2")
        println("authLevel not found/defined.")
        exitProcess(0)
        Process.killProcess(Process.myPid())
    }
}

// Response



    else {
        println("sound.Errory")
        println("ERROR 3")
        println("RESPONSE TYPE NOT FOUND/DEFINED")
    }

}

fun Flux() {
    //personality response sender
    
}

// NTS //

// MED . Screen is on and unlocked or will prompt unlock

//\\ NTS \\//

// RUNTIME //
suspend fun Fly(request: String): String /* <------- ^^^ REMOVE IN FINAL THIS ONLY HERE FOR MAINACTIVITY TESTS*/ {
    //might as well just put the request in there and have it be required and just run it like taht yk.
   /* val request =
        "Placeholder for stt input"// do a .strip AND a .lower to that as well (for keyword only!)*/
    // hook = Resolve(request) //This is the real final intent

    val hook = Resolve(request)// also no need lowercase fastText is fire

    println(hook)

    val pallet = Launch(hook)

    /*%^65*/ println(pallet)

     // Verification Checks

    Key(pallet)

    if (Zhou == true) {
        Inter(pallet) // Sound Checks
        Fleet[hook]?.Exec()   // use !! for exit on NONE/UNKNOWN
    }

    else {
        println("16 16 16 16 16 16 16 16 16")
        println("ERROR 4")
        println("ACTION NOT AUTHENTICATED")
        //implement error sound pls

    }
// if key and inter both pass, then run the action Deploy()

// for debug mode have key and inter run a series of prints for their values


    // here will be function to execute the action. AFTER verifying all verification is 100% valid.

//  if Fleet[hook].authLevel == "MAX"
//  require screen on unlocked and biometrics and pin and final confirmation.

    // elif == "NONE"

    /*
  MAKE SOME BEEP WHEN REQUIRING CONFIRMATION RECOGNISABLE
  */
    return hook // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ REMOVE FOR FINAL!!!!!!!!!!!!!!!!!!! THIS RETURN IS ONLY HERE FOR MAINACTIVITY TESTING
}
