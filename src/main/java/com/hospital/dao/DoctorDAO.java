package com.hospital.dao;

import com.hospital.model.Doctor;
import java.util.List;

public interface DoctorDAO {

    void saveDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();
    Doctor getDoctorById(int id);

}