package com.pronix.questionsapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pronix.questionsapp.models.QuestionsDO;

import java.util.ArrayList;

public class Questions extends AppCompatActivity {
    TextView tv_Question, tv_Option1, tv_Option2, tv_Option3, tv_Option4;
    Button but_Previous, but_Next;
    LinearLayout ll_Layout1, ll_Layout2, ll_Layout3, ll_Layout4;
    ArrayList<QuestionsDO> arrayList = new ArrayList<>();
    int questionNo = -1;
    String[] question = {"Which of the following is used in pencils?", "The gas usually filled in the electric bulb is", "Bromine is a"};
    String[] option1 = {"Graphite", "nitrogen", "black solid"};
    String[] option2 = {"Silicon", "hydrogen", "red liquid"};
    String[] option3 = {"Charcoal", "carbon dioxide", "colourless gas"};
    String[] option4 = {"Phosphorous", "oxygen", "highly inflammable gas"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initializeControls();
        loadData();
        but_Next.performClick();
    }

    private void initializeControls() {
        tv_Question = (TextView) findViewById(R.id.tv_Question);
        tv_Option1 = (TextView) findViewById(R.id.tv_Option1);
        tv_Option2 = (TextView) findViewById(R.id.tv_Option2);
        tv_Option3 = (TextView) findViewById(R.id.tv_Option3);
        tv_Option4 = (TextView) findViewById(R.id.tv_Option4);
        ll_Layout1 = (LinearLayout) findViewById(R.id.linearLayout);
        ll_Layout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        ll_Layout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        ll_Layout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        tv_Option1.setOnClickListener(clickListener);
        tv_Option2.setOnClickListener(clickListener);
        tv_Option3.setOnClickListener(clickListener);
        tv_Option4.setOnClickListener(clickListener);
        but_Next = (Button) findViewById(R.id.but_Next);
        but_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextRecord();
            }
        });
        but_Previous = (Button) findViewById(R.id.but_Previous);
        but_Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousRecord();
            }
        });

    }

    public void nextRecord()
    {
        questionNo++;
        if(questionNo != arrayList.size())
        {
            tv_Question.setText((questionNo + 1) +". " + arrayList.get(questionNo).getQuestion());
            tv_Option1.setText(arrayList.get(questionNo).getOption1());
            tv_Option2.setText(arrayList.get(questionNo).getOption2());
            tv_Option3.setText(arrayList.get(questionNo).getOption3());
            tv_Option4.setText(arrayList.get(questionNo).getOption4());

        }
        else
        {
            Toast.makeText(this, "Last Question",Toast.LENGTH_SHORT).show();
            questionNo--;
        }

    }

    public void previousRecord()
    {
        questionNo--;
        if(questionNo != -1 && questionNo != -2)
        {
            tv_Question.setText((questionNo + 1) +". " +arrayList.get(questionNo).getQuestion());
            tv_Option1.setText(arrayList.get(questionNo).getOption1());
            tv_Option2.setText(arrayList.get(questionNo).getOption2());
            tv_Option3.setText(arrayList.get(questionNo).getOption3());
            tv_Option4.setText(arrayList.get(questionNo).getOption4());

        }
        else
        {
            Toast.makeText(this, "1st Question",Toast.LENGTH_SHORT).show();
            questionNo++;
        }

    }

    public void loadData()
    {
        QuestionsDO obj;
        for (int i = 0; i<question.length; i++)
        {
            obj = new QuestionsDO();
            obj.setQuestion(question[i]);
            obj.setOption1(option1[i]);
            obj.setOption2(option2[i]);
            obj.setOption3(option3[i]);
            obj.setOption4(option4[i]);
            arrayList.add(obj);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView curText = (TextView) v;
            if(curText == tv_Option1)
            {
                ll_Layout1.setBackground(getResources().getDrawable(R.drawable.layout_selected));
                ll_Layout2.setBackground(getResources().getDrawable(R.drawable.layout_default));
                ll_Layout3.setBackground(getResources().getDrawable(R.drawable.layout_default));
                ll_Layout4.setBackground(getResources().getDrawable(R.drawable.layout_default));
            } else  if(curText == tv_Option2)
            {
                ll_Layout2.setBackground(getResources().getDrawable(R.drawable.layout_selected));
                ll_Layout1.setBackground(getResources().getDrawable(R.drawable.layout_default));
                ll_Layout3.setBackground(getResources().getDrawable(R.drawable.layout_default));
                ll_Layout4.setBackground(getResources().getDrawable(R.drawable.layout_default));
            } else  if(curText == tv_Option3)
            {
                ll_Layout3.setBackground(getResources().getDrawable(R.drawable.layout_selected));
                ll_Layout1.setBackground(getResources().getDrawable(R.drawable.layout_default));
                ll_Layout2.setBackground(getResources().getDrawable(R.drawable.layout_default));
                ll_Layout4.setBackground(getResources().getDrawable(R.drawable.layout_default));
            } else  if(curText == tv_Option4)
            {
                ll_Layout4.setBackground(getResources().getDrawable(R.drawable.layout_selected));
                ll_Layout1.setBackground(getResources().getDrawable(R.drawable.layout_default));
                ll_Layout3.setBackground(getResources().getDrawable(R.drawable.layout_default));
                ll_Layout2.setBackground(getResources().getDrawable(R.drawable.layout_default));
            }
            // If the clicked view is selected, deselect it
//            if (curText.isSelected()) {
//                curText.setSelected(false);
//                curText.setTextColor(Color.RED);
//            }
//            else {
//                curText.setTextColor(Color.BLUE);
//                v.setSelected(true);
//            }

        }
    };

}
