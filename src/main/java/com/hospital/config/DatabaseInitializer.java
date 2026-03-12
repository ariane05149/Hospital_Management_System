package com.hospital.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createTables() {
        String doctors = """
            CREATE TABLE IF NOT EXISTS doctors(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(100),
                last_name VARCHAR(100),
                specialty VARCHAR(100),
                phone_number VARCHAR(20),
                email VARCHAR(150) UNIQUE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """;
        String patients = """
            CREATE TABLE IF NOT EXISTS patients(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(100),
                last_name VARCHAR(100),
                date_of_birth DATE,
                gender VARCHAR(10),
                phone_number VARCHAR(20),
                email VARCHAR(150) UNIQUE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """;
        String appointments = """
            CREATE TABLE IF NOT EXISTS appointments(
                id SERIAL PRIMARY KEY,
                doctor_id INT REFERENCES doctors(id),
                patient_id INT REFERENCES patients(id),
                appointment_date TIMESTAMP,
                status VARCHAR(20),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """;
        String medicalRecords = """
            CREATE TABLE IF NOT EXISTS medical_records(
                id SERIAL PRIMARY KEY,
                patient_id INT REFERENCES patients(id),
                doctor_id INT REFERENCES doctors(id),
                diagnosis TEXT,
                treatment TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """;

        jdbcTemplate.execute(doctors);
        jdbcTemplate.execute(patients);
        jdbcTemplate.execute(appointments);
        jdbcTemplate.execute(medicalRecords);
        System.out.println("All tables created successfully.");
    }
}