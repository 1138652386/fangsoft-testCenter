package org.fangsoft.testcenter.data;

import org.fangsoft.testcenter.model.Customer;

/**
 * @author Jianxin Pei
 * @create 2022-09-23 16:01
 */
public class CustomerData {
    public static String[][] CUSTOMER_DATA = {
            {
                "jianxin",       //userId
                "123",                     //password
                "fangsoft.org@gmail.com",        //email
            },
            {
                "fangsoft.java@gmail.com",       //userId
                "fangsoft",                     //password
                "fangsoft.java@gmail.com",        //email
            }
    };

    private static final Customer[] customers;

    static {
        int size = CUSTOMER_DATA.length;
        customers = new Customer[size];
        for(int i = 0; i < size; i++){
            String[] cust = CUSTOMER_DATA[i];
            Customer c = new Customer();
            c.setUserId(cust[0]);
            c.setPassword(cust[1]);
            c.setEmail(cust[2]);
            customers[i]= c;
        }
    }

    public static Customer[] getCustomers(){
        return customers;
    }


}
