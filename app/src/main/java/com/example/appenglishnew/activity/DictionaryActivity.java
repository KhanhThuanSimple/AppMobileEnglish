package com.example.appenglishnew.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appenglishnew.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryActivity extends AppCompatActivity {

    EditText etWord;
    Button btnSearch;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        etWord = findViewById(R.id.etWord);
        btnSearch = findViewById(R.id.btnSearch);
        tvResult = findViewById(R.id.tvResult);

        btnSearch.setOnClickListener(v -> {
            String word = etWord.getText().toString().trim();
            if (!word.isEmpty()) {
                fetchDefinition(word);
            } else {
                tvResult.setText("Vui lòng nhập từ cần tra.");
            }
        });
    }

    private void fetchDefinition(String word) {
        new Thread(() -> {
            try {
                String encodedWord = java.net.URLEncoder.encode(word, "UTF-8");
                URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + encodedWord);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        result.append(line);
                    }
                    in.close();

                    // Parse JSON an toàn
                    JSONArray jsonArray = new JSONArray(result.toString());
                    if (jsonArray.length() > 0) {
                        JSONObject firstEntry = jsonArray.getJSONObject(0);
                        JSONArray meanings = firstEntry.optJSONArray("meanings");
                        if (meanings != null && meanings.length() > 0) {
                            JSONObject firstMeaning = meanings.getJSONObject(0);
                            JSONArray definitions = firstMeaning.optJSONArray("definitions");
                            if (definitions != null && definitions.length() > 0) {
                                JSONObject firstDef = definitions.getJSONObject(0);
                                String definition = firstDef.optString("definition", "Không có định nghĩa.");

                                // Loại bỏ dấu ngoặc kép ở đầu/cuối nếu có
                                definition = definition.trim();
                                if (definition.startsWith("\"") && definition.endsWith("\"") && definition.length() > 1) {
                                    definition = definition.substring(1, definition.length() - 1);
                                }

                                String finalDefinition = definition;
                                runOnUiThread(() -> tvResult.setText("Định nghĩa: " + finalDefinition));
                            } else {
                                runOnUiThread(() -> tvResult.setText("Không có định nghĩa cho từ này."));
                            }
                        } else {
                            runOnUiThread(() -> tvResult.setText("Không có nghĩa cho từ này."));
                        }
                    } else {
                        runOnUiThread(() -> tvResult.setText("Không tìm thấy dữ liệu cho từ này."));
                    }
                } else {
                    runOnUiThread(() -> tvResult.setText("Không tìm thấy định nghĩa hoặc có lỗi xảy ra."));
                }
                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace(); // để log lỗi debug
                runOnUiThread(() -> tvResult.setText("Không tìm thấy định nghĩa hoặc có lỗi xảy ra."));
            }
        }).start();
    }

}
