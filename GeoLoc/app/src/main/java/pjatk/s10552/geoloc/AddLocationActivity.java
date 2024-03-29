package pjatk.s10552.geoloc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pjatk.s10552.geoloc.db.DatabaseHelper;
import pjatk.s10552.geoloc.entities.Location;

public class AddLocationActivity extends AppCompatActivity {

    private DatabaseHelper db;
    public static final String FIELDS_REQUIRED_ERROR = "In order to proceed all fields must be filled.";
    public static final String NAME_TAKEN_ERROR = "Place with such name already exists.";
    public static final String SAVED = "Successfully added new place.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        db = new DatabaseHelper(getApplicationContext());

        final EditText addLocationActivityName = (EditText) findViewById(R.id.add_location_activity_name);
        final EditText addLocationActivityDescription = (EditText) findViewById(R.id.add_location_activity_description);
        final EditText addLocationProximityRadius = (EditText) findViewById(R.id.add_location_proximity_radius);
        final Button addLocationActivityCancelButton = (Button) findViewById(R.id.add_location_activity_cancel_button);
        final Button addLocationActivitySaveButton = (Button) findViewById(R.id.add_location_activity_save_button);


        final double latitude = getIntent().getDoubleExtra(getString(R.string.latitude_string),0);
        final double longitude = getIntent().getDoubleExtra(getString(R.string.longitude_string),0);

        final Intent mainActivityIntent = new Intent(this, MainActivity.class);

        addLocationActivityCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivityIntent);
            }
        });

        addLocationActivitySaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = addLocationActivityName.getText().toString();
                String description = addLocationActivityDescription.getText().toString();
                Double proximity = Double.parseDouble(addLocationProximityRadius.getText().toString());

                if(name.equals(getString(R.string.empty_string))|| description.equals("")) {
                    Toast.makeText(AddLocationActivity.this, FIELDS_REQUIRED_ERROR, Toast.LENGTH_SHORT).show();
                } else {
                    if(db.getByName(name) != null){
                        Toast.makeText(AddLocationActivity.this, NAME_TAKEN_ERROR, Toast.LENGTH_SHORT).show();
                    } else {

                        Location location = new Location();
                        location.setLatitude(latitude);
                        location.setLongitude(longitude);
                        location.setName(name);
                        location.setDescription(description);
                        location.setProximity(proximity);

                        db.insert(location);

                        startActivity(mainActivityIntent);
                        Toast.makeText(AddLocationActivity.this, SAVED, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}