package com.aspera.space
suspend fun main() {
    while(true) {

        print("Matthew@Aspera_Interface >")
        val shipment = readln()
        if (shipment.lowercase() == "exit") break
        Fly(shipment.trim())
        //no exit for now oh well

    }

}