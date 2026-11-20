package com.example.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.math.exp
import kotlin.math.sin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DevotionalAudioPlayer {

    suspend fun playTempleBell() = withContext(Dispatchers.Default) {
        try {
            val sampleRate = 44100
            val durationSeconds = 2.4
            val numSamples = (durationSeconds * sampleRate).toInt()
            val buffer = ShortArray(numSamples)

            val baseFreq = 880.0 // Pitch of consecrated brass temple bell
            for (i in 0 until numSamples) {
                val t = i.toDouble() / sampleRate
                val decay = exp(-2.3 * t)

                // Harmonics creating authentic rich brass resonance
                val s1 = sin(2.0 * Math.PI * baseFreq * t)
                val s2 = 0.55 * sin(2.0 * Math.PI * baseFreq * 1.58 * t) * exp(-2.8 * t)
                val s3 = 0.35 * sin(2.0 * Math.PI * baseFreq * 2.15 * t) * exp(-3.2 * t)
                val s4 = 0.20 * sin(2.0 * Math.PI * baseFreq * 2.95 * t) * exp(-4.0 * t)
                val s5 = 0.12 * sin(2.0 * Math.PI * baseFreq * 4.10 * t) * exp(-5.0 * t)

                val sample = ((s1 + s2 + s3 + s4 + s5) * decay * 0.40 * Short.MAX_VALUE).toInt()
                buffer[i] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            val track = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(buffer.size * 2)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            track.write(buffer, 0, buffer.size)
            track.play()
        } catch (_: Exception) {
            // AudioTrack fallback
        }
    }

    suspend fun playShankh() = withContext(Dispatchers.Default) {
        try {
            val sampleRate = 44100
            val durationSeconds = 3.2
            val numSamples = (durationSeconds * sampleRate).toInt()
            val buffer = ShortArray(numSamples)

            val baseFreq = 230.0
            for (i in 0 until numSamples) {
                val t = i.toDouble() / sampleRate
                // Attack & Release envelope of sacred conch
                val attack = (t / 0.7).coerceAtMost(1.0)
                val release = ((durationSeconds - t) / 0.8).coerceIn(0.0, 1.0)
                val envelope = attack * release

                // Natural subtle vibrato and overtone harmonics of Shankh
                val vibrato = 7.0 * sin(2.0 * Math.PI * 4.0 * t)
                val freq = baseFreq + vibrato
                val s1 = sin(2.0 * Math.PI * freq * t)
                val s2 = 0.65 * sin(2.0 * Math.PI * (freq * 2.0) * t)
                val s3 = 0.35 * sin(2.0 * Math.PI * (freq * 3.0) * t)
                val s4 = 0.15 * sin(2.0 * Math.PI * (freq * 4.0) * t)

                val sample = ((s1 + s2 + s3 + s4) * envelope * 0.38 * Short.MAX_VALUE).toInt()
                buffer[i] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            val track = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(buffer.size * 2)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            track.write(buffer, 0, buffer.size)
            track.play()
        } catch (_: Exception) {
            // AudioTrack fallback
        }
    }

    suspend fun playChantTone() = withContext(Dispatchers.Default) {
        try {
            val sampleRate = 44100
            val durationSeconds = 1.8
            val numSamples = (durationSeconds * sampleRate).toInt()
            val buffer = ShortArray(numSamples)

            val baseFreq = 136.1 // Sacred OM frequency (136.1 Hz, cosmic octave)
            for (i in 0 until numSamples) {
                val t = i.toDouble() / sampleRate
                val envelope = sin(Math.PI * (t / durationSeconds))
                val s1 = sin(2.0 * Math.PI * baseFreq * t)
                val s2 = 0.5 * sin(2.0 * Math.PI * (baseFreq * 2.0) * t)
                val s3 = 0.25 * sin(2.0 * Math.PI * (baseFreq * 3.0) * t)
                val sample = ((s1 + s2 + s3) * envelope * 0.35 * Short.MAX_VALUE).toInt()
                buffer[i] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            val track = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(buffer.size * 2)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            track.write(buffer, 0, buffer.size)
            track.play()
        } catch (_: Exception) {
            // AudioTrack fallback
        }
    }
}
