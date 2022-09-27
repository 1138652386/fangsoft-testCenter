package org.fangsoft.testcenter.data;

import org.fangsoft.testcenter.model.ChoiceItem;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.Test;

/**
 * @author Jianxin Pei
 * @create 2022-09-23 16:27
 */
public class TestData {
    public static final String RIGHT_CHOICE="#";

    private static final String[][] TEST_QUSETION_LIB = {
            {
                "java知识测试",
                "3",
                "10",
                "www.fangsoft.org的java知识测试",
                "10"
            },
            {
                "有关java语言论述正确的是？",
                "#它是一门编程语言",
                "#它是一个平台",
                "#它是跨平台的",
                "#它是面向对象的"

            },
            {
                "Java学习可以参考的网站有：",
                "#java.sun.com",
                "#www.javaworld.com",
                "#www130.ibm.com",
                "#www.fangsoft.org"
            },
            {
                "如果一个属性声明用private， 下面论述正确的是：",
                "不可变",
                "同步",
                "#封装",
                "代表is-a"
            }
    };


    public static Test produceTest(){
        String[] tds = TEST_QUSETION_LIB[0];
        int numQ = TEST_QUSETION_LIB.length;        //4
        int numQuestion = Integer.parseInt(tds[1]);     //3
        if(numQuestion > (numQ - 1))    numQuestion = numQ - 1;
        Test test = new Test(numQuestion);
        test.setName(tds[0]);
        test.setNumQuestion(numQuestion);
        test.setTimeLimitMin(Integer.parseInt(tds[2]));
        test.setDescription(tds[3]);
        test.setScore(Integer.parseInt(tds[4]));
        int qi = 0;
        while (qi < numQuestion){
            String[] qds = TEST_QUSETION_LIB[qi + 1];
            Question q = new Question();
            q.setName(qds[0]);
            ChoiceItem[] items = new ChoiceItem[qds.length - 1];
            for(int j = 1; j < qds.length; j++){
                ChoiceItem ch = new ChoiceItem();
                String choiceText = qds[j];
                if(choiceText.indexOf(RIGHT_CHOICE) == 0){
                    choiceText = choiceText.substring(1);
                    ch.setCorrect(true);
                }
                ch.setName(choiceText);
                items[j-1] = ch;
            }
            q.setChoiceItem(items);
            q.setScore(1);
            test.addQuestion(q);
            qi++;
        }
        return test;
    }
}
