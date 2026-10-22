package com.aspera.space

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import android.os.SystemClock // TROUBLE

object Adrenaline {
    init {
        val start = SystemClock.elapsedRealtimeNanos() // TROUBLE
        System.loadLibrary("aspera_native")
        Log.d( // TROUBLE
            "AsperaTiming", // TROUBLE
            "Library load: ${(SystemClock.elapsedRealtimeNanos() - start) / 1_000_000} ms" // TROUBLE
        ) // TROUBLE
    }

    private external fun Ignite(modelPath: String): Boolean
    // private external fun Purge()
    private external fun Resolve(query: String): String

    private var modelFilePath: String? = null

    // 1. Called ONCE in MainActivity.onCreate(this) to unpack model.bin from assets
    fun setup(context: Context, modelFileName: String = "starlight.ftz") {
        val setupStart = SystemClock.elapsedRealtimeNanos() // TROUBLE
        try {
            val file = File(context.filesDir, modelFileName)
            if (!file.exists()) {
                val copyStart = SystemClock.elapsedRealtimeNanos() // TROUBLE
                context.assets.open(modelFileName).use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
                Log.d( // TROUBLE
                    "AsperaTiming", // TROUBLE
                    "Model extraction: ${(SystemClock.elapsedRealtimeNanos() - copyStart) / 1_000_000} ms" // TROUBLE
                ) // TROUBLE
            } // TROUBLE
            modelFilePath = file.absolutePath
        } catch (e: Exception) {
            Log.e("Adrenaline", "Setup failed", e)
        }
        Log.d( // TROUBLE
            "AsperaTiming", // TROUBLE
            "Total setup: ${(SystemClock.elapsedRealtimeNanos() - setupStart) / 1_000_000} ms" // TROUBLE
        ) // TROUBLE
    }

    // 2. Autonomous intent resolver: Ignite -> Predict -> Purge
    fun processQuery(input: String): String {
        val path = modelFilePath ?: return "UNKNOWN"

        val igniteStart = SystemClock.elapsedRealtimeNanos() // TROUBLE
        val igniteSuccess = Ignite(path) // TROUBLE
        Log.d( // TROUBLE
            "AsperaTiming", // TROUBLE
            "Ignite: ${(SystemClock.elapsedRealtimeNanos() - igniteStart) / 1_000_000} ms; success=$igniteSuccess" // TROUBLE
        ) // TROUBLE

        val resolveStart = SystemClock.elapsedRealtimeNanos() // TROUBLE
        val rawResult = Resolve(input)
        Log.d( // TROUBLE
            "AsperaTiming", // TROUBLE
            "Resolve: ${(SystemClock.elapsedRealtimeNanos() - resolveStart) / 1_000_000} ms" // TROUBLE
        ) // TROUBLE

        // Purge from RAM instantly (0 idle memory)
        // Purge()

        // Clean up label e.g. "MEDIA_PLAY"
        // val result = rawResult.split(" ").firstOrNull()?.removePrefix("__label__") ?: "UNKNOWN" // TROUBLE
        val result = rawResult.substringBefore(" ").removePrefix("__label__").ifEmpty { "UNKNOWN" } // TROUBLE
        Log.d( // TROUBLE
            "AsperaTiming", // TROUBLE
            "Total processQuery: ${(SystemClock.elapsedRealtimeNanos() - igniteStart) / 1_000_000} ms" // TROUBLE
        ) // TROUBLE
        return result // TROUBLE
    }
}