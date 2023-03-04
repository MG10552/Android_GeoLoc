package pjatk.s10552.geoloc;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

public class ProximityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        final String key = LocationManager.KEY_PROXIMITY_ENTERING;
        final Boolean entering = intent.getBooleanExtra(key, false);
        String data = intent.getStringExtra("data");
        if (entering) {
            SendNotification(context, data +" - Entering");
        } else {
            SendNotification(context, data +" - Leaving");
        }
    }
    public void SendNotification(Context context, String message){
        Log.i("NotificationTest", "I caught something ");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, context.getPackageManager().getLaunchIntentForPackage("pjatk.s10552.geoloc"), PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new Notification.Builder(context)
                .setContentTitle("Proximity Alert. ")
                .setContentText("Content: "+ message)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
