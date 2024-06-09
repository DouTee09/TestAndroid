package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class UnitMainActivity extends AppCompatActivity {
    private ImageView imvThemDonVi;
    private ImageView imvTimKiem;
    private Button btnNhanVien;

    private ListView lvDanhSachDonVi;
    private UnitAdapter unitAdapter;
    private List<Unit> unitList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imvThemDonVi = findViewById(R.id.imvThemDonVi);
        imvTimKiem = findViewById(R.id.imvTimKiem);
        btnNhanVien = findViewById(R.id.btnNhanVien);

        lvDanhSachDonVi = findViewById(R.id.lvDanhSachDonVi);
        unitList = new ArrayList<>();
        unitAdapter = new UnitAdapter(this, R.layout.item_listview_unit, unitList);
        lvDanhSachDonVi.setAdapter(unitAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference("units");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                unitList.clear();
                for (DataSnapshot unitSnapshot : dataSnapshot.getChildren()) {
                    Unit unit = unitSnapshot.getValue(Unit.class);
                    unitList.add(unit);
                }
                unitAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

        imvThemDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnitMainActivity.this, CreateNewUnitActivity.class);
                startActivity(intent);
            }
        });

        imvTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnitMainActivity.this, FindUnitActivity.class);
                startActivity(intent);
            }
        });

        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnitMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lvDanhSachDonVi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit selectedUnit = unitList.get(position);
                Intent intent = new Intent(UnitMainActivity.this, InformationUnitActivity.class);
                intent.putExtra("unit", selectedUnit);
                startActivity(intent);
            }
        });
    }
}
