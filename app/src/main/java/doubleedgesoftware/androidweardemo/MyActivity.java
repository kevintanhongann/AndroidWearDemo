package doubleedgesoftware.androidweardemo;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {

    final static String GROUP_KEY_SAMPLE = "group_key_sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, new Intent(this, MyActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("My notification")
                        .addAction(R.drawable.ic_launcher, "Reply", pendingIntent)
                        .setContentText("Hello World!");

        NotificationCompat.Builder mBuilder2 =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("My notification 2")
                        .addAction(R.drawable.ic_launcher, "Reply", pendingIntent)
                        .setContentText("Hello World!");

        // mId allows you to update the notification later on.
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);


        // Create a big text style for the second page
        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
        secondPageStyle.setBigContentTitle("Page 2")
                .bigText("A lot of text...");

        // Create second page notification
        Notification secondPageNotification =
                new NotificationCompat.Builder(this)
                        .setStyle(secondPageStyle)
                        .build();

        Notification summaryNotification = new WearableNotifications.Builder(mBuilder)
                .setGroup(GROUP_KEY_SAMPLE, WearableNotifications.GROUP_ORDER_SUMMARY)
                .build();

        // Create a WearablesNotification.Builder to add special functionality for wearables
        Notification notification =
                new WearableNotifications.Builder(mBuilder)
                        .setGroup(GROUP_KEY_SAMPLE)
                        .addPage(secondPageNotification)
                        .build();

        // Use the same group as the previous notification
        Notification notif2 = new WearableNotifications.Builder(mBuilder2)
                .setGroup(GROUP_KEY_SAMPLE)
                .build();

        notificationManagerCompat.notify(0, summaryNotification);
        notificationManagerCompat.notify(1, notification);
        notificationManagerCompat.notify(2, notif2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
