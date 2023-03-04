package pjatk.s10552.geoloc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button mapsActivityButton = (Button) findViewById(R.id.maps_activity_button);
        final Button mapsHistoryActivityButton = (Button) findViewById(R.id.list_view_button);

        final Intent mapsActivityIntent = new Intent(this, MapsActivity.class);
        final Intent mapsHistoryActivityIntent = new Intent(this, MapsHistoryActivity.class);


        mapsHistoryActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mapsHistoryActivityIntent);
            }
        });

        mapsActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mapsActivityIntent);
            }
        });

    }
}
