package com.aspera.space

import android.media.SoundPool

// personality / response random picker file
//coding on my phone btw...
//region IPI
//may have to remove bits of redundant in carrier file. IN CARRIER TO RESPONTYPE = RESPONSE TYPE DOR PARSMTERS
val Dean = Jacob(AppContext.get)
fun Inter(vessel: Ships) {
//oops dorgot this was a thing. make a property or attriubute in raxh ship/vessel with the response message

    try {

    if (vessel.responseType == "Alert") {
        println("Sound.Alerty ")
    }

    else if (vessel.responseType == "Personality") {
        println(vessel.responseMSG)
    }

    else if (vessel.responseType == "Chirp") {
        Dean.Dive("Chirp")
        println("I did it!")
    }

    else if (vessel.responseType == "Chime") {
        println("Sound.chimy")
    }

    else if (vessel.responseType == "Blip0") {
        println("Sound.Blippy0")
    }

    else if (vessel.responseType == "Blip1") {
        println("Sound.Blippy1")
    }

    else if (vessel.responseType == "Blood") {
        println("Sound.Bloody")
    }
    
    else {
        throw Exception ("Response not found/delivered properly.")
    }
      
    } catch (e: Exception) {
        println("ResponseType wasn't recognised, so an exception *QueryException was thrown.")

	}

}
  
//endregion IPI



