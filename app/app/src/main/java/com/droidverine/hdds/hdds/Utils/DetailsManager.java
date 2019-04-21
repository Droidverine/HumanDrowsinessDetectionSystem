package com.droidverine.hdds.hdds.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by DELL on 10-11-2017.
 */

public class DetailsManager {
    //Doctor
    public static final String PATIENT_UID = "patientuid";
    public static final String PATIENT_ID="patientid";
    public static final String PATIENT_NAME = "patientname";
    public static final String PATIENT_EMAIL = "patientemail";
    public static final String PATIENT_TABLE = "patienttable";
 //Prescription
    public static final String PRESCRIPTIONS_ID="prescriptionid";
    public static final String PRESCRIPTIONS_TITLE="prescriptiontitle";
    public static final String PRESCRIPTIONS_IMG="prescriptionimg";
    public static final String PRESCRIPTIONS_TIME = "prescriptiontime";
    public static final String PRESCRIPTIONS_MEDICINE = "prescriptionmedicine";

    public static final String PRESCRIPTIONS_TABLE = "prescriptiontable";

    //
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "name";
    private static final String FOOD_CODE="foodcode";
    public static final String SPONSOR_TABLE="SponsorsTable";

    public static final String SPONSOR_TITLE="sponsortitle";
    public static final String SPONSOR_DETAILS="sponsordetails";
    public static final String SPONSOR_LINK="sponsorlink";
    public static final String SPONSOR_IMG="sponsorimg";
    //NEW ADDED
    public static final String DOCS_TABLE="DocsTable";
    public static final String DOCS_TITLE="docstitle";
    public static final String DOCS_IMG="docsimg";
    public static final String DOCS_COLUMN_TITLE = "docstitle";
    public static final String DOCS_COLUMN_IMG = "docsimg";
    //ENDS HERE

    public static final String Schedule_TABLE = "Schduletable";
    public static final String Schedule_COLUMN_TITLE = "scheduletitle";
    public static final String Schedule_COLUMN_TIME = "scheduletime";
    public static final String MESSAGES_TABLE = "messagestable";
    public static final String MESSAGES_UID = "uid";

    public static final String MESSAGES_COLUMN_TITLE = "msghead";
    public static final String MESSAGES_COLUMN_BODY = "msgbody";
    public static final String MESSAGES_COLUMN_TIME = "msgtime";
    public static final String NOTIFICATION_ID = "notification_id";
    public static final String NOTIFICATION_TYPE_ID = "notification_type_id";
    public static final String NOTIFICATION_TITLE = "notification_title";
    public static final String NOTIFICATION_CONTENT = "notification_content";
    public static final String NOTIFICATION_ACTION_ID = "notification_action_id";
    public static final String NOTIFICATION_ACTION_NAME = "notification_action_name";
    public static final String NOTIFICATION_TIMESTAMP = "notification_timestamp";
    public static final String NOTIFICATION_UNREAD = "notification_unread";
    // Sharedpref file name
    private static final String PREF_NAME = "TECHXTERPREF";
    private static final String GOOGLE_PROFILE_URI = "google_profile_uri";
    private static final String CONTACT_NO="contact_no";
    // shared preference for first time run
    private static final String IS_FIRST = "is_first";
    private static final String USER_EMAIL="email";


    private static final String TEAM="team";

    private static final String VENUE="venue";

    // All Shared Preferences Keys
    private SharedPreferences pref;
    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Constructor
    public DetailsManager(Context context)
    {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public  String getTeam() {
        return pref.getString(TEAM,"");
    }

    public void setTeam(String team)
    {
        editor.putString(TEAM, team).apply();
    }
    public  String getVENUE() {
        return pref.getString(VENUE,"");
    }

    public void setVenue(String venue)
    {
        editor.putString(VENUE, venue).apply();
    }

    public  String getFoodCode() {
        return pref.getString(FOOD_CODE,"");
    }


    public String getUserId()
    {
        return pref.getString(USER_ID, " ");
    }

    public void setUserId(String userId)
    {
        editor.putString(USER_ID, userId).apply();
    }






    public String getGoogleProfileUri()
    {

        return pref.getString(GOOGLE_PROFILE_URI,"");
    }
    public void setFoodCode(String foodcode)
    { editor.putString(FOOD_CODE, foodcode).apply();}
    public void setGoogleProfileUri(String uri)
    {

        editor.putString(GOOGLE_PROFILE_URI, uri);

    }

    public String getName()
    {

        return  pref.getString(USER_NAME,"");
        //pref.getString(USER_NAME, "name");
    }

    public void setName(String name)
    {
        editor.putString(USER_NAME, name).apply();
    }

    public Boolean getIsFirst()
    {
        return pref.getBoolean(IS_FIRST, false);
    }

    public void setIsFirst(boolean value)
    {
        editor.putBoolean(IS_FIRST, true).apply();
    }



    public void setContactNo(String contact){
        editor.putString(CONTACT_NO, contact).apply();
    }

    public String getContactNo(){
        return pref.getString(CONTACT_NO,"none");
    }

    public void setEmail(String email){
        editor.putString(USER_EMAIL, email).apply();
    }
    public String getUserEmail(){
        Log.d("kartoy",pref.getString(USER_EMAIL, "null"));
        return pref.getString(USER_EMAIL, "null");
    }

}
