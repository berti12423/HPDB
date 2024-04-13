package com.example.hospitalproject;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataManager {
    private static ArrayList<Patient> patients;
    private static final String DBMainList="patients";
    public static void LoadDataBase()
    {
        DBManager.getDB().getReference(DBMainList).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<ArrayList<Patient>> t = new GenericTypeIndicator<ArrayList<Patient>>() {};
                patients=snapshot.getValue(t);
                if(patients==null)
                {
                    patients=new ArrayList<Patient>();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static ArrayList<Patient> getPatients()
    {
        if(patients==null)
        {
            patients=new ArrayList<Patient>();
        }
        return  patients;
    }
    public static void AddNewPatient(Patient patient)
    {
        getPatients().add(patient);
        DBManager.getDB().getReference(DBMainList).setValue(patients);
    }

    public static void retrievePatients(){
        DatabaseReference dbRef = DBManager.getDB().getReference("patients");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<ArrayList<Patient>> t = new GenericTypeIndicator<ArrayList<Patient>>() {};
                DataManager.patients=snapshot.getValue(t);
                if (patients==null)
                {
                    patients=new ArrayList<Patient>();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
