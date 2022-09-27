package org.fangsoft.testcenter.model;

/**
 * @author Jianxin Pei
 * @create 2022-09-23 15:14
 */
public class QuestionResult {
    private Question question;
    private int score = DEFAULT_SCORE;
    private String answer;          //考生的答案
    private boolean result;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }


    //如何判断一道题是否答对
    private static final int DEFAULT_SCORE = -1;

    public int computeAnswer(){
        if(this.score != DEFAULT_SCORE){
            return this.score;
        }

        if(answer == null || this.answer.length() == 0){                //这道题没有答
            this.result = false;
            this.score = 0;
        }
        else if(this.question == null || this.question.getAnswer() == null){    //这道题没有设定正确答案或没有这道题
            this.result = false;
            this.score = 0;
        }
        else if(this.question.getAnswer().equals(this.answer)){         //答案正确
            this.result = true;
            this.score = this.getScore();
            if(this.question.getScore() < 0){
                this.score = 1;
            }
        }
        else if(! this.question.getAnswer().equals(this.answer)){       //答案不对
            this.result = false;
            this.score = 0;
        }
        return this.score;
    }


}
