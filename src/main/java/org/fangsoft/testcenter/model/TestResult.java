package org.fangsoft.testcenter.model;

import java.util.Date;

/**
 * @author Jianxin Pei
 * @create 2022-09-23 15:40
 */
public class TestResult {
    private Customer customer;
    private int score = UNKNOW_SCORE;
    private Test test;
    private int pass = UNKONW_PASS;
    private Date starrTtime;
    private Date endTime;
    private QuestionResult[] questionResult;

    private static final int UNKNOW_SCORE = -1;
    private static final int UNKONW_PASS = -1;
    private static final int PASS_SUCCESS = 1;
    private static final int PASS_FAILURE = 0;

    //计算分数
    protected int computeScore(){
        if(this.score != UNKNOW_SCORE){
            return this.score;
        }
        if(this.score == UNKNOW_SCORE){
            this.score = 0;
        }
        for(QuestionResult qr : this.questionResult){
            this.score += qr.computeAnswer();
        }
        return this.score;
    }

//    public TestResult() {
//    }

    protected boolean computePass(){
        if(this.pass != UNKONW_PASS){
            return this.pass == PASS_SUCCESS ? true : false;
        }

        int right = 0;
        for(QuestionResult qr : questionResult){
            if(qr.getResult() ){
                right++;
            }
        }
        if(100 * right >= 70 * this.getQuestionResult().length){
            this.pass = PASS_SUCCESS;
            return true;
        }
        else {
            this.pass = PASS_FAILURE;
            return false;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public Date getStarrTtime() {
        return starrTtime;
    }

    public void setStartTtime(Date starrTtime) {
        this.starrTtime = starrTtime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public QuestionResult[] getQuestionResult() {
        return questionResult;
    }

    public void setQuestionResult(QuestionResult[] questionResult) {
        this.questionResult = questionResult;
    }

    public boolean commoitTest() {
        this.computePass();
        return this.computePass();
    }
}
