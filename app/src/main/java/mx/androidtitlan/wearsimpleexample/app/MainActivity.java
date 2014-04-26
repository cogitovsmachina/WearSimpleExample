package mx.androidtitlan.wearsimpleexample.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.preview.support.wearable.notifications.*;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;

import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    private static final String EXTRA_EVENT_ID = "event_id";
    private boolean eventId;
    private String location ="19.412252, -99.180628";
    private PendingIntent viewPendingIntent;
    private CharSequence eventTitle = "Titulo del evento";
    private CharSequence eventLocation = "Centraal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button notificationButton = (Button)findViewById(R.id.notificationButton);
        notificationButton.setOnClickListener(this);
        Button actionButton = (Button) findViewById(R.id.actionButton);
        actionButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.notificationButton){
            int notificationId = 001;
            // Build intent for notification content
            Intent viewIntent = new Intent(this, ViewEventActivity.class);
            viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
            PendingIntent viewPendingIntent =
                    PendingIntent.getActivity(this, 0, viewIntent, 0);

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle(eventTitle)
                            .setContentText("Contenido contenido contenido")
                            .setContentIntent(viewPendingIntent);
            // Get an instance of the NotificationManager service
            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(this);

            // Build the notification and issues it with notification manager.
            notificationManager.notify(notificationId, notificationBuilder.build());
        }
        if(v.getId()==R.id.actionButton){
            int notificationId = 002;
            Intent mapIntent = new Intent(Intent.ACTION_VIEW);
            Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
            mapIntent.setData(geoUri);
            PendingIntent mapPendingIntent =
                    PendingIntent.getActivity(this, 0, mapIntent, 0);

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle(eventTitle)
                            .setContentText(eventLocation)
                            .setContentIntent(viewPendingIntent)
                            .addAction(R.drawable.ic_launcher,
                                    getString(R.string.map), mapPendingIntent);
            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(this);
            notificationManager.notify(notificationId, notificationBuilder.build());
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
