package com.example.android.wearable.geofencing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofenceReceiver extends BroadcastReceiver {
        Context context;

        @Override
        public void onReceive(Context context, Intent intent) {
            this.context = context;

            GeofencingEvent geoFenceEvent = GeofencingEvent.fromIntent(intent);
            if (geoFenceEvent.hasError()) {
                int errorCode = geoFenceEvent.getErrorCode();
                //showToast(MainActivity.this, "Location Services error: " + errorCode);
                //Log.e(TAG, "Location Services error: " + errorCode);
            } else {

                int transitionType = geoFenceEvent.getGeofenceTransition();
                if (Geofence.GEOFENCE_TRANSITION_ENTER == transitionType) {
                    //showToast(MainActivity.this, R.string.entering_geofence);
                    Intent loginIntent = new Intent("ACTION_LOGIN");
                    loginIntent.putExtra("type", "login");
                    LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(loginIntent);
                } else if (Geofence.GEOFENCE_TRANSITION_EXIT == transitionType) {
                    //showToast(MainActivity.this, R.string.exiting_geofence);
                    Intent loginIntent = new Intent("ACTION_LOGIN");
                    loginIntent.putExtra("type", "logout");
                    LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(loginIntent);
                }
            }

        }
    }