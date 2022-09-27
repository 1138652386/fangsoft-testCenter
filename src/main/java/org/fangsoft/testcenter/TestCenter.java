package org.fangsoft.testcenter;

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.data.CustomerData;
import org.fangsoft.testcenter.data.TestData;
import org.fangsoft.testcenter.model.*;
import org.fangsoft.util.Console;

import java.io.OutputStream;
import java.lang.annotation.Repeatable;
import java.util.Date;

import static org.fangsoft.util.Console.*;

/**
 * @author Jianxin Pei
 * @create 2022-09-23 17:33
 */
public class TestCenter {


    private static Customer findCustomer(String userId, String password){
        Customer[] customers = CustomerData.getCustomers();
        for(int i = 0; i < customers.length; i++){
            Customer c = customers[i];
            if(c.getUserId().equals(userId) && c.getPassword().equals(password)){
                return customers[i];
            }
        }
        return null;
    }

    public static Customer login(){
        output("Please log in before taking the exam, and press enter to confirm after entering");
        for(int i = 0; i < Configuration.MAX_LOGIN; i++){
            String userId = prompt("Enter user name:");
            String password = prompt("Enter user password:");
            Customer customer = findCustomer(userId, password);
            if(customer != null){
                output("Welcome " + customer.getUserId() + "Welcome fangsoft Test Center");
                return customer;
            }
            output("User name or password error, log in again"+"login"+Configuration.MAX_LOGIN+"times Unsuccessful times, the system will exit，"+"This is " +(i+1)+"times");
        }
        return null;
    }

    private static void exit(Object msg){
        output(msg);
        System.exit(1);
    }

    private static TestResult newTestResult(Customer c, Test test){
        TestResult tr = new TestResult();
        tr.setCustomer(c);
        tr.setTest(test);
        tr.setStartTtime(new Date());
        tr.setQuestionResult(new QuestionResult[test.getQuestion().length]);
        int count = 0;
        for(Question q:test.getQuestion()){
            q.assignLabel(Configuration.CHOICE_LABEL);
            QuestionResult qr = new QuestionResult();
            qr.setQuestion(q);
            tr.getQuestionResult()[count++] = qr;
        }
        return tr;
    }

    public static String presentQuestion(int pos, Question q){
        Console.output("%1$s.%2$s%n", pos, q.getName());
        for(ChoiceItem item : q.getChoiceItem()){
            Console.output("%1$s.%2$s%n", item.getLabel(),item.getName());
        }
        Console.output("Output answers");
        return Console.read();
    }

    public static TestResult takeTest(Test test, Customer customer){
        output("=======Begin the exam=======");
        output("Examination name：%1$5s%n" +
                    "Examination description：%2$5s%n" +
                    "Examination problem：%3$5s%n" +
                    "Examination time：%4$5s%n",
                test.getName(),
                test.getDescription(),
                test.getNumQuestion(),
                test.getTimeLimitMin());
        long start = System.currentTimeMillis();
        output("Watch out for you and %1$s minites, The time is：%2$tT%n", test.getTimeLimitMin(),start);
        TestResult tr = newTestResult(customer, test);
        int count = 0;
        for(QuestionResult qr:tr.getQuestionResult()){
            String answers = presentQuestion(++count, qr.getQuestion());
            qr.setAnswer(answers);
        }
        long end = System.currentTimeMillis();
        tr.setEndTime(new Date(end));
        tr.commoitTest();
        output("The exam is over. Now it's time：%1$tT%n", end);
        return tr;
    }

    public static void reportTestResult(TestResult tr){
        output("===========Examination report==========");
        long duration = (tr.getEndTime().getTime() - tr.getStarrTtime().getTime()) / (1000 * 60);
        output("Your take %1$s minites test%n", duration);
        output("%1$-6s%2$-6s%3$-6s%4$-6s%n", "QuestionNum", "Your answer", "Correct answer", "result");
        int count = 0;
        for(QuestionResult qr:tr.getQuestionResult()){
            output("%1$-8s%2$-8s%3$-8s%4$-8s%n",
                        ++count,
                    qr.getAnswer(),
                    qr.getQuestion().getAnswer(),
                    qr.getResult() ? "right" : "wrong");
        }

        boolean pass = tr.getPass() > 0 ? true :false;
        String displayPass="";
        if(pass) displayPass = "Congratulations, you passed the exam.";
        else displayPass = "I'm sorry you didn't pass the exam";
//        output("Your test score is: ",tr.getScore(), ",", displayPass);
        System.out.println("Your test score is: " + tr.getScore() + ", " + displayPass);

    }

    public static void welcome(){
        output("Today is：" + Configuration.getDateFormat().format(new Date()));
        output("Your operating system is：" + System.getProperty("os,name"));
    }

    public static void main(String[] args) {
        welcome();
        Customer customer = login();
        if(customer == null){
            exit("User name error, unable to log in, system exit");
        }
        boolean response  = promptYesNo("Are you sure to take the exam?", "y","yes:y","no,exit:n");
        if(!response) exit("System exit");
        Test test = TestData.produceTest();
        TestResult tr = takeTest(test, customer);
        reportTestResult(tr);

    }
}
