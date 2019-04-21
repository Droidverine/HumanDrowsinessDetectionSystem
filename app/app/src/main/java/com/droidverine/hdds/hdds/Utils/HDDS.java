package com.droidverine.hdds.hdds.Utils;

import android.app.Application;

public class HDDS extends Application {
    private static HDDS mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized HDDS getInstance() {
        return mInstance;
    }
}
