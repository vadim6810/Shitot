package com.shitot.repository;

import java.util.*;

import com.shitot.model.*;
import com.shitot.to.*;

public interface ShitotRepository {

   // From office
	User loginUser(User user);
    boolean createUser(User user);
    boolean createDoctor(Doctor doctor);
    boolean createPatient(Patient patient);
    boolean createProblems(Problem problem);
    boolean createTreatment(Treatment treatment, int patientId, int intervalId);
    boolean addSymptom(Symptom symptom);
   
    
    // From Doctor
    Doctor loginDoctor(Doctor doctor);
    boolean updateClinic(Clinic clinic);
    boolean updatePatientByDoctor(Treatment treatment);
    boolean createClinic(Clinic clinic);
    List<SlotDAO> createSlot(List<Slot> slots);
    List<Patient> getPatientByPeriod(int doctorId, Date startDate, Date endDate);
    
    
    //Requests
    List<Patient> getAllPatient();
    List<Doctor> getAllDoctors();
    List<Clinic> getAllClinic();
    List<Symptom> getAllSymptoms();
    List<Clinic> getAllClinicByDoctor(int doctorId);
    List<Clinic> getAllClinicByCity(String city);
    List<Doctor> getDoctorByClinicCity(String city);
	List<Doctor> getDoctorBySpecialty(String specialty);
	
    Patient getPatient(int patientId);
    Doctor getDoctor(int doctorId);
    Patient getPatientIdByName(String patientName);
    Doctor getDoctorByName(String doctorName);
    
    List<Patient> getPatientByDoctor(int doctorId);
    List<Patient> getPatientByDoctorNotPayment(int doctorId);
	List<Patient> getPatientNotPayment();
	int getSumPatientByPeriod(Date startDate, Date endDate);
	int getStatisticBySymptom(String symptom);
	List<Patient> getPatientNotMeeting();
	int getPatientByDay(Date date);
	int getCalledPatientByDay(Date date);
	int getTherapyPatientByDay(Date date);
	int getPaymentPatientByDay(Date date);
	Map<String, Integer> getStatisticBySymptoms(List<Symptom> symptoms);
	List<Interval> getFreeIntervalByDoctor(int doctorId);
	List<Interval> getFreeIntervalByCity(String city);
	List<Problem> getAllProblems();

   
    
    

}
