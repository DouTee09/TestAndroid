package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabaseHelperStaff staffHelper;
    private ListView listViewStaff;
    private StaffAdapter staffAdapter;
    private ArrayList<Staff> staffList;
    private Button btnDonVi;
    private ImageView imvThemNhanVien;
    private ImageView imvTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewStaff = findViewById(R.id.ListViewStaff);
        btnDonVi = findViewById(R.id.btDonVi);
        imvThemNhanVien = findViewById(R.id.imvThemNhanVien);
        imvTimKiem = findViewById(R.id.imvTimKiem);

        staffList = new ArrayList<>();
        staffAdapter = new StaffAdapter(this, staffList);
        listViewStaff.setAdapter(staffAdapter);

        staffHelper = new FirebaseDatabaseHelperStaff();
        DatabaseReference staffRef = staffHelper.getReference();
        staffRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                staffList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Staff staff = snapshot.getValue(Staff.class);
                    staffList.add(staff);
                }
                staffAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });

        btnDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UnitMainActivity.class);
                startActivity(intent);
            }
        });

        imvThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNewUserActivity.class);
                startActivity(intent);
            }
        });

        imvTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Find_Activity.class);
                startActivity(intent);
            }
        });

        listViewStaff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Staff selectedStaff = staffList.get(position);
                Intent intent = new Intent(MainActivity.this, InformationUserActivity.class);
                intent.putExtra("staff", selectedStaff);
                startActivity(intent);
            }
        });
    }
}
