package com.example.MeetDinner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkReceiver extends BroadcastReceiver {

    boolean isWifiConn = false;
    boolean isMobileConn = false;
    ConnectivityReceiverListener listener;

    public NetworkReceiver(){}

    public NetworkReceiver(ConnectivityReceiverListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (null != networkInfo) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = true;
                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = true;
                }

                if (networkInfo.isConnected()) {
                    if (listener != null) {
                        listener.onNetworkStatusChanged(true);
                    }
                } else if (networkInfo.isConnectedOrConnecting()) {
                    //do nothing
                } else {
                    if (listener != null) {
                        listener.onNetworkStatusChanged(false);
                    }
                }
            }
        }
    }

    public interface ConnectivityReceiverListener {
        void onNetworkStatusChanged(Boolean isConnected);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
