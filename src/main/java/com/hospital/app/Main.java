package com.hospital.app;

import com.hospital.dao.*;
import com.hospital.db.DatabaseSetup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.hospital");

        DatabaseSetup dbSetup = context.getBean(DatabaseSetup.class);


        DoctorDAO doctorDAO = context.getBean(DoctorDAO.class);

        context.close();
    }
}