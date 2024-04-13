package com.example.hospitalproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBManager {
    private static FirebaseDatabase db;
    public static  FirebaseDatabase getDB()
    {
        if (db==null)
        {
            db= FirebaseDatabase.getInstance("https://hospitalproject-7f6a4-default-rtdb.europe-west1.firebasedatabase.app/");
        }
        return db;
    }
    public static DatabaseReference GetMainRoot()
    {
        return getDB().getReference("patients");
    }

}

