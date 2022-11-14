package com.cleverTapDemoAndroid

import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clevertap.android.sdk.CleverTapAPI
import java.util.*
import kotlin.collections.HashMap

class MainActivity2 : AppCompatActivity() {

    var cleverTapDefaultInstance: CleverTapAPI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)
        cleverTapDefaultInstance!!.enableDeviceNetworkInfoReporting(true)
        CleverTapAPI.setDebugLevel(3)

        if(cleverTapDefaultInstance != null){
            CleverTapAPI.createNotificationChannelGroup(applicationContext, "1234", "CleverTapPushAndroid")
            CleverTapAPI.createNotificationChannel(applicationContext, "CT4444", "CT-PushAndroid", "Test-NotificationsAndroid", NotificationManager.IMPORTANCE_MAX, "1234", true)
        }

        onUserLoginMethod()
    }

    private fun onUserLoginMethod() {

        val profileUpdate = HashMap<String, Any>()
        profileUpdate["Name"] = "Rameshwar Gupta" // String
        profileUpdate["Identity"] = 612339822032 // String or number
        profileUpdate["Email"] = "rameshwargct@gmail.com" // Email address of the user
        profileUpdate["Phone"] = "+919990999999" // Phone (with the country code, starting with +)
        profileUpdate["Gender"] = "M" // Can be either M or F
        profileUpdate["DOB"] = Date() // Date of Birth. Set the Date object to the appropriate value first
        profileUpdate["Photo"] = "www.foobar.com/image.jpeg" // URL to the Image

        profileUpdate["MSG-email"] = false // Disable email notifications
        profileUpdate["MSG-push"] = true // Enable push notifications
        profileUpdate["MSG-sms"] = false // Disable SMS notifications
        profileUpdate["MSG-dndPhone"] = true // Opt out phone
        profileUpdate["MSG-dndEmail"] = true // Opt out email
        profileUpdate["MyStuff"] = arrayListOf("bag", "shoes") //ArrayList of Strings
        profileUpdate["MyStuff"] = arrayOf("Jeans", "Perfume") //String Array
        cleverTapDefaultInstance?.pushProfile(profileUpdate)
    }


    fun sendEventsOnProfile() {

        val prodViewedAction = mapOf(
            "Product Name" to "Casio Chronograph Watch",
            "Category" to "Mens Accessories",
            "Price" to 59.99,
            "Date" to Date())
        cleverTapDefaultInstance?.pushEvent("Product viewed", prodViewedAction)
    }
}