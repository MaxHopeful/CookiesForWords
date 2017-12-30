package com.jarvis.coockiesforwords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StudyingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button newWord,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying);

        newWord = (Button) findViewById(R.id.newWord);
        newWord.setOnClickListener(this);

        answer = (Button) findViewById(R.id.answer);
        answer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.answer:

                break;
            case R.id.newWord:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

       }
    }
}
