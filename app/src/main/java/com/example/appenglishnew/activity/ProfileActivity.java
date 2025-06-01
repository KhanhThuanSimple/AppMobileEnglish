package com.example.appenglishnew.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appenglishnew.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import android.widget.ImageView;
//import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView tvFullName, tvEmail;
    private ImageView imgAvatar;
    private MaterialButton btnLogout, btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();

        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnLogout = findViewById(R.id.btnLogout);
        btnEditProfile = findViewById(R.id.btnEditProfile);


        btnLogout.setOnClickListener(v -> {
            FirebaseUser currentUserBeforeLogout = auth.getCurrentUser();
            if (currentUserBeforeLogout != null) {
//                Toast.makeText(ProfileActivity.this, "User trước logout: " + currentUserBeforeLogout.getEmail(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProfileActivity.this, "Chưa có user đăng nhập trước logout", Toast.LENGTH_SHORT).show();
            }

            auth.signOut();

            FirebaseUser currentUserAfterLogout = auth.getCurrentUser();
            if (currentUserAfterLogout == null) {
                Toast.makeText(ProfileActivity.this, "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProfileActivity.this, "Đăng xuất không thành công!", Toast.LENGTH_SHORT).show();
            }

            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        });

        // BottomNavigationView xử lý như bạn có sẵn
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                } else if (id == R.id.nav_progress) {
                    startActivity(new Intent(ProfileActivity.this, ProgressActivity.class));
                } else if (id == R.id.nav_settings) {
                    startActivity(new Intent(ProfileActivity.this, SettingsActivity.class));
                }
                return true;
            });
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            // Người dùng chưa đăng nhập -> chuyển sang màn Login
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            // Đã đăng nhập, hiển thị thông tin người dùng
            displayUserInfo();
        }
    }
    private void displayUserInfo() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            // Hiển thị email
            tvEmail.setText(user.getEmail());

            // Hiển thị tên, nếu có (nếu không có thì có thể lấy email hoặc "Người dùng")
            if (user.getDisplayName() != null && !user.getDisplayName().isEmpty()) {
                tvFullName.setText(user.getDisplayName());
            } else {
                tvFullName.setText("Người dùng");
            }

            // Hiển thị avatar nếu có photoUrl
//            if (user.getPhotoUrl() != null) {
//                Glide.with(this)
//                        .load(user.getPhotoUrl())
//                        .placeholder(R.drawable.ic_profile)
//                        .into(imgAvatar);
//            } else {
//                imgAvatar.setImageResource(R.drawable.ic_profile);
//            }
        } else {
            // Nếu user chưa đăng nhập
            tvFullName.setText("Chưa đăng nhập");
            tvEmail.setText("");
            imgAvatar.setImageResource(R.drawable.ic_profile);
        }
    }
}
