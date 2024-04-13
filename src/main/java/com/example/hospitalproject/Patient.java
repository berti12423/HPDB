package com.example.hospitalproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient implements Serializable {
    public Patient(){}
    private ArrayList<Reaction> Rating;
    private Reaction currentRating;
    private String PatientCode;
    public Patient(ArrayList<com.example.hospitalproject.Reaction> rating, String patientCode) {
        Rating = rating;
        PatientCode = patientCode;
    }
    public Patient (String patientCode)
    {
        PatientCode=patientCode;
    }
    public void setReaction(ArrayList<com.example.hospitalproject.Reaction> rating) {
        Rating = rating;
    }
    public void setPatientCode(String  patientCode) {
        PatientCode = patientCode;
    }
    public ArrayList<com.example.hospitalproject.Reaction> getRating() {
        return Rating;
    }
    public String getPatientCode() {
        return PatientCode;
    }

}
