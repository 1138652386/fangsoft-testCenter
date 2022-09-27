package org.fangsoft.testcenter.model;

import com.sun.source.tree.Scope;

/**
 * @author Jianxin Pei
 * @create 2022-09-23 11:16
 */

public class Question {
    private String name;        //应该是问题描述
    private String answer;      //正确答案
    private int score;          //这道题的分数
    private ChoiceItem[] choiceItem;        //选项

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ChoiceItem[] getChoiceItem() {
        return choiceItem;
    }

    public ChoiceItem getChioceItem(int index){
        return choiceItem[index];
    }


    public void setChoiceItem(ChoiceItem[] choiceItem) {
        this.choiceItem = choiceItem;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Question(int size) {
        choiceItem = new ChoiceItem[size];
    }

    public Question() {
    }

    private int count;

    public void  addChoiceItem(ChoiceItem item){
        choiceItem[count++] = item;
    }





    public void assignLabel(String[] label){
        int count = 0;
        StringBuffer rightanwser = new StringBuffer();
        if(this.choiceItem != null){
            for (ChoiceItem item : this.getChoiceItem() ){
                item.setLabel(label[count++]);
                if(item.isCorrect()){
                    rightanwser.append(label[count - 1]);
                }
            }
        }
        this.setAnswer(rightanwser.toString());
    }


}
