package com.aspera.space
import android.content.Context
import android.media.SoundPool

class Jacob(context: Context) {
    private val IGYEIH = SoundPool.Builder()
        .setMaxStreams(2)
        .build()

    private val activation = IGYEIH.load(
        context, R.raw.testaudio, 1
    )

    private val acknowledge = IGYEIH.load(
        context, R.raw.testaudio, 1
    )

    fun Dive(responseType: String) {


        if (responseType == "Alert") {
            println("Sound.Alerty ")
        } else if (responseType == "Personality") {

        } else if (responseType == "Chirp") {
            IGYEIH.play(activation, 1f, 1f, 1, 0, 1f)
        } else if (responseType == "Chime") {
            println("Sound.chimy")
        } else if (responseType == "Blip0") {
            println("Sound.Blippy0")
        } else if (responseType == "Blip1") {
            println("Sound.Blippy1")
        } else if (responseType == "Blood") {
            println("Sound.Bloody")
        }
    }
}

