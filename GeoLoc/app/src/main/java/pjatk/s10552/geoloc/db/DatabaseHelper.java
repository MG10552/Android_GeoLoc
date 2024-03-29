package pjatk.s10552.geoloc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pjatk.s10552.geoloc.entities.Location;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String NAME = "locations";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE LOCATIONS (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE, latitude REAL, longitude REAL, description TEXT, proximity REAL)";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS LOCATIONS";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM LOCATIONS";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_LATITUDE = "LATITUDE";
    private static final String COLUMN_LONGITUDE = "LONGITUDE";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_PROXIMITY = "PROXIMITY";
    private static final String WHERE_NAME_STRING = "NAME=?";


    public DatabaseHelper(Context context) {
        super(context, NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }


    public void insert(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, location.getName());
        contentValues.put(COLUMN_LATITUDE, location.getLatitude());
        contentValues.put(COLUMN_LONGITUDE, location.getLongitude());
        contentValues.put(COLUMN_DESCRIPTION, location.getDescription());
        contentValues.put(COLUMN_PROXIMITY, location.getProximity());

        db.insert(NAME, null, contentValues);
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery(SELECT_ALL_QUERY, null);
        return data;
    }

    public Location getByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Location location = null;

        Cursor data = db.rawQuery("SELECT * FROM LOCATIONS WHERE NAME = \"" + name +"\"", null);
        if (data.moveToFirst()) {
            location = new Location();
            location.setName(data.getString(1));
            location.setDescription(data.getString(4));
            location.setLatitude(data.getDouble(2));
            location.setLongitude(data.getDouble(3));
            location.setProximity(data.getDouble(5));
        }
        data.close();

        return location;
    }
}