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

public class InformationUnitActivity extends AppCompatActivity {
    private FirebaseDatabaseHelperUnit unitHelper;
    private Unit currentUnit;
    private ImageView imvBack;
    private EditText edtMaDonViCha;
    private EditText edtTenDonVi;
    private EditText edtEmail;
    private EditText edtWebsite;
    private EditText edtDiaChi;
    private EditText edtSDT;
    private Button btnLuu;
    private Button btnXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_information_unit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imvBack = findViewById(R.id.imvQuayLai);

        edtMaDonViCha = findViewById(R.id.edtMaDonViCha);
        edtTenDonVi = findViewById(R.id.edtTenDonVi);
        edtEmail = findViewById(R.id.edtEmail);
        edtWebsite = findViewById(R.id.edtWebsite);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSDT);

        btnLuu = findViewById(R.id.btnLuu);
        btnXoa = findViewById(R.id.btnDelete);

        unitHelper = new FirebaseDatabaseHelperUnit();

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationUnitActivity.this, UnitMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Unit unit = (Unit) getIntent().getSerializableExtra("unit");
        if (unit != null) {
            edtMaDonViCha.setText(unit.getMaDonVi());
            edtTenDonVi.setText(unit.getTenDonVi());
            edtEmail.setText(unit.getEmail());
            edtWebsite.setText(unit.getWebsite());
            edtDiaChi.setText(unit.getDiaChi());
            edtSDT.setText(unit.getSdt());
        }

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maDonViCha = edtMaDonViCha.getText().toString();
                String tenDonVi = edtTenDonVi.getText().toString();
                String email = edtEmail.getText().toString();
                String website = edtWebsite.getText().toString();
                String diachi = edtDiaChi.getText().toString();
                String sdt = edtSDT.getText().toString();

                Unit updatedUnit = new Unit(maDonViCha, tenDonVi, email, website, diachi, sdt);
                unitHelper.updateUnit(maDonViCha, updatedUnit);

                Toast.makeText(InformationUnitActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(InformationUnitActivity.this, UnitMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maDonViCha = edtMaDonViCha.getText().toString();

                unitHelper.deleteUnit(maDonViCha);
                Toast.makeText(InformationUnitActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InformationUnitActivity.this, UnitMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
