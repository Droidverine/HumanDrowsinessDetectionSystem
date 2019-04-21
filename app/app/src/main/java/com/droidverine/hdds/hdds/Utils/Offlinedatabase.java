package com.droidverine.hdds.hdds.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.droidverine.hdds.hdds.models.Messages;

import java.util.ArrayList;
import java.util.HashMap;

import static com.droidverine.hdds.hdds.Utils.DetailsManager.DOCS_TABLE;


/**
 * Created by DELL on 13-12-2017.
 */

public class Offlinedatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HDDS.db";
    public static final String MESSAGES_TABLE_NAME = "MessagesTable";
    public static final String MESSAGES_COLUMN_ID = "msgid";
    //patient
    public static final String PATIENT_ID="patientid";
    public static final String PATIENT_NAME = "patientname";
    public static final String PATIENT_EMAIL = "patientemail";
    public static final String PATIENT_TABLE = "patienttable";


    //


    public static final String DOCS_ID="docsid";
    public static final String DOCS_COLUMN_TITLE = "docstitle";
    public static final String DOCS_COLUMN_IMG = "docsimg";

    public static final String SPONSORS_ID="sponsorid";
    public static final String SCHEDULE_ID="scheduleid";

    public static final String MESSAGES_COLUMN_TITLE = "msghead";
    public static final String MESSAGES_COLUMN_BODY = "msgbody";
    public static final String MESSAGES_UID = "uid";


    private static final String TRUNCATE_MESSAGES = "DELETE FROM " + MESSAGES_TABLE_NAME;
    private static final String TRUNCATE_SPONSORS = "DELETE FROM " + DetailsManager.SPONSOR_TABLE;
    private static final String TRUNCATE_SCHEDULe= "DELETE FROM " + DetailsManager.Schedule_TABLE;
    private static final String TRUNCATE_DOCS = "DELETE FROM " + DOCS_TABLE;

    private static final String DELETE_NOTIF_TABLE = "DROP TABLE IF EXISTS " + MESSAGES_TABLE_NAME;

    private static final String CREATE_NOTIF_TABLE = "CREATE TABLE IF NOT EXISTS " + MESSAGES_TABLE_NAME + " ( " +
            MESSAGES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + MESSAGES_UID +" VARCJAR DEFAULT NULL ,"+
    MESSAGES_COLUMN_TITLE + " VARCHAR DEFAULT NULL, " +
            MESSAGES_COLUMN_BODY +" , "+DetailsManager.MESSAGES_COLUMN_TIME +" VARCHAR DEFAULT NULL );";


   /* private static final String CREATE_NOTIF_TABLE = "CREATE TABLE IF NOT EXISTS " + MESSAGES_TABLE_NAME + " ( " +
            MESSAGES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + MESSAGES_COLUMN_TITLE + " VARCHAR DEFAULT NULL, " +
            MESSAGES_COLUMN_BODY +" , "+DetailsManager.MESSAGES_COLUMN_TIME +" VARCHAR DEFAULT NULL );";
   */
    private static final String DELETE_SPONS_TABLE = "DROP TABLE IF EXISTS " + DetailsManager.SPONSOR_TABLE;
    private static final String CREATE_SPONS_TABLE = "CREATE TABLE IF NOT EXISTS " + DetailsManager.SPONSOR_TABLE + " ( " +
            SPONSORS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DetailsManager.SPONSOR_TITLE + " VARCHAR DEFAULT NULL, " +
            DetailsManager.SPONSOR_DETAILS +" VARCHAR DEFAULT NULL, "+ DetailsManager.SPONSOR_IMG + " VARCHAR DEFAULT NULL" + " , "+DetailsManager.SPONSOR_LINK +" VARCHAR DEFAULT NULL );";

    private static final String DELETE_SCHEDULE_TABLE = "DROP TABLE IF EXISTS " + DetailsManager.SPONSOR_TABLE;

    private static final String CREATE_SCHEDULE_TABLE = "CREATE TABLE IF NOT EXISTS " + DetailsManager.Schedule_TABLE + " ( " +
            SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DetailsManager.Schedule_COLUMN_TITLE + " VARCHAR DEFAULT NULL, " +
            DetailsManager.Schedule_COLUMN_TIME +" VARCHAR DEFAULT NULL );";

    private static final String DELETE_DOCS_TABLE = "DROP TABLE IF EXISTS " + DOCS_TABLE;
    private static final String CREATE_DOCS_TABLE = "CREATE TABLE IF NOT EXISTS " + DOCS_TABLE + " ( " +
            DOCS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DetailsManager.DOCS_TITLE + " VARCHAR DEFAULT NULL, " +
            DetailsManager.DOCS_IMG +" VARCHAR DEFAULT NULL );";


    //patient create and truncate
    private static final String CREATE_PATIENT_TABLE = "CREATE TABLE IF NOT EXISTS " + PATIENT_TABLE + " ( " +
            PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ DetailsManager.PATIENT_UID + " VARCHAR DEFAULT NULL, " + DetailsManager.PATIENT_NAME + " VARCHAR DEFAULT NULL, " +
            DetailsManager.PATIENT_EMAIL +" VARCHAR DEFAULT NULL );";
//Prescription
private static final String CREATE_PRESCRIPTIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + DetailsManager.PRESCRIPTIONS_TABLE + " ( " +
        DetailsManager.PRESCRIPTIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ DetailsManager.PRESCRIPTIONS_TITLE + " VARCHAR DEFAULT NULL, "+ DetailsManager.PRESCRIPTIONS_MEDICINE + " VARCHAR DEFAULT NULL, "
        +DetailsManager.PRESCRIPTIONS_TIME + " VARCHAR DEFAULT NULL, " +
        DetailsManager.PATIENT_NAME + " VARCHAR DEFAULT NULL, " +
        DetailsManager.PRESCRIPTIONS_IMG +" VARCHAR DEFAULT NULL );";
    private static final String DELETE_PRESCRIPTIONS_TABLE = "DELETE FROM " + DetailsManager.PRESCRIPTIONS_TABLE;

    public Offlinedatabase(Context context) {
        super(context,DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PRESCRIPTIONS_TABLE);
        sqLiteDatabase.execSQL(CREATE_NOTIF_TABLE);
        sqLiteDatabase.execSQL(CREATE_SPONS_TABLE);
        sqLiteDatabase.execSQL(CREATE_DOCS_TABLE);
        sqLiteDatabase.execSQL(CREATE_PATIENT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public void insertNotifications(HashMap<String, String> map) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MESSAGES_UID,map.get(MESSAGES_UID));
        values.put(MESSAGES_COLUMN_TITLE, map.get(DetailsManager.MESSAGES_COLUMN_TITLE));
        values.put(MESSAGES_COLUMN_BODY, map.get(DetailsManager.MESSAGES_COLUMN_BODY));
        values.put(DetailsManager.MESSAGES_COLUMN_TIME,map.get(DetailsManager.MESSAGES_COLUMN_TIME));

        db.insert(MESSAGES_TABLE_NAME, null, values);
        Log.d("inserted","data");

    }
    public void truncateNotifications()
    {
        SQLiteDatabase database=getWritableDatabase();
        Log.d("table truncated","dropped");
        database.execSQL(TRUNCATE_MESSAGES);
    }
    public ArrayList<Messages> getAllNotifications()
    {
        ArrayList<Messages> messagesList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(MESSAGES_TABLE_NAME, new String[]{"*"}, null, null, null, null, null);
        //Cursorr cursoror=db.rawQuery("SELECT * FROM "+Constants.NOTIFICATIONS_TABLE_NAME+" ORDER BY "+Constants.NOTIFICATION_TIMESTAMP,null);
        //for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious())
        {
            Messages notification_data = new Messages();
            notification_data.setUid(cursor.getString(cursor.getColumnIndex(MESSAGES_UID)));
            notification_data.setMsghead(cursor.getString(cursor.getColumnIndex(MESSAGES_COLUMN_TITLE)));
            notification_data.setMsgtext(cursor.getString(cursor.getColumnIndex(MESSAGES_COLUMN_BODY)));
            Long l=Long.parseLong(cursor.getString(cursor.getColumnIndex(DetailsManager.MESSAGES_COLUMN_TIME)));
            notification_data.setMsgtime(l);
            messagesList.add(notification_data);
        }
        cursor.close();
        db.close();
        return messagesList;
    }

    //DOCS STARTS HERE

    public void insertDocs(HashMap<String, String> map) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DOCS_COLUMN_TITLE, map.get(DetailsManager.DOCS_COLUMN_TITLE));
        values.put(DOCS_COLUMN_IMG, map.get(DetailsManager.DOCS_COLUMN_IMG));
        db.insert(DOCS_TABLE, null, values);
        Log.d("DOCSDATA","inserted");

    }



}
