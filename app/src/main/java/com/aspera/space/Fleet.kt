package com.aspera.space

import android.view.KeyEvent // crawling
import android.widget.Toast

// This file gonna run the commands, and stores a library of exes and the verification levels and arg requirements. Kinda like concept py.

// Defaults and Blueprint for all vals
/* To implement in Core

reading authLevel string and force auth using it.
make requires arg work
run Exec with launch() or like Ships.Launch or something


*/

// To Add

// Calls (911 especially) "Aspera, call EMS" and it will do that .


// AHT -- ASPERA HOOK TEMPLATE -- AHT \\



// Blueprint

data class Ships(
    val Exec: () -> Unit,
    val authLevel: String = "MAX",
    val requiresArg: Boolean = false,
    val responseType: String = "Chime"
)

// Here's the Library

val Fleet = mapOf(
//region Media
// Media //

    "MEDIA_PLAY" to Ships(
        Exec = {

            AppContext.get.CastleofGlass(KeyEvent.KEYCODE_MEDIA_PLAY)

        },
        authLevel = "NONE",
        responseType = "Chirp"
//hopefully shouldnt need that just defaults
    ),

    "MEDIA_PAUSE" to Ships(
        Exec = {

            println("media.pause")
            AppContext.get.CastleofGlass(KeyEvent.KEYCODE_MEDIA_PAUSE)

        },
        authLevel = "NONE",
        responseType = "Chirp"
//hopefully shouldnt need that just defaults
    ),

    "MEDIA_NEXT" to Ships(
        Exec = {

            println("media.next")
            AppContext.get.CastleofGlass(KeyEvent.KEYCODE_MEDIA_NEXT)

        },
        authLevel = "NONE",
        responseType = "Chirp"
//hopefully shouldnt need that just defaults
    ),

    "MEDIA_PREVIOUS" to Ships(
        Exec = {

            println("media.previous")
            AppContext.get.CastleofGlass(KeyEvent.KEYCODE_MEDIA_PREVIOUS)

        },
        authLevel = "NONE",
        responseType = "Chirp"
//hopefully shouldnt need that just defaults
    ),

    "MEDIA_GET" to Ships(
        Exec = {

            println("media.get")
            Toast.makeText(AppContext.get, AppContext.get.getCurrentPlayingTrack(), Toast.LENGTH_SHORT).show()

        },
        authLevel = "NONE",
        responseType = "Personality"
//hopefully shouldnt need that just defaults
    ),
    //endregion Media

    //region Mute
    // Mute //
    "MUTE" to Ships(
        Exec = {

            println("audio.mute")
            AppContext.get.AudioMute()

        },
        authLevel = "NONE",
        responseType = "Chirp"
    ),

    "UNMUTE" to Ships(
        Exec = {

            println("audio.unmute")
            AppContext.get.AudioUnmute()
        },
        authLevel = "NONE",
        responseType = "Chirp"
    ),
//endregion Mute//
    //region Device Control
    // Device Control //

    "BLUETOOTH_ON" to Ships(
        Exec = {

            println("Life life life is life is going on.")

        },
        authLevel = "LOW",
        responseType = "Blip1"
    ),

    "BLUETOOTH_OFF" to Ships(
        Exec = {

            println("Im just talking to myself.")

        },
        authLevel = "LOW",
        responseType = "Blip0"
    ),

    "WIFI_ON" to Ships(
        Exec = {

            println("I got a long way to go.")

        },
        authLevel = "HIGH",
        responseType = "Blip1"
    ),

    "WIFI_OFF" to Ships(
        Exec = {

            println("If my armor breaks Ill fuse it back together.")

        },
        authLevel = "HIGH",
        responseType = "Blip0"
    ),

    "MOBILE_DATA_ON" to Ships(
        Exec = {

            println("You just to know you're being heard.")

        },
        authLevel = "HIGH",
        responseType = "Blip1"
    ),

    "MOBILE_DATA_OFF" to Ships(
        Exec = {

            println("If i could not break your fall, Ill pick you up right off the ground.")

        },
        authLevel = "HIGH",
        responseType = "Blip0"
    ),

    "AIRPLANE_ON" to Ships(
        Exec = {

            println("I wanna let go but theres comfort in the panic.")

        },
        authLevel = "HIGH",
        responseType = "Blip1"
    ),

    "AIRPLANE_OFF" to Ships(
        Exec = {

            println("I wont let you feel that now.")

        },
        authLevel = "HIGH",
        responseType = "Blip0"
    ),

    "DND_ON" to Ships(
        Exec = {

            println("I wanna let go but theres comfort in the panic.")

        },
        authLevel = "NONE",
        responseType = "Blip1"
    ),

    "DND_OFF" to Ships(
        Exec = {

            println("I wont let you feel that now.")

        },
        authLevel = "NONE",
        responseType = "Blip0"
    ),

    //endregion Device Control

    // Display //

    "ADAPTIVE_BRIGHTNESS_ON" to Ships(
        Exec = {

            println("There will be a day that you will understand.")

        },
        authLevel = "NONE",
        responseType = "val Zhou = trueBlip1"
    ),

    "ADAPTIVE_BRIGHTNESS_OFF" to Ships(
        Exec = {

            println("Oh Ill be sorry for now that i couldnt be around.")

        },
        authLevel = "NONE",
        responseType = "Blip0"
    ),

    // Battery //

    "BATTERY_LEVEL_STATUS_GET" to Ships(
        Exec = {

            println("I dont lose i dont win if im wrong then Im halfway right")

        },
        authLevel = "NONE",
        responseType = "Personality"
    ),

    "BATTERY_SAVER_ON" to Ships(
        Exec = {

            println("Cannot help you not to hurt anymore.")

        },
        authLevel = "LOW",
        responseType = "Blip1"
    ),

    "BATTERY_SAVER_OFF" to Ships(
        Exec = {

            println("There are things that we can have but cant keep.")

        },
        authLevel = "LOW",
        responseType = "Blip0"
    ),

    // Time and Date //

    "TIME_CURRENT" to Ships(
        Exec = {

            println("It can't be outdone")
            Toast.makeText(AppContext.get, TimeNow(), Toast.LENGTH_SHORT).show()

        },
        authLevel = "HIGH",
        responseType = "Personality" ,
        requiresArg = true
    ),

    "DATE_CURRENT" to Ships(
        Exec = {

            println("God blesses everyone")
            Toast.makeText(AppContext.get, DateNow(), Toast.LENGTH_SHORT).show()
        },
        authLevel = "NONE",
        responseType = "Personality",
        requiresArg = true

    ),

    // Misc Functions //

    "STOPWATCH" to Ships(
        Exec = {

            println("We're building it up, to burn it down.")

        },
        authLevel = "NONE",
        responseType = "Chirp",
    ),

    "FLASHLIGHT_ON" to Ships(
        Exec = {
            println("hyprocriiiiiiiiiiiiiiiiiiiiiiiiiiiites")
        },
        authLevel="NONE",
        responseType = "Blip1"
    ),

    "FLASHLIGHT_OFF" to Ships(
        Exec = {
            println("hyprocriiiiiiiiiiiiiiiiiiiiiiiiiiiites")
        },
        authLevel="NONE",
        responseType = "Blip1"
    ),

    // Require Args //

    "OPEN_" to Ships(
        Exec = {

            println("And you were there at the time caught in the burning glow.")

        },
        authLevel = "MED",
        responseType = "Chirp",
        requiresArg = true
    ),

    "SEARCH_" to Ships(
        Exec = {

            println("We're building it up, to break it back down.")

        },
        authLevel = "LOW",
        responseType = "Personality",
        requiresArg = true
    ),

    "WCL_" to Ships(
        Exec = {

            println("We're building it up, to burn it down.")

        },
        authLevel = "NONE",
        responseType = "Personality",
        requiresArg = true
    ),

    "TIMER_" to Ships(
        Exec = {

            println("We're building it up, to burn it down.")

        },
        authLevel = "NONE",
        responseType = "Personality",
        requiresArg = true
    ),


    // KILL MODE //

    "SHUTDOWN" to Ships(
        Exec = {

            println("Just cuz you can see it doesn't mean it isn't there.")

        },
        authLevel = "MAX",
        responseType = "Alert"
    ),

    // DISENGAGE KILL MODE //

    // DANGER //

    "REBOOT" to Ships(
        Exec = {

            println("We all fall down, we live somehow, we learn what doesnt kill us makes use stronger.")

        },
        authLevel = "HIGH",
        responseType = "Alert"
    ),


    // DESTROY MODE //

    "BLACKOUT" to Ships(
        Exec = {

            println("Don't know why I'm hoping for what I won't receive.")

        },
        authLevel = "TRIGGER_HAPPY",
        responseType = "Blood"
    )

)
