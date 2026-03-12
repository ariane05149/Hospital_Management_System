package com.hospital.dao.impl;

import com.hospital.dao.MedicalRecordDAO;
import com.hospital.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordDAOImpl implements MedicalRecordDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveRecord(MedicalRecord record) {

        String sql = """
        INSERT INTO medical_records
        (patient_id,doctor_id,diagnosis,treatment)
        VALUES (?,?,?,?)
        """;

        jdbcTemplate.update(
                sql,
                record.getPatientId(),
                record.getDoctorId(),
                record.getDiagnosis(),
                record.getTreatment()
        );
    }

    @Override
    public List<MedicalRecord> getRecordsByPatient(int patientId) {

        String sql = "SELECT * FROM medical_records WHERE patient_id=?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            MedicalRecord record = new MedicalRecord();

            record.setId(rs.getInt("id"));
            record.setPatientId(rs.getInt("patient_id"));
            record.setDoctorId(rs.getInt("doctor_id"));
            record.setDiagnosis(rs.getString("diagnosis"));
            record.setTreatment(rs.getString("treatment"));

            return record;
        }, patientId);
    }
}