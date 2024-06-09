package com.example.testapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseHelperUnit {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceUnits;

    public FirebaseDatabaseHelperUnit() {
        mDatabase = FirebaseDatabase.getInstance("https://qldanhba-fe458-default-rtdb.asia-southeast1.firebasedatabase.app/");
        mReferenceUnits = mDatabase.getReference("units");
    }

    public void addUnit(Unit unit) {
        String id = mReferenceUnits.push().getKey();
        unit.setMaDonVi(id);
        mReferenceUnits.child(id).setValue(unit);
    }

    public void updateUnit(String id, Unit unit) {
        mReferenceUnits.child(id).setValue(unit);
    }

    public void deleteUnit(String id) {
        mReferenceUnits.child(id).removeValue();
    }

    public DatabaseReference getReference() {
        return mReferenceUnits;
    }
}
