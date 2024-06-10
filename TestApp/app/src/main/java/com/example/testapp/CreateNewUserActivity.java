package com.example.testapp;

import com.example.testapp.FirebaseDatabaseHelperStaff;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class CreateNewUserActivity extends AppCompatActivity {
    private FirebaseDatabaseHelperStaff staffHelper;
    private ImageView imvBack;
    private Button btnLuu;
    private EditText edtMaDonVi;
    private EditText edtMaNhanVien;
    private EditText edtHoTen;
    private EditText edtChucVu;
    private EditText edtEmail;
    private EditText edtSDT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_new_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imvBack = findViewById(R.id.imvQuayLai);
        btnLuu = findViewById(R.id.btnLuu);
        edtMaDonVi = findViewById(R.id.edtMaDonVi);
        edtMaNhanVien = findViewById(R.id.edtMaNhanVien);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtChucVu = findViewById(R.id.edtChucVu);
        edtEmail = findViewById(R.id.edtEmail);
        edtSDT = findViewById(R.id.edtSDT);

        staffHelper = new FirebaseDatabaseHelperStaff();
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStaff();
            }
        });
    }

    private  void addStaff(){
        String maDonVi = edtMaDonVi.getText().toString();
        String maNhanVien = edtMaNhanVien.getText().toString();
        String hoTen = edtHoTen.getText().toString();
        String chucVu = edtChucVu.getText().toString();
        String email = edtEmail.getText().toString();
        String soDienThoai = edtSDT.getText().toString();

        if (maDonVi.isEmpty() || maNhanVien.isEmpty() || hoTen.isEmpty() || chucVu.isEmpty() || email.isEmpty() || soDienThoai.isEmpty()) {
            Toast.makeText(CreateNewUserActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Staff newStaff = new Staff(maNhanVien, hoTen, chucVu, email, soDienThoai, maDonVi);

        staffHelper.addStaff(newStaff);
        Toast.makeText(CreateNewUserActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields() {
        edtMaDonVi.setText("");
        edtMaNhanVien.setText("");
        edtHoTen.setText("");
        edtChucVu.setText("");
        edtEmail.setText("");
        edtSDT.setText("");
    }
}
