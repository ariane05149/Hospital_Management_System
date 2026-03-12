package com.hospital.dao;

import com.hospital.model.Appointment;
import java.util.List;

public interface AppointmentDAO {

    void saveAppointment(Appointment appointment);

    void updateStatus(int appointmentId, String status);

    List<Appointment> getAppointmentsByDoctor(int doctorId);

}