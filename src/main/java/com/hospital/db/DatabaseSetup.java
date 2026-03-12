package com.hospital.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseSetup {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createTables(){

        String doctorsTable = """
            CREATE TABLE IF NOT EXISTS doctors(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(100),
                last_name VARCHAR(100),
                specialty VARCHAR(100),
                phone_number VARCHAR(20),
                email VARCHAR(150) UNIQUE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        String patientsTable = """
            CREATE TABLE IF NOT EXISTS patients(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(100),
                last_name VARCHAR(100),
                date_of_birth DATE,
                gender VARCHAR(10),
                phone_number VARCHAR(20),
                email VARCHAR(150) UNIQUE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        String appointmentsTable = """
            CREATE TABLE IF NOT EXISTS appointments(
                id SERIAL PRIMARY KEY,
                doctor_id INT REFERENCES doctors(id) ON DELETE CASCADE,
                patient_id INT REFERENCES patients(id) ON DELETE CASCADE,
                appointment_date TIMESTAMP,
                status VARCHAR(20),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        String medicalRecords = """
            CREATE TABLE IF NOT EXISTS medical_records(
                id SERIAL PRIMARY KEY,
                patient_id INT REFERENCES patients(id) ON DELETE CASCADE,
                doctor_id INT REFERENCES doctors(id) ON DELETE CASCADE,
                diagnosis TEXT,
                treatment TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        String doctorPatient = """
            CREATE TABLE IF NOT EXISTS doctor_patient(
                doctor_id INT REFERENCES doctors(id) ON DELETE CASCADE,
                patient_id INT REFERENCES patients(id) ON DELETE CASCADE,
                PRIMARY KEY(doctor_id, patient_id)
            )
        """;

        String index = """
            CREATE INDEX IF NOT EXISTS idx_appointment_date
            ON appointments(appointment_date)
        """;

        jdbcTemplate.execute(doctorsTable);
        jdbcTemplate.execute(patientsTable);
        jdbcTemplate.execute(appointmentsTable);
        jdbcTemplate.execute(medicalRecords);
        jdbcTemplate.execute(doctorPatient);
        jdbcTemplate.execute(index);

        System.out.println("All tables created successfully.");
    }
}