package com.example.k2022_04_04c.Services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.k2022_03_08_rv.model.MyMediaPlayer
import com.example.k2022_03_08_rv.model.RadioStations

var value: Int = 0

class MediaServices: Service() {

    val radioStations: RadioStations = RadioStations()
    val myMediaPlayer: MyMediaPlayer = MyMediaPlayer()

    var binder = LocalBinder()

    override fun onBind(sevice: Intent?): IBinder? {
        return binder
    }

    fun getInt(): Int {
        value = value.plus(1)
        return value
    }

    fun radioOn() {
        val link: String = "http://stream.whus.org:8000/whusfm"
        myMediaPlayer.setUpRadio(link)
        myMediaPlayer.prepareAndPlayStation(link)
    }

    inner class LocalBinder : Binder() {
        fun getInstance() : MediaServices = this@MediaServices
    }
}