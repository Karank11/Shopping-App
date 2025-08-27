package com.example.shoppingapp

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.example.shoppingapp.di.ApplicationComponent
import com.example.shoppingapp.di.DaggerApplicationComponent
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

class ShoppingApplication: Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this@ShoppingApplication)
    }

    override fun onCreate() {
        super.onCreate()

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.rc_defaults)

        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                Log.d("ShoppingApp", "Config params updated: $updated")
                Toast.makeText(this, "Fetch and activate succeeded",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Fetch failed",
                    Toast.LENGTH_SHORT).show()
            }
        }

        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                TODO("Not yet implemented")
            }
            override fun onError(error: FirebaseRemoteConfigException) {
                TODO("Not yet implemented")
            }
        })
    }
}