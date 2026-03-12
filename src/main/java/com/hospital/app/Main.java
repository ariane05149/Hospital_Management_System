package com.hospital.app;

import com.hospital.dao.*;
import com.hospital.model.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.sql.Timestamp;


public class HospitalApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.hospital");

        DoctorDAO doctorDAO = context.getBean(DoctorDAO.class);
        PatientDAO patientDAO = context.getBean(PatientDAO.class);
        AppointmentDAO appointmentDAO = context.getBean(AppointmentDAO.class);
        MedicalRecordDAO recordDAO = context.getBean(MedicalRecordDAO.class);

        Doctor doctor = new Doctor(
                "John",
                "Smith",
                "Cardiology",
                "078888111",
                "john@hospital.com"
        );

        doctorDAO.saveDoctor(doctor);

        Patient patient = new Patient(
                "Alice",
                "Brown",
                Date.valueOf("1995-04-10"),
                "Female",
                "078888222",
                "alice@mail.com"
        );

        patientDAO.savePatient(patient);

        Appointment appointment = new Appointment(
                1,
                1,
                Timestamp.valueOf("2026-03-20 10:00:00"),
                "Scheduled"
        );

        appointmentDAO.saveAppointment(appointment);

        MedicalRecord record = new MedicalRecord(
                1,
                1,
                "Flu",
                "Medication"
        );

        recordDAO.saveRecord(record);

        System.out.println("Hospital Management System running successfully.");

        context.close();
    }
}