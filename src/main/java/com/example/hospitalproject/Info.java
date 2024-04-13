package com.example.hospitalproject;

public class Info {
    public Info(){}

    public Info(String surgeryName, String dateOfHospitalization, String meds, String externalIntervention) {
        SurgeryName = surgeryName;
        DateOfHospitalization = dateOfHospitalization;
        this.meds = meds;
        ExternalIntervention = externalIntervention;
    }

    private String SurgeryName;
    private String DateOfHospitalization;
    private String meds;
    private String ExternalIntervention;

    public String getSurgeryName() {
        return SurgeryName;
    }

    public String getDateOfHospitalization() {
        return DateOfHospitalization;
    }

    public String getMeds() {
        return meds;
    }

    public String getExternalIntervention() {
        return ExternalIntervention;
    }

    public void setSurgeryName(String surgeryName) {
        SurgeryName = surgeryName;
    }

    public void setDateOfHospitalization(String dateOfHospitalization) {
        DateOfHospitalization = dateOfHospitalization;
    }

    public void setMeds(String meds) {
        this.meds = meds;
    }

    public void setExternalIntervention(String externalIntervention) {
        ExternalIntervention = externalIntervention;
    }
}
