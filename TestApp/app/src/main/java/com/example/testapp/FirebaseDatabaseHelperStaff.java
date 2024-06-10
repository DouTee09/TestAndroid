package com.example.testapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseHelperStaff {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceStaffs;

    public FirebaseDatabaseHelperStaff() {
        mDatabase = FirebaseDatabase.getInstance("https://qldanhba-fe458-default-rtdb.asia-southeast1.firebasedatabase.app/");
        mReferenceStaffs = mDatabase.getReference("staffs");
    }

    public void addStaff(Staff staff) {
        String id = mReferenceStaffs.push().getKey();
        staff.setMaNhanVien(id);
        mReferenceStaffs.child(id).setValue(staff);
    }

    public void updateStaff(String id, Staff staff) {
        mReferenceStaffs.child(id).setValue(staff);
    }

    public void deleteStaff(String id) {
        mReferenceStaffs.child(id).removeValue();
    }

    public DatabaseReference getReference() {
        return mReferenceStaffs;
    }
}
