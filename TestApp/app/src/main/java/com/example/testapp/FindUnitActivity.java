package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindUnitActivity extends AppCompatActivity {
    private ImageView imvBack;
    private EditText edtTimKiem;
    private ImageView imvTimKiem;
    private ListView lvUnit;
    private FirebaseDatabaseHelperUnit firebaseHelper;
    private List<Unit> unitList;
    private UnitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_unit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imvBack = findViewById(R.id.imvQuayLai);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        imvTimKiem = findViewById(R.id.imvTimKiem);
        lvUnit = findViewById(R.id.lvUnit);

        firebaseHelper = new FirebaseDatabaseHelperUnit();
        unitList = new ArrayList<>();
        adapter = new UnitAdapter(this, R.layout.item_listview_unit, unitList);
        lvUnit.setAdapter(adapter);

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindUnitActivity.this, UnitMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imvTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = edtTimKiem.getText().toString().trim();
                if (searchText.isEmpty()) {
                    Toast.makeText(FindUnitActivity.this, "Vui lòng nhập tên để tìm kiếm", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseHelper.getReference().orderByChild("tenDonVi").startAt(searchText).endAt(searchText + "\uf8ff").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        unitList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Unit unit = snapshot.getValue(Unit.class);
                            unitList.add(unit);
                        }
                        adapter.notifyDataSetChanged();

                        if (unitList.isEmpty()) {
                            Toast.makeText(FindUnitActivity.this, "Không tìm thấy đơn vị", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(FindUnitActivity.this, "Lỗi khi tìm kiếm", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
