package com.aspera.space
//Hey! This is the keyword intent model, not to be used for the final, just the v1 prototype.

fun Rkesolve(request: String): String {

    /*  if ("time now" in request) {
          return "TIME_CURRENT"
      }
      else {return "ERROR"}*/

    return when {

        // Media //

        "play" in request -> "MEDIA_PLAY"
        "pause" in request -> "MEDIA_PAUSE"
        "previous" in request -> "MEDIA_PREVIOUS"
        "next" in request -> "MEDIA_NEXT"
        "now playing" in request -> "MEDIA_GET"

        "mute" in request -> "MUTE"
        "unmute" in request -> "MUTE_OFF"

        // Module Control //

        "bluetooth on" in request -> "BLUETOOTH_ON"
        "bluetooth off" in request -> "BLUETOOTH_OFF"

        "wifi on" in request -> "WIFI_ON"
        "wifi off" in request -> "WIFI_OFF"

        "mobile data on" in request -> "MOBILE_DATA_ON"
        "mobile data off" in request -> "MOBILE_DATA_OFF"

        "airplane mode on" in request -> "AIRPLANE_ON"
        "airplane mode off" in request -> "AIRPLANE_OFF"

        "turn on do not disturb" in request -> "DND_ON"
        "turn off do not disturb" in request -> "DND_OFF"

        "adaptive brightness on" in request -> "ADAPTIVE_BRIGHTNESS_ON"
        "adaptive brightness off" in request -> "ADAPTIVE_BRIGHTNESS_OFF"

        // Battery //

        "battery level" in request -> "BATTERY_LEVEL_STATUS_GET"
        "battery saver on" in request -> "BATTERY_SAVER_ON"
        "battery saver off" in request -> "BATTERY_SAVER_OFF"

        // Time & Date //

        "time now" in request -> "TIME_CURRENT"
        "date today" in request -> "DATE_CURRENT"

        // Argument Hooks will not be shown for now right now at least : //

            //OPEN_
            //SEARCH_
            //WCL_

        // KILL MODE -- WILL NOT BE IMPLEMENTED ON KEYWORD BASIS OR PROTOTYPE MOST LIKELY. //

            //SHUTDOWN
            //REBOOT
            //#{$+%^&_*)&>@|=+*

        else -> {
            println("glass glass glass glass glass glass")
            println("ERROR 1")
            println("HOOK NOT FOUND")
            // rmbr last ln in this blk is returned bc of above
             "NOT_FOUND" // NOT FOUND has not been implemented.
        }
    }

}

