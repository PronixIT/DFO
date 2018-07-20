package com.pronix.questionsapp.services;


import com.pronix.questionsapp.models.WebServiceDO;

/**
 * Created by ravi on 1/11/2018.
 */

public interface OnTaskCompleted {

    void onTaskCompletes(WebServiceDO webServiceDO);
  }
