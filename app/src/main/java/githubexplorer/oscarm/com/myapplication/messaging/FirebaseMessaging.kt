package githubexplorer.oscarm.com.myapplication.messaging

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessaging: FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        Log.e("FUCK!!", p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        Log.e("FUCK!", p0.notification?.body ?: "to")
    }
}