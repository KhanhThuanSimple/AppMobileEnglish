package com.example.appenglishnew.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appenglishnew.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnLogin;
    private TextView tvRegister;
    private TextView tvforgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Layout của bạn là login.xml

        // Ánh xạ view
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvforgotpassword= findViewById(R.id.tvForgotPassword);

        // Bắt sự kiện đăng nhập
        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Thực hiện đăng nhập (gọi API hoặc kiểm tra giả)
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                // Chuyển sang màn hình chính (nếu có)
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Bắt sự kiện chuyển sang đăng ký
        tvRegister.setOnClickListener(view -> {
            // TODO: Mở màn hình đăng ký (nếu có)
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
//            Toast.makeText(this, "Chức năng đang phát triển", Toast.LENGTH_SHORT).show();
        });
        tvforgotpassword.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
        } );
    }


}
