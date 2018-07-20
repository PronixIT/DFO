package com.pronix.questionsapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.pronix.questionsapp.common.Constants;
import com.pronix.questionsapp.common.Utils;
import com.pronix.questionsapp.models.WebServiceDO;
import com.pronix.questionsapp.render.CommonSpinnerAdapter;
import com.pronix.questionsapp.services.AsyncTask;
import com.pronix.questionsapp.services.OnTaskCompleted;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnTaskCompleted{

    ArrayList<String> majorTopicArrayList = new ArrayList<>();
    ArrayList<String> minorTopicArrayList = new ArrayList<>();
    ArrayList<String> difficultyLevelArrayList = new ArrayList<>();
    ArrayList<String> subjectArrayList = new ArrayList<>();
    Spinner sp_Subject, sp_MajorTopic, sp_MinorTopic, sp_DifficultyLevel;
    Dialog customDialog = null;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Selection");
        initializeControls();
        calWebservice();
    }

    public void initializeControls()
    {
        sp_Subject = (Spinner) findViewById(R.id.sp_Subject);
        sp_MajorTopic = (Spinner) findViewById(R.id.sp_MajorTopic);
        sp_MinorTopic = (Spinner) findViewById(R.id.sp_MinorTopic);
        sp_DifficultyLevel = (Spinner) findViewById(R.id.sp_DifficultyLevel);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();


//    }

    public void movetoQuestions(View view)
    {
//        Intent in = new Intent(this, Questions.class);
//        startActivity(in);
        customeDialog();
    }

    public void movetoQuestions1(View view)
    {
        Intent in = new Intent(this, SubjectQuestionsActivity.class);
        String alert = validation();
        if(alert.equals("")) {
            in.putExtra("MAJORTOPIIC", majorTopicArrayList.get(sp_MajorTopic.getSelectedItemPosition()));
            in.putExtra("MINORTOPIC", minorTopicArrayList.get(sp_MinorTopic.getSelectedItemPosition()));
            in.putExtra("SUBJECT", subjectArrayList.get(sp_Subject.getSelectedItemPosition()));
            in.putExtra("DIIFICULTYLEVEL", difficultyLevelArrayList.get(sp_DifficultyLevel.getSelectedItemPosition()));
            startActivity(in);
        }
        else
        {
            Toast.makeText(this, alert, Toast.LENGTH_SHORT).show();
        }
    }
    public String validation()
    {
        String alert = "";
        if(sp_Subject.getSelectedItemPosition() == -1)
        {
            alert = "Select subject option";
        }
        else if(sp_MajorTopic.getSelectedItemPosition() == -1)
        {
            alert = "Select majorTopic option";
        } else if(sp_MinorTopic.getSelectedItemPosition() == -1)
        {
            alert = "Select minorTopic option";
        }
        else if(sp_DifficultyLevel.getSelectedItemPosition() == -1)
        {
            alert = "Select difficulty level";
        }
        return alert;

    }

    public void calWebservice() {
        try {
            if(Utils.isNetworkAvailable(this)) {
                progressDialog(this);
                WebServiceDO webServiceDO = new WebServiceDO();
                JSONObject jsonObject = new JSONObject();
                webServiceDO.result = Constants.SENT;
                webServiceDO.request = "LISt";
                new AsyncTask(MainActivity.this, MainActivity.this, Constants.URLBase + "" + "dropdown", "GET", jsonObject.toString()).execute(webServiceDO);
            }
            else
            {
                Utils.showToastMessage(this, "Please verify network connection");
            }
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }

    }

    @Override
    public void onTaskCompletes(WebServiceDO webServiceDO) {
        try
        {
            Utils.hideProgress(dialog);
            if(webServiceDO.result.equals(Constants.SUCCESS)) {
                JSONObject jsonObject = new JSONObject(webServiceDO.responseContent);
                JSONArray array = jsonObject.getJSONArray("listMajorTopic");
                for(int i = 0; i< array.length(); i++)
                {
                    majorTopicArrayList.add(array.getString(i));
                }

                array = jsonObject.getJSONArray("listMinorTopic");
                for(int i = 0; i< array.length(); i++)
                {
                    minorTopicArrayList.add(array.getString(i));
                }
                array = jsonObject.getJSONArray("listSubject");
                for(int i = 0; i< array.length(); i++)
                {
                    subjectArrayList.add(array.getString(i));
                }
                array = jsonObject.getJSONArray("listDifficultyLevel");
                for(int i = 0; i< array.length(); i++)
                {
                    difficultyLevelArrayList.add(array.getString(i));
                }
//                Type listType = new TypeToken<ListDO>() {
//                }.getType();
//                arrayList = new Gson().fromJson(webServiceDO.responseContent, listType);
//                Toast.makeText(this, arrayList.toString(), Toast.LENGTH_SHORT).show();
                loadSpinners();

            }
            else {
                Toast.makeText(this, webServiceDO.responseContent, Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e)
        {
            Utils.hideProgress(dialog);
            e.getMessage();
        }

    }

    public void loadSpinners()
    {
        CommonSpinnerAdapter adapter = new CommonSpinnerAdapter(this, subjectArrayList);
        sp_Subject.setAdapter(adapter);
        CommonSpinnerAdapter adapter1 = new CommonSpinnerAdapter(this, minorTopicArrayList);
        sp_MinorTopic.setAdapter(adapter1);
        CommonSpinnerAdapter adapter2 = new CommonSpinnerAdapter(this, majorTopicArrayList);
        sp_MajorTopic.setAdapter(adapter2);
        CommonSpinnerAdapter adapter3 = new CommonSpinnerAdapter(this, difficultyLevelArrayList);
        sp_DifficultyLevel.setAdapter(adapter3);
    }

    public void customeDialog() {
        customDialog = new Dialog(this);
        customDialog.setCancelable(false);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.custom_url);
//        Typeface custom = Utils.getFontStyle(getAssets());
        final EditText et_Prescription = (EditText) customDialog.findViewById(R.id.et_Prescription);
        et_Prescription.setText(Constants.URLBase);
        Button but_Submit = (Button) customDialog.findViewById(R.id.but_Submit);
        Button but_Cancel = (Button) customDialog.findViewById(R.id.but_Cancel);
//        ((TextView) customDialog.findViewById(R.id.tv_Header)).setTypeface(custom);
        but_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.URLBase = et_Prescription.getText().toString().trim();
                customDialog.dismiss();
                calWebservice();

            }
        });
        but_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.show();


    }

    public void progressDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progressview);
        dialog.show();
    }




}
