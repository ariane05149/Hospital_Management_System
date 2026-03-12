package com.hospital.dao.impl;

import com.hospital.dao.DoctorDAO;
import com.hospital.model.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Repository marks this class
as a database repository component.
*/
@Repository
public class DoctorDAOImpl implements DoctorDAO {

    /*
    JdbcTemplate handles SQL operations.
    Spring injects it automatically.
    */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    Insert doctor into database.
    */
    @Override
    public void saveDoctor(Doctor doctor) {

        String sql = """
        INSERT INTO doctors
        (first_name,last_name,specialty,phone_number,email)
        VALUES (?,?,?,?,?)
        """;

        jdbcTemplate.update(
                sql,
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getSpecialty(),
                doctor.getPhoneNumber(),
                doctor.getEmail()
        );
    }

    /*
    Retrieve all doctors.
    */
    @Override
    public List<Doctor> getAllDoctors() {

        String sql = "SELECT * FROM doctors";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Doctor doctor = new Doctor();

            doctor.setId(rs.getInt("id"));
            doctor.setFirstName(rs.getString("first_name"));
            doctor.setLastName(rs.getString("last_name"));
            doctor.setSpecialty(rs.getString("specialty"));
            doctor.setPhoneNumber(rs.getString("phone_number"));
            doctor.setEmail(rs.getString("email"));

            return doctor;
        });
    }

    /*
    Retrieve doctor by id.
    */
    @Override
    public Doctor getDoctorById(int id) {

        String sql = "SELECT * FROM doctors WHERE id=?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

            Doctor doctor = new Doctor();

            doctor.setId(rs.getInt("id"));
            doctor.setFirstName(rs.getString("first_name"));
            doctor.setLastName(rs.getString("last_name"));
            doctor.setSpecialty(rs.getString("specialty"));
            doctor.setPhoneNumber(rs.getString("phone_number"));
            doctor.setEmail(rs.getString("email"));

            return doctor;

        }, id);
    }
}