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

public class CreateNewUnitActivity extends AppCompatActivity {

    private FirebaseDatabaseHelperUnit unitHelper;
    private EditText edtMaDonViCha;
    private EditText edtTenDonVi;
    private EditText edtEmail;
    private EditText edtWebsite;
    private EditText edtDiaChi;
    private EditText edtSDT;

    private ImageView imvBack;
    private Button btnLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_new_unit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        unitHelper = new FirebaseDatabaseHelperUnit();

        imvBack = findViewById(R.id.imvQuayLai);
        btnLuu = findViewById(R.id.btnLuu);

        edtMaDonViCha = findViewById(R.id.edtMaDonViCha);
        edtTenDonVi = findViewById(R.id.edtTenDonVi);
        edtEmail = findViewById(R.id.edtEmail);
        edtWebsite = findViewById(R.id.edtWebsite);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSDT);

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewUnitActivity.this, UnitMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUnit();
            }
        });
    }

    private  void addUnit(){
        String maDonViCha = edtMaDonViCha.getText().toString();
        String tenDonVi = edtTenDonVi.getText().toString();
        String email = edtEmail.getText().toString();
        String website = edtWebsite.getText().toString();
        String diachi = edtDiaChi.getText().toString();
        String soDienThoai = edtSDT.getText().toString();

        if (maDonViCha.isEmpty() || tenDonVi.isEmpty() || email.isEmpty() || soDienThoai.isEmpty()) {
            Toast.makeText(CreateNewUnitActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Unit newUnit = new Unit(maDonViCha, tenDonVi, email, website, diachi ,soDienThoai);

        unitHelper.addUnit(newUnit);
        Toast.makeText(CreateNewUnitActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields() {
        edtMaDonViCha.setText("");
        edtTenDonVi.setText("");
        edtEmail.setText("");
        edtWebsite.setText("");
        edtDiaChi.setText("");
        edtSDT.setText("");
    }
}