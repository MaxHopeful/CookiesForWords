package com.jarvis.coockiesforwords;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class StudyingActivity extends AppCompatActivity implements View.OnClickListener {

    Button newWord,answer;
    TextView word;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying);

        newWord = (Button) findViewById(R.id.newWord);
        newWord.setOnClickListener(this);

        answer = (Button) findViewById(R.id.answer);
        answer.setOnClickListener(this);

        word = (TextView) findViewById(R.id.word);

        dbHelper = new DBHelper(this);

        final Random random = new Random();

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_WORDS,
                new String[] {DBHelper.KEY_ENG, DBHelper.KEY_RUS},
                null,null,
                null, null, null);

        //Cursor cursor = database.rawQuery("SELECT eng FROM words WHERE _id=1",null);

        int randWord=random.nextInt(cursor.getCount());

        String engContent="";
        if(cursor.moveToPosition(randWord)){
            //уже головое англиское слово
            engContent = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ENG));
        }
        word.setText(engContent);

        cursor.close();
        database.close();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.answer:

                /*if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int rusIndex = cursor.getColumnIndex(DBHelper.KEY_RUS);
                    int engIndex = cursor.getColumnIndex(DBHelper.KEY_ENG);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", rus = " + cursor.getString(rusIndex) +
                                ", eng = " + cursor.getString(engIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");*/



                break;
            case R.id.newWord:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

       }
    }
}
