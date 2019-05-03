package com.example.dictionarysqllite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import helper.Word;

public class DisplayMeaning extends AppCompatActivity {
    private TextView lvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_meaning);

        lvWord = findViewById(R.id.lvWordList);
//        lvWord.setText("hel");
        Bundle bundle = new Bundle();

        if(bundle != null){
            lvWord.setText(bundle.getString("meaning")+"a");
        }
        LoadWord();

    }

    private void LoadWord() {
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sql = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = myHelper.GetAllWords(sql);

        HashMap<String, String> hashMap = new HashMap<>();
        for(int i = 0; i<wordList.size();i++){
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())
        );
//        lvWord.setAdapter(stringArrayAdapter);
    }
}
