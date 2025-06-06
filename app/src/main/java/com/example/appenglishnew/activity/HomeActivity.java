package com.example.appenglishnew.activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appenglishnew.R;
import com.example.appenglishnew.adapter.LearningOptionAdapter;
import com.example.appenglishnew.model.LearningOption;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvLearningOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Gắn layout ở đây

        rvLearningOptions = findViewById(R.id.rvLearningOptions);

        List<LearningOption> optionList = new ArrayList<>();
        optionList.add(new LearningOption("Học Ngữ Pháp", R.drawable.ic_vocabulary));
        optionList.add(new LearningOption("Ôn Tập Ngữ Pháp", R.drawable.book));
        optionList.add(new LearningOption("Học Từ Vựng", R.drawable.ic_vocabulary));
        optionList.add(new LearningOption("Thi Thử", R.drawable.ic_test));
        optionList.add(new LearningOption("Lộ Trình Học", R.drawable.ic_journey));
        optionList.add(new LearningOption("Tra cứu từ vựng", R.drawable.ic_review));

        LearningOptionAdapter adapter = new LearningOptionAdapter(this, optionList, option -> {
            Intent intent = null;
            switch (option.getTitle()) {
                case "Tra cứu từ vựng":
                    intent = new Intent(HomeActivity.this, DictionaryActivity.class);
                    break;
//                case "Học Từ Vựng":
//                    intent = new Intent(HomeActivity.this, VocabularyActivity.class);
//                    break;
//                case "Học Ngữ Pháp":
//                    intent = new Intent(HomeActivity.this, GrammarActivity.class);
//                    break;
//                case "Ôn Tập Ngữ Pháp":
//                    intent = new Intent(HomeActivity.this, GrammarReviewActivity.class);
//                    break;
//                case "Thi Thử":
//                    intent = new Intent(HomeActivity.this, TestActivity.class);
//                    break;
//                case "Lộ Trình Học":
//                    intent = new Intent(HomeActivity.this, LearningJourneyActivity.class);
//                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }
        });

        rvLearningOptions.setLayoutManager(new GridLayoutManager(this, 2));
        rvLearningOptions.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_profile) {
                    Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else if (id==R.id.nav_progress) {
                   Intent intent = new Intent(HomeActivity.this, ProgressActivity.class);
                    startActivity(intent);


                }else if (id==R.id.nav_settings) {
                    Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                    startActivity(intent);


                }

                return true;
            });
        } else {
            Toast.makeText(this, "NavigationView không tồn tại trong layout!", Toast.LENGTH_SHORT).show();
        }


    }
}