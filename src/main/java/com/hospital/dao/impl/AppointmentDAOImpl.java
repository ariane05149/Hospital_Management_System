package com.hospital.dao.impl;

import com.hospital.dao.AppointmentDAO;
import com.hospital.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveAppointment(Appointment appointment) {

        String sql = """
        INSERT INTO appointments
        (doctor_id,patient_id,appointment_date,status)
        VALUES (?,?,?,?)
        """;

        jdbcTemplate.update(
                sql,
                appointment.getDoctorId(),
                appointment.getPatientId(),
                appointment.getAppointmentDate(),
                appointment.getStatus()
        );
    }

    @Override
    public void updateStatus(int appointmentId, String status) {

        String sql = "UPDATE appointments SET status=? WHERE id=?";

        jdbcTemplate.update(sql, status, appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {

        String sql = "SELECT * FROM appointments WHERE doctor_id=?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Appointment appointment = new Appointment();

            appointment.setId(rs.getInt("id"));
            appointment.setDoctorId(rs.getInt("doctor_id"));
            appointment.setPatientId(rs.getInt("patient_id"));
            appointment.setAppointmentDate(rs.getTimestamp("appointment_date"));
            appointment.setStatus(rs.getString("status"));

            return appointment;
        }, doctorId);
    }
}