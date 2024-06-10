package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Find_Activity extends AppCompatActivity {
    private EditText edtSearch;
    private ImageView imvTimKiem;
    private ListView lvDanhSachNhanVien;
    private FirebaseDatabaseHelperStaff firebaseHelper;
    private List<Staff> staffList;
    private ImageView imvBack;
    private StaffAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        imvBack = findViewById(R.id.imvQuayLai);
        edtSearch = findViewById(R.id.edtSearch);
        imvTimKiem = findViewById(R.id.imvTimKiem);
        lvDanhSachNhanVien = findViewById(R.id.lvDanhSachNhanVien);

        firebaseHelper = new FirebaseDatabaseHelperStaff();
        staffList = new ArrayList<>();
        adapter = new StaffAdapter(this, (ArrayList<Staff>) staffList);
        lvDanhSachNhanVien.setAdapter(adapter);

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imvTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = edtSearch.getText().toString().trim();
                if (searchText.isEmpty()) {
                    Toast.makeText(Find_Activity.this, "Vui lòng nhập tên để tìm kiếm", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseHelper.getReference().orderByChild("tenNhanVien").startAt(searchText).endAt(searchText + "\uf8ff").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        staffList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Staff staff = snapshot.getValue(Staff.class);
                            staffList.add(staff);
                        }
                        adapter.notifyDataSetChanged();

                        if (staffList.isEmpty()) {
                            Toast.makeText(Find_Activity.this, "Không tìm thấy nhân viên", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Find_Activity.this, "Lỗi khi tìm kiếm", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
