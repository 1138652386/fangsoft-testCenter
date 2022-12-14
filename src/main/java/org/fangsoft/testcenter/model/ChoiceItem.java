package org.fangsoft.testcenter.model;

/**
 * @author Jianxin Pei
 * @create 2022-09-23 11:12
 */

public class ChoiceItem {
    private String name;
    private boolean correct;
    private String label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
