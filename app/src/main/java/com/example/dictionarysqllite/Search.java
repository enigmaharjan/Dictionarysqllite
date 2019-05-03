package com.example.dictionarysqllite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helper.MyHelper;
import helper.Word;

import static java.lang.String.valueOf;

public class Search extends AppCompatActivity {
    private EditText etSearch;
    private Button btnSearch;
    private ListView lvSearch;
    Map<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch= findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        lvSearch = findViewById(R.id.lvSearch);

        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        hashMap = new HashMap<>();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Word> wordList = new ArrayList<>();
                wordList =  myHelper.GetWordbyName(etSearch.getText().toString(), sqLiteDatabase);


                for(int i = 0; i<wordList.size();i++){
                    hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
                }
                ArrayAdapter stringArrayAdapter = new ArrayAdapter<String> (
                            getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        new ArrayList<String>(hashMap.keySet())
                );
                lvSearch.setAdapter(stringArrayAdapter);
            }
        });

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);
                String meaning = hashMap.get(selectedItem);
                Intent i = new Intent(Search.this, DisplayMeaning.class);
                i.putExtra("meaning","hell");
                startActivity(i);
//                Toast.makeText(Search.this, meaning, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
