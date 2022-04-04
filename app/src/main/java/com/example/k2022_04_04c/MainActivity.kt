package com.example.k2022_04_04c

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.ipsec.ike.TunnelModeChildSessionParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.example.k2022_04_04c.Services.MediaServices

lateinit var mediaServices: MediaServices
var bound: Boolean = false

class MainActivity : AppCompatActivity() {

    lateinit var radioToggle: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioToggle = findViewById(R.id.radio_toggle_button)
        radioToggle.setOnClickListener{
            var value = mediaServices.getInt()
            mediaServices.radioOn()
            Toast.makeText(applicationContext,"Hello: $value", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()

        val intent: Intent = Intent(this, MediaServices::class.java)
        intent.putExtra("Greeting","Hello world!")
        bindService(intent, connection, BIND_AUTO_CREATE)
        bound = true
    }

    override fun onStop() {
        super.onStop()

        unbindService(connection)
        bound = false
    }

    private val connection: ServiceConnection = object: ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            val binder: MediaServices.LocalBinder = service as MediaServices.LocalBinder
            mediaServices = binder.getInstance()
            bound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
           bound = false
        }

    }


}