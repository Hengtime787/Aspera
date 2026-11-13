package com.aspera.space

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import android.os.SystemClock

object Mask {
    init {
        try {
            System.loadLibrary("aspera_native")
        } catch (e: UnsatisfiedLinkError) {
            Log.e("Mask", "Failed to load aspera_native library.", e)
        }
    }

    private external fun Ignite(modelPath: String): Boolean
    private external fun Purge()
    private external fun Resolve(query: String): String

    private var modelFilePath: String? = null

    fun setup(context: Context, modelFileName: String = "asperav6.model") {
        val start = SystemClock.elapsedRealtimeNanos()
        try {
            val file = File(context.filesDir, modelFileName)
            if (!file.exists()) {
                context.assets.open(modelFileName).use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
                Log.d("MaskTiming", "CRF Model extracted in ${(SystemClock.elapsedRealtimeNanos() - start) / 1_000_000} ms.")
            }
            modelFilePath = file.absolutePath
        } catch (e: Exception) {
            Log.e("Mask", "Setup failed", e)
        }
    }

    fun processQuery(input: String): String {
        val path = modelFilePath ?: return "UNKNOWN"

        val igniteSuccess = Ignite(path)
        if (!igniteSuccess) {
            Log.e("Mask", "Failed to ignite CRF model")
            return "IGNITE_FAILED"
        }

        val start = SystemClock.elapsedRealtimeNanos()
        val result = Resolve(input)
        Log.d("MaskTiming", "CRF Resolve: ${(SystemClock.elapsedRealtimeNanos() - start) / 1_000_000} ms")

        // Optional: Call Purge() if you want to free model memory immediately
        // Purge()

        return result
    }
}
