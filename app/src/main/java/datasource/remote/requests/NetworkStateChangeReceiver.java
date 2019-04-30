package datasource.remote.requests;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class NetworkStateChangeReceiver extends BroadcastReceiver {
    private static HashMap<String, ConnectivityReceiverListener> connectivityReceiverListeners;


    public static void setConnectivityReceiverListener(String tag, ConnectivityReceiverListener connectivityReceiverListener) {
        if (connectivityReceiverListeners == null) {
            connectivityReceiverListeners = new HashMap<>();
        }
        connectivityReceiverListeners.put(tag, connectivityReceiverListener);
    }

    public static void removeConnectivityReceiverListener(String tag) {
        if (connectivityReceiverListeners != null) {
            connectivityReceiverListeners.remove(tag);
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isOPen = isOpenWifi(context);
        if (connectivityReceiverListeners != null) {

            for (Iterator<Map.Entry<String, ConnectivityReceiverListener>> it = connectivityReceiverListeners.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, ConnectivityReceiverListener> entry = it.next();
                if (entry.getValue() != null) {
                    entry.getValue().isOPenWifi(isOPen);
                } else {
                    it.remove();
                }
            }

        }
    }

    public static boolean isOpenWifi(Context context) {

        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
         // "Have Wifi Connection"
            return true;
        } else {
         // "Don't have Wifi Connection"
            return false;
        }
    }

    public static boolean isOpenMobileData(Context context) {

        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_MOBILE ) {
        // "Have Mobile Data Connection"
            return true;
        } else {
        //  "Don't have Wifi Connection"
            return false;
        }
    }

    public interface ConnectivityReceiverListener {
        void isOPenWifi(boolean isConnected);
    }
}