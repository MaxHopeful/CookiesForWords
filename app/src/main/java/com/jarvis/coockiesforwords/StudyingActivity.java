package com.jarvis.coockiesforwords;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class StudyingActivity extends AppCompatActivity implements View.OnClickListener {

    Button newWord,answer;
    TextView word,result;
    DBHelper dbHelper;
    EditText transWord;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying);

        newWord = (Button) findViewById(R.id.newWord);
        newWord.setOnClickListener(this);

        answer = (Button) findViewById(R.id.answer);
        answer.setOnClickListener(this);

        transWord = (EditText) findViewById(R.id.transWord);

        word = (TextView) findViewById(R.id.word);
        result = (TextView) findViewById(R.id.result);


        word.setText(getWord("eng"));

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.answer:
                String answerWord = transWord.getText().toString();
                if(getWord("rus").equals(answerWord)){
                    result.setText("Правильно");
                }else{
                    result.setText("Не верно");
                }

                break;
            case R.id.newWord:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

       }
    }

    public String getWord(String langWord) {
        dbHelper = new DBHelper(this);
        String engContent = "";
        String rusContent = "";

        final Random random = new Random();

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_WORDS,
                new String[]{DBHelper.KEY_ENG, DBHelper.KEY_RUS},
                null, null,
                null, null, null);

        //Cursor cursor = database.rawQuery("SELECT eng FROM words WHERE _id=1",null);

        int randWord = random.nextInt(cursor.getCount());

        if (cursor.moveToPosition(randWord)) {
            //уже головое англиское слово
            engContent = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ENG));
            rusContent = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_RUS));
        }

        cursor.close();
        database.close();

        switch (langWord) {
            case "rus":
                return rusContent;
            case "eng":
                return engContent;
            default:
                return null;
        }
    }


}
