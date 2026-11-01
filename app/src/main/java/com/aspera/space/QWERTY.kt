package com.aspera.space

// personality / response random picker file
//coding on my phone btw...
//region IPI
//may have to remove bits of redundant in carrier file. IN CARRIER TO RESPONTYPE = RESPONSE TYPE DOR PARSMTERS
fun Inter(vessel: Ships) {
//oops dorgot this was a thing. make a property or attriubute in raxh ship/vessel with the response message
    if (vessel.responseType == "Alert") {
        println(" Sound.Alerty ")
    }

    else if (vessel.responseType == "Personality") {
        println(vessel.responseMSG)
    }

    else if (vessel.responseType == "Chirp") {
        println("Sound.chirpy")
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
        throw QueryException ("Response not found/delivered properly.")
    }
      
    } catch (e: SecurityException) {
      println("It seems permissions are in order. idk what yet.")
      startActivity(Intent(Settings.something.pleasesendhelp)
    } catch (e: QueryException) {
        println("ResponseType wasn't recognised, so QueryException was thrown.")
    }
    
    
  }
  
//endregion IPI



