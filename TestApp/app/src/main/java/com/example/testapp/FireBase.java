package com.example.testapp;

import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance("https://qldanhba-fe458-default-rtdb.asia-southeast1.firebasedatabase.app/").setPersistenceEnabled(true);
    }
}
