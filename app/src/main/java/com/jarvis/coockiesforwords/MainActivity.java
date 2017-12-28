package com.jarvis.coockiesforwords;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addBut,repBut;
    EditText rusWord,engWord;

    DBHelper dbHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBut = (Button) findViewById(R.id.addBut);
        addBut.setOnClickListener(this);

        repBut = (Button) findViewById(R.id.repBut);
        addBut.setOnClickListener(this);

        rusWord = (EditText) findViewById(R.id.rusWord);
        engWord = (EditText) findViewById(R.id.engWord);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.addBut:

                String rus = rusWord.getText().toString();
                String eng = engWord.getText().toString();

                SQLiteDatabase database = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_RUS, rus);
                contentValues.put(DBHelper.KEY_ENG, eng);
                database.insert(DBHelper.TABLE_WORDS, null, contentValues);


                dbHelper.close();

                break;

            case R.id.repBut:

                //Intent intent = new Intent(this,Repeat.class);
                //startActivity(intent);

                break;
            default:
                break;
        }


    }
}
