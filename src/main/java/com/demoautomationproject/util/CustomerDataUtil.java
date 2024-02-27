package com.demoautomationproject.util;


import com.demoautomationproject.testdataprovider.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerDataUtil {

    public static Customer getRandomCustomerData() throws IOException {
        List<Customer> customers = new ArrayList<>();
        try (InputStream inputStream = CustomerDataUtil.class.getClassLoader().getResourceAsStream("randomCustomer.csv")) {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    Customer customer = new Customer(data[0], data[1], data[2]);
                    customers.add(customer);
                }
            }
        }
        return customers.get(new Random().nextInt(customers.size()));
    }
}
