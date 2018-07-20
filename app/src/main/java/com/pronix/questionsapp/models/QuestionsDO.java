package com.pronix.questionsapp.models;

import java.util.ArrayList;

public class QuestionsDO {

    public String qId;
    public String question;
    public String option1;
    public String option2;
    public String option3;
    public String option4;

    private String subject;
    private String majorTopic;
    private String minorTopic1;
    private String minorTopic2;
    private String minortop;
    private String difficultyLevel;
    private String prevExam1;
    private String prevExam2;
    private String prevExam3;
    private String type;
    private String answer;
    private String solution;
    private String formula;
    private String hint;
    private String selectedAnswer;
    private boolean isCorrectAnswer;
    public ArrayList<OptionsDO> options;

    public ArrayList<OptionsDO> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<OptionsDO> options) {
        this.options = options;
    }

    public String getQuestionId() {
        return qId;
    }

    public void setQuestionId(String questionId) {
        this.qId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajorTopic() {
        return majorTopic;
    }

    public void setMajorTopic(String majorTopic) {
        this.majorTopic = majorTopic;
    }

    public String getMinorTopic1() {
        return minorTopic1;
    }

    public void setMinorTopic1(String minorTopic1) {
        this.minorTopic1 = minorTopic1;
    }

    public String getMinorTopic2() {
        return minorTopic2;
    }

    public void setMinorTopic2(String minorTopic2) {
        this.minorTopic2 = minorTopic2;
    }

    public String getMinortop() {
        return minortop;
    }

    public void setMinortop(String minortop) {
        this.minortop = minortop;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getPrevExam1() {
        return prevExam1;
    }

    public void setPrevExam1(String prevExam1) {
        this.prevExam1 = prevExam1;
    }

    public String getPrevExam2() {
        return prevExam2;
    }

    public void setPrevExam2(String prevExam2) {
        this.prevExam2 = prevExam2;
    }

    public String getPrevExam3() {
        return prevExam3;
    }

    public void setPrevExam3(String prevExam3) {
        this.prevExam3 = prevExam3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }
}
