package com.pronix.questionsapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pronix.questionsapp.common.Constants;
import com.pronix.questionsapp.common.Utils;
import com.pronix.questionsapp.models.OptionsDO;
import com.pronix.questionsapp.models.QuestionsDO;
import com.pronix.questionsapp.models.WebServiceDO;
import com.pronix.questionsapp.services.AsyncTask;
import com.pronix.questionsapp.services.OnTaskCompleted;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SubjectQuestionsActivity extends AppCompatActivity implements OnTaskCompleted {

    TextView tv_Question, tv_CheckAnswer, tv_CorrectAnswer, tv_Solution;
    Button but_Previous, but_Next, but_Check, but_Submit;
    static ArrayList<QuestionsDO> arrayList = new ArrayList<>();
    LinearLayout ll_Solution;
    RadioGroup radioGroup;
    int questionNo = -1;
    Dialog dialog;
    String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    String[] question = {"Which of the following is used in pencils?", "The gas usually filled in the electric bulb is", "Bromine is a"};
    String[] o1 = {"Graphite", "Silicon", "Charcoal", "Phosphorous"};
    String[] o2 = {"nitrogen", "hydrogen", "carbon dioxide", "oxygen", "oxygen"};
    String[] o3 = {"black solid", "red liquid", "colourless gas", "highly inflammable gas"};

    String jsonData = "[{\"question\":\"What is Activity\",\"qId\":\"001\",\"options\":[{\"optionsId\":\"O1\",\"optionName\":\"Answerr01\"},{\"optionsId\":\"O1\",\"optionName\":\"Answerr02\"},{\"optionsId\":\"O1\",\"optionName\":\"Answerr03\"},{\"optionsId\":\"O1\",\"optionName\":\"Answerr04\"}]}, {\"question\":\"Which of the following is used in pencils?\",\"qId\":\"002\",\"options\":[{\"optionsId\":\"O1\",\"optionName\":\"Graphite\"},{\"optionsId\":\"O1\",\"optionName\":\"Graphite\"},{\"optionsId\":\"O1\",\"optionName\":\"Charcoal\"},{\"optionsId\":\"O1\",\"optionName\":\"Phosphorous\"}]}, {\"question\":\"The gas usually filled in the electric bulb is\",\"qId\":\"003\",\"options\":[{\"optionsId\":\"O1\",\"optionName\":\"nitrogen\"},{\"optionsId\":\"O1\",\"optionName\":\"hydrogen\"},{\"optionsId\":\"O1\",\"optionName\":\"carbon dioxide\"},{\"optionsId\":\"O1\",\"optionName\":\"oxygen\"}, {\"optionsId\":\"O1\",\"optionName\":\"potacium\"}]}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_questions);
        initializecontrols();
//        loadData();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Questions");
        sendRequest();
    }

    public void initializecontrols() {

        tv_Question = (TextView) findViewById(R.id.tv_Question);
        tv_CheckAnswer = (TextView) findViewById(R.id.tv_CheckAnswer);
        tv_CorrectAnswer = (TextView) findViewById(R.id.tv_CorrectAnswer);
        tv_Solution = (TextView) findViewById(R.id.tv_Solution);
        radioGroup = (RadioGroup) findViewById(R.id.rg_RadioGroup);
        ll_Solution = (LinearLayout) findViewById(R.id.ll_Solution);
        ll_Solution.setVisibility(View.GONE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                Toast.makeText(getBaseContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        but_Next = (Button) findViewById(R.id.but_Next);
        but_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int id = radioGroup.getCheckedRadioButtonId();
//                Toast.makeText(SubjectQuestionsActivity.this, id+"", Toast.LENGTH_LONG).show();
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
        but_Check = (Button) findViewById(R.id.but_Check);
        but_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableSolution();
            }
        });
        but_Submit = (Button) findViewById(R.id.but_Submit);
        but_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SubjectQuestionsActivity.this, Result.class);
                startActivity(in);
            }
        });
    }

    public void enableSolution() {
        ll_Solution.setVisibility(View.VISIBLE);

    }

    public void loadData() {
        QuestionsDO obj;

//        for (int i = 0; i<question.length; i++)
//        {
//            obj = new QuestionsDO();
//            obj.setQuestion(question[i]);
//
//
////            obj.setOption1(option1[i]);
////            obj.setOption2(option2[i]);
////            obj.setOption3(option3[i]);
////            obj.setOption4(option4[i]);
//            arrayList.add(obj);
//        }

        Type listType = new TypeToken<List<QuestionsDO>>() {
        }.getType();
        arrayList = new Gson().fromJson(jsonData, listType);
        Toast.makeText(this, arrayList.toString(), Toast.LENGTH_SHORT).show();
    }

    public void nextRecord() {


        questionNo++;
        if((questionNo + 1) == (arrayList.size()))
            but_Submit.setVisibility(View.VISIBLE);
        if (questionNo != arrayList.size()) {
            but_Check.setVisibility(View.GONE);
            ll_Solution.setVisibility(View.GONE);
            tv_Question.setText((questionNo + 1) + ". " + arrayList.get(questionNo).getQuestion());
            tv_CorrectAnswer.setText("Correct Answer: " + arrayList.get(questionNo).getAnswer());
            tv_Solution.setText("Solution: " + arrayList.get(questionNo).getSolution());
            QuestionsDO questionsDO = arrayList.get(questionNo);
            addRadioButtons(questionsDO);
        } else {
            Toast.makeText(this, "This is last Question", Toast.LENGTH_SHORT).show();
            questionNo--;
        }

    }

    public void previousRecord() {
        questionNo--;
        but_Submit.setVisibility(View.INVISIBLE);
        if (questionNo != -1 && questionNo != -2) {
            but_Check.setVisibility(View.GONE);
            ll_Solution.setVisibility(View.GONE);
            tv_Question.setText((questionNo + 1) + ". " + arrayList.get(questionNo).getQuestion());
            QuestionsDO questionsDO = arrayList.get(questionNo);
            addRadioButtons(questionsDO);

        } else {
            Toast.makeText(this, "1st Question", Toast.LENGTH_SHORT).show();
            questionNo++;
        }

    }

    RadioGroup ll;

    public void addRadioButtons(final QuestionsDO questionsDO) {
        radioGroup.removeAllViews();
        int selectedPos = -1;
        for (int row = 0; row < 1; row++) {
            ll = new RadioGroup(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    but_Check.setVisibility(View.VISIBLE);
                    RadioButton radioButton = (RadioButton) findViewById(checkedId);
                    questionsDO.setSelectedAnswer(alphabets[radioButton.getId()]);
                    if (alphabets[radioButton.getId()].equals(questionsDO.getAnswer())) {
                        tv_CheckAnswer.setText("Correct");
                        questionsDO.setCorrectAnswer(true);
                        tv_CheckAnswer.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        tv_CheckAnswer.setText("Wrong");
                        questionsDO.setCorrectAnswer(false);
                        tv_CheckAnswer.setTextColor(getResources().getColor(R.color.red));
                    }
//                    Toast.makeText(getBaseContext(), "Id- " +radioButton.getId() + " - " + radioButton.getText() + "-Answer:" + questionsDO.getAnswer(), Toast.LENGTH_SHORT).show();

                }
            });

            for (int i = 1; i <= questionsDO.getOptions().size(); i++) {
                RadioButton rdbtn = new RadioButton(this);
                ArrayList<OptionsDO> list = questionsDO.getOptions();
//                rdbtn.setLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                rdbtn.setId((row * 2) + i);
                rdbtn.setId((i - 1));
//                rdbtn.setTag((i-1) == 0 ? "A" : (i-1) == 0);
//                String[] values;
//                if (questionNo == 0)
//                    values = o1;
//                else if (questionNo == 1)
//                    values = o2;+
//                else
//                    values = o3;

                rdbtn.setText(list.get((i - 1)).getOptionName());
                if (alphabets[(i - 1)].equals(questionsDO.getSelectedAnswer()))
                    selectedPos = (i - 1);
//                rdbtn.setText(values[(i-1)]);
                ll.addView(rdbtn);
            }
            radioGroup.addView(ll);
            if (selectedPos != -1) {
                View view = ll.getChildAt(selectedPos);
                ll.check(view.getId());
            }


        }

    }

    public void sendRequest() {
        try {
            if (Utils.isNetworkAvailable(this)) {
                progressDialog(this);
                WebServiceDO webServiceDO = new WebServiceDO();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("subject", getIntent().getStringExtra("SUBJECT"));
                jsonObject.put("majorTopic", getIntent().getStringExtra("MAJORTOPIIC"));
                jsonObject.put("minorTopic", getIntent().getStringExtra("MINORTOPIC"));
                jsonObject.put("difficultyLevel", getIntent().getStringExtra("DIIFICULTYLEVEL"));
                webServiceDO.result = Constants.SENT;
                webServiceDO.request = "QUESTIONS";
                new AsyncTask(SubjectQuestionsActivity.this, SubjectQuestionsActivity.this, Constants.URLBase + "" + "questions\n", "POST", jsonObject.toString()).execute(webServiceDO);
            } else {
                Utils.showToastMessage(this, "Please verify network connection");
            }
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    @Override
    public void onTaskCompletes(WebServiceDO webServiceDO) {

        try {
            Utils.hideProgress(dialog);
            if (webServiceDO.result.equals(Constants.SUCCESS)) {
                if (webServiceDO.request.equals("QUESTIONS")) {
                    Type listType = new TypeToken<List<QuestionsDO>>() {
                    }.getType();
                    arrayList = new Gson().fromJson(webServiceDO.responseContent, listType);
                    nextRecord();
                }
            } else {
//                    Utils.showalert(this, "Alert", webServiceDO.responseContent);
//                    Utils.hideProgress(spotDialog);
                Toast.makeText(this, webServiceDO.responseContent, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }

    }

    public void progressDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progressview);
        dialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
