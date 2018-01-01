package com.jarvis.coockiesforwords;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudyingActivity extends AppCompatActivity implements View.OnClickListener {

    Button newWord,answer,pass;
    TextView word,result;
    EditText transWord;

    String engContent;
    String rusContent;

    Cursor cursor;
    boolean langFlag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying);

        newWord = (Button) findViewById(R.id.newWord);
        newWord.setOnClickListener(this);

        answer = (Button) findViewById(R.id.answer);
        answer.setOnClickListener(this);

        pass = (Button) findViewById(R.id.pass);
        pass.setOnClickListener(this);

        transWord = (EditText) findViewById(R.id.transWord);

        word = (TextView) findViewById(R.id.word);
        result = (TextView) findViewById(R.id.result);

        setRandWord();

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.answer:
                String answerWord = transWord.getText().toString();
                if(langFlag){
                    if(rusContent.equals(answerWord)){
                        result.setText("Правильно");
                        setRandWord();
                    }else{
                        result.setText("Не верно");
                    }
                }else{
                    if(engContent.equals(answerWord)){
                        result.setText("Правильно");
                        setRandWord();
                    }else{
                        result.setText("Не верно");
                    }
                }
                break;

            case R.id.pass:
                setRandWord();
                result.setText("");
                break;
            case R.id.newWord:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

       }
    }

    public void setRandWord(){
        DBHelper dbHelper;
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        cursor = database.rawQuery("SELECT eng,rus FROM words ORDER BY Random() LIMIT 1",null);

        java.util.Random r = new java.util.Random();
        if(cursor.moveToFirst()){
            //уже головые слова
            engContent = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ENG));
            rusContent = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_RUS));
        }

        if(r.nextBoolean()){
            word.setText(engContent);
            langFlag=true;
        }else{
            word.setText(rusContent);
            langFlag=false;
        }
        transWord.setText(null);

        database.close();
        cursor.close();
    }

}
