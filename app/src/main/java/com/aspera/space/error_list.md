ERROR 1 - HOOK NOT FOUND.\
ERROR 2 - AUTHENTICATION LEVEL NOT FOUND/DEFINED.\
ERROR 3 - RESPONSE TYPE NOT FOUND/DEFINED.\
ERROR 4 - ACTION NOT AUTHENTICATED.\

SUBPROCESS ERRORS (MINOR ERRORS) :::

------| MEDIA SUBPROCESSES |------

MINOR 1 - Notification Listener Permission Required\
MINOR 2 - Unable to read current track

------| BLUETOOTH |------
MINOR 3 - Bluetooth Devices Permission Required\
MINOR 4 - No bluetooth action found/specified

------| WIFI |------
// flat means designed to trip under certain circumstances while still having successful result.\
FLAT 1 - Android SDK older than Android 10. Falling back to old wifi ctl panel.\
MINOR 5 - No wifi action found/specified
MINOR 6 - Unable to open wifi-settings. Error info shown in println dialogue.

------| I don't want to write anymore |------

MINOR 7 - System brightness not changed due to error in system settings write.
