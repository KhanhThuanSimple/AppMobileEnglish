package com.example.appenglishnew.activity;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appenglishnew.R;
import com.example.appenglishnew.adapter.LearningOptionAdapter;
import com.example.appenglishnew.model.LearningOption;

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
        optionList.add(new LearningOption("Học Ngữ Pháp", R.drawable.book));
        optionList.add(new LearningOption("Ôn Tập Ngữ Pháp", R.drawable.ic_review));
        optionList.add(new LearningOption("Học Từ Vựng", R.drawable.ic_vocabulary));
        optionList.add(new LearningOption("Thi Thử", R.drawable.ic_test));
        optionList.add(new LearningOption("Lộ Trình Học", R.drawable.ic_journey));

        LearningOptionAdapter adapter = new LearningOptionAdapter(this, optionList);
        rvLearningOptions.setLayoutManager(new GridLayoutManager(this, 2));
        rvLearningOptions.setAdapter(adapter);
    }
}