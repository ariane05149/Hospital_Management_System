package com.hospital.dao;

import com.hospital.model.Patient;
import java.util.List;

public interface PatientDAO {
    void savePatient(Patient patient);

    List<Patient> getAllPatients();

}