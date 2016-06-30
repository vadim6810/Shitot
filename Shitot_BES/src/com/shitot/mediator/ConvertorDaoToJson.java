package com.shitot.mediator;

import java.util.*;
import java.util.stream.Collectors;

import com.shitot.dao.*;
import com.shitot.json.*;

public class ConvertorDaoToJson {

	public static List<Patient> convertPatients(List<PatientDAO> patientsDao) {
		if (patientsDao == null)
			return null;
		List<Patient> patientsJson = new LinkedList<Patient>();
		for (PatientDAO patientDao : patientsDao) {
			Patient p = convertPatient(patientDao);
			patientsJson.add(p);
		}
		return patientsJson;
	}

	public static Patient convertPatient(PatientDAO patientDao) {
		if (patientDao == null)
			return null;
		Patient patientJson = new Patient();
		patientJson.setId(patientDao.getId());
		patientJson.setAge(patientDao.getAge());
		patientJson.setName(patientDao.getName());
		patientJson.setTelNumber(patientDao.getTelNumber());
		patientJson.setDescription(patientDao.getDescription());
		patientJson.setTreatments(convetTreatmens(patientDao.getTreatments()));
		return patientJson;
	}

	public static List<Treatment> convetTreatmens(List<TreatmentDAO> treatments) {
		if (treatments == null)
			return null;
		List<Treatment> treatmensJson = new LinkedList<Treatment>();
		for (TreatmentDAO tr : treatments) {
			Treatment t = convertTreatment(tr);
			treatmensJson.add(t);
		}
		return treatmensJson;
	}

	public static Treatment convertTreatment(TreatmentDAO tr) {
		if (tr == null)
			return null;
		Treatment treatmentJson = new Treatment();
		treatmentJson.setId(tr.getId());
		treatmentJson.setDateMeeting(tr.getDateMeeting());
		treatmentJson.setDatePayment(tr.getDatePayment());
		treatmentJson.setPayment(tr.getPayment());
		treatmentJson.setCheckNumber(tr.getCheckNumber());
		// treatmentJson.setDoctor(convertDoctor(tr.getDoctor()));
		treatmentJson.setProblems(convertProblems(tr.getProblems()));
		treatmentJson.setIsAlternativeDoctor(tr.isAlternativeDoctor());
		// treatmentJson.setAlternativeDoctor(convertDoctor(tr.getAlternativeDoctor()));
		treatmentJson.setInfSourse(tr.getInfSourse());
		treatmentJson.setNameUser(tr.getNameUser());
		treatmentJson.setDateApplication(tr.getDateApplication());
		treatmentJson.setDescription(tr.getDescription());
		return treatmentJson;
	}

	public static List<Problem> convertProblems(List<ProblemDAO> problemsDao) {
		if (problemsDao == null)
			return null;
		return problemsDao.stream().map(p -> convertProblem(p)).collect(Collectors.toList());
		// List<Problem> problemsJson = new LinkedList<Problem>();
		// for (ProblemDAO problemDao : problemsDao) {
		// Problem p = convertProblem(problemDao);
		// problemsJson.add(p);
		// }
		// return problemsJson;
	}

	public static Problem convertProblem(ProblemDAO problemDao) {
		if (problemDao == null)
			return null;
		Problem problemJson = new Problem();
		problemJson.setId(problemDao.getId());
		problemJson.setNameProblem(problemDao.getName());
		problemJson.setDescription(problemDao.getDescription());
		problemJson.setSymptoms(convertSymptoms(problemDao.getSymptoms()));
		return problemJson;
	}

	public static List<Doctor> convertDoctors(List<DoctorDAO> doctorsDao) {
		if (doctorsDao == null)
			return null;
		return doctorsDao.stream().map(d -> convertDoctor(d)).collect(Collectors.toList());
		// List<Doctor> doctorsJson = new LinkedList<Doctor>();
		// for (DoctorDAO doctorDao : doctorsDao) {
		// Doctor d = convertDoctor(doctorDao);
		// doctorsJson.add(d);
		// }
		// return doctorsJson;
	}

	public static Doctor convertDoctor(DoctorDAO doctorDao) {
		if (doctorDao == null)
			return null;
		Doctor doctorJson = new Doctor(doctorDao.getAddress(), doctorDao.getCertification(), doctorDao.getComments(),
				doctorDao.getName(), doctorDao.getEmail(), doctorDao.getExpert(), doctorDao.getLectors(),
				doctorDao.getOtherSpecialty(), doctorDao.getPassword(), doctorDao.getPreferential(),
				doctorDao.getSpecialty(), doctorDao.getTargetFromAge(), doctorDao.getTargetGender(),
				doctorDao.getTargetToAge(), doctorDao.getTelHouse(), doctorDao.getTelNumber());
		return doctorJson;
	}

	public static List<Clinic> convertClinics(List<ClinicDAO> clinicsDao) {
		if (clinicsDao == null)
			return null;
		List<Clinic> clinicsJson = new LinkedList<Clinic>();
		for (ClinicDAO clinicDao : clinicsDao) {
			Clinic cl = convertClinic(clinicDao);
			clinicsJson.add(cl);
		}
		return clinicsJson;
	}

	public static Clinic convertClinic(ClinicDAO clinicDao) {
		if (clinicDao == null)
			return null;
		Clinic clinicJson = new Clinic();
		clinicJson.setId(clinicDao.getId());
		clinicJson.setCity(clinicDao.getCity());
		clinicJson.setAddressClinic(clinicDao.getAddressClinic());
		// clinicJson.setDoctors(convertDoctor(clinicDao.getDoctors()));
		clinicJson.setSlots(convertSlots(clinicDao.getSlots()));
		return clinicJson;
	}

	public static List<Slot> convertSlots(List<SlotDAO> slotsDao) {
		if (slotsDao == null)
			return null;
		List<Slot> slotsJson = new LinkedList<Slot>();
		for (SlotDAO slotDao : slotsDao) {
			Slot s = convertSlot(slotDao);
			slotsJson.add(s);
		}
		return slotsJson;
	}

	public static Slot convertSlot(SlotDAO slotDao) {
		if (slotDao == null)
			return null;
		Slot slotJson = new Slot();
		slotJson.setId(slotDao.getId());
		slotJson.setDate(slotDao.getDate());
		slotJson.setIntervals(convertIntervals(slotDao.getIntervals()));

		return slotJson;
	}

	public static List<Interval> convertIntervals(List<IntervalDAO> intervalsDao) {
		if (intervalsDao == null)
			return null;
		List<Interval> intervalsJson = new LinkedList<Interval>();
		for (IntervalDAO intervalDao : intervalsDao) {
			Interval i = convertInterval(intervalDao);
			intervalsJson.add(i);
		}
		return intervalsJson;
	}

	public static Interval convertInterval(IntervalDAO intervalDao) {
		if (intervalDao == null)
			return null;
		Interval intervalJson = new Interval();
		intervalJson.setId(intervalDao.getId());
		intervalJson.setHours(intervalDao.getHours());
		intervalJson.setTreatment(convertTreatment(intervalDao.getTreatment()));
		return intervalJson;
	}

	public static List<Symptom> convertSymptoms(List<SymptomDAO> symptomsDao) {
		if (symptomsDao == null)
			return null;
		return symptomsDao.stream().map(s -> convertSymptom(s)).collect(Collectors.toList());
		// List<Symptom> symptomsJson = new LinkedList<Symptom>();
		// for (SymptomDAO symptomDao : symptomsDao) {
		// Symptom s = convertSymptom(symptomDao);
		// symptomsJson.add(s);
		// }
		// return symptomsJson;
	}

	public static Symptom convertSymptom(SymptomDAO symptomDao) {
		if (symptomDao == null)
			return null;
		return new Symptom(symptomDao.getId(), symptomDao.getName());
	}

}
