package com.pronix.questionsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pronix.questionsapp.models.QuestionsDO;

import java.util.ArrayList;

public class Result extends AppCompatActivity {
    TextView tv_Result;
    QuestionsDO questionsDO;
    ArrayList<QuestionsDO> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initializeControls();
        arrayList.addAll(SubjectQuestionsActivity.arrayList);
        getResult();
    }

    public void initializeControls()
    {
        tv_Result = (TextView) findViewById(R.id.tv_Result);
    }

    public void getResult()
    {
        int res = 0;
        for (int i = 0; i < arrayList.size(); i++)
        {
            if(arrayList.get(i).isCorrectAnswer())
                res++;
        }
        tv_Result.setText(res+" / "+ arrayList.size());
    }
}
