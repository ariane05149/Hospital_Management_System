package com.hospital.dao.impl;

import com.hospital.dao.PatientDAO;
import com.hospital.model.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDAOImpl implements PatientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void savePatient(Patient patient) {

        String sql = """
        INSERT INTO patients
        (first_name,last_name,date_of_birth,gender,phone_number,email)
        VALUES (?,?,?,?,?,?)
        """;

        jdbcTemplate.update(
                sql,
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDateOfBirth(),
                patient.getGender(),
                patient.getPhoneNumber(),
                patient.getEmail()
        );
    }

    @Override
    public List<Patient> getAllPatients() {

        String sql = "SELECT * FROM patients";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Patient patient = new Patient();

            patient.setId(rs.getInt("id"));
            patient.setFirstName(rs.getString("first_name"));
            patient.setLastName(rs.getString("last_name"));
            patient.setDateOfBirth(rs.getDate("date_of_birth"));
            patient.setGender(rs.getString("gender"));
            patient.setPhoneNumber(rs.getString("phone_number"));
            patient.setEmail(rs.getString("email"));

            return patient;
        });
    }
}