package io.maido.intercom

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.intercom.android.sdk.push.IntercomPushClient

class PushMessageReceiver : FirebaseMessagingService() {

  /**
   * Called when message is received.
   *
   * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
   */
  // [START receive_message]
  override fun onMessageReceived(remoteMessage: RemoteMessage) {
    val message = remoteMessage.data

    if (intercomPushClient.isIntercomPush(message)) {
      Log.d(TAG, "Intercom message received")
      intercomPushClient.handlePush(application, message)
    } else {
      Log.d(TAG, "Push message received, not for Intercom")
    }
  }

    private val intercomPushClient = IntercomPushClient()


    companion object {
        private const val TAG = "PushInterceptReceiver"
    }
}
