package com.hospital.dao;

import com.hospital.model.MedicalRecord;
import java.util.List;

public interface MedicalRecordDAO {

    void saveRecord(MedicalRecord record);

    List<MedicalRecord> getRecordsByPatient(int patientId);

}