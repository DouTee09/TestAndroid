package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InformationUserActivity extends AppCompatActivity {
    private FirebaseDatabaseHelperStaff staffHelper;
    private Staff currentStaff;
    private ImageView imvBack;
    private EditText edtMaDonVi;
    private EditText edtMaNhanVien;
    private EditText edtHoTen;
    private  EditText edtChucVu;
    private  EditText edtEmail;
    private EditText edtSDT;
    private Button btnLuu;
    private Button btnXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_information_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imvBack = findViewById(R.id.imvQuayLai);
        edtMaDonVi = findViewById(R.id.edtMaDonVi);
        edtMaNhanVien = findViewById(R.id.edtMaNhanVien);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtChucVu = findViewById(R.id.edtChucVu);
        edtEmail = findViewById(R.id.edtEmail);
        edtSDT = findViewById(R.id.edtSDT);
        btnLuu = findViewById(R.id.btnLuu);
        btnXoa = findViewById(R.id.btnXoa);

        staffHelper = new FirebaseDatabaseHelperStaff();

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Staff staff = (Staff) getIntent().getSerializableExtra("staff");
        if (staff != null) {
            edtMaDonVi.setText(staff.getMaDonVi());
            edtMaNhanVien.setText(staff.getMaNhanVien());
            edtHoTen.setText(staff.getTenNhanVien());
            edtChucVu.setText(staff.getChucVu());
            edtEmail.setText(staff.getEmail());
            edtSDT.setText(staff.getSDT());
        }

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maDonVi = edtMaDonVi.getText().toString();
                String maNhanVien = edtMaNhanVien.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String chucVu = edtChucVu.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtSDT.getText().toString();

                Staff updatedStaff = new Staff(maNhanVien, hoTen, chucVu, email, sdt, maDonVi);
                staffHelper.updateStaff(maNhanVien, updatedStaff);

                Toast.makeText(InformationUserActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(InformationUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNhanVien = edtMaNhanVien.getText().toString();

                staffHelper.deleteStaff(maNhanVien);
                Toast.makeText(InformationUserActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InformationUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}