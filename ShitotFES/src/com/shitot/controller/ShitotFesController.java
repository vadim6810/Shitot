package com.shitot.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shitot.interfaces.Constants;
import com.shitot.to.Doctor;
import com.shitot.to.Patient;
import com.shitot.to.Problem;
import com.shitot.to.Symptom;
import com.shitot.to.Treatment;
import com.shitot.to.User;
import com.shitot.utils.Utils;

@Controller
@RequestMapping("/")
@Scope("session")
public class ShitotFesController {
	protected boolean authorized = false;
//	protected boolean authorizeDoctor = false;
	protected User loggedUser;
	// protected Doctor doctor;
	protected RestTemplate restTemplate = new RestTemplate();
	protected ObjectMapper om = new ObjectMapper();

	protected String index(Model model, String page) {
		if (authorized)
			model.addAttribute("loggedUser", loggedUser.getName());
		model.addAttribute("page", page);
		return "index";
	}
	// Customer Page
	@RequestMapping({ "/", "home" })
	public String home(Model model) throws IOException {
		return index(model, authorized? "home": "loginUserForm");
	}

	@RequestMapping("loginUserForm")
	public String loginUserForm(Model model) {
		if (authorized)
			model.addAttribute("cancel", "<a href=\"home\">cancel</a>");
		return index(model, "loginUserForm");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("loginUserAction")
	String loginUser(String name, String password, Model model) throws IOException {
		User requestUser = new User();
		requestUser.setName(name);
		requestUser.setPassword(password);
		Map<String, Object> response = restTemplate.postForObject(Constants.URI + Constants.REQUEST_LOGIN, requestUser,
				Map.class);
		requestUser = om.convertValue(response.get("data"), User.class);
		model.addAttribute("message", om.convertValue(response.get("result"), String.class));
		if (requestUser != null) {
			authorized = true;
			loggedUser = requestUser;
			return index(model, "home");
		}
		return index(model, "loginUserForm");
	}

	@RequestMapping("logout")
	String logOut(Model model) throws IOException {
		authorized = false;
		return index(model, "loginUserForm");
	}

	@RequestMapping("addUserForm")
	public String addUserForm(Model model) {
		if (!authorized)
			return index(model, "loginUserForm");
		return index(model, "addUserForm");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addUserAction")
	String addUser(Model model, String name, String password) {
		if (!authorized)
			return index(model, "loginUserForm");
		User newUser = new User(name, password);
		Map<String, Object> response = restTemplate.postForObject(Constants.URI + Constants.REQUEST_CREATE_USER,
				newUser, Map.class);
		model.addAttribute("message", om.convertValue(response.get("result"), String.class));
		return index(model, "home");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addDoctorForm")
	public String addDoctorForm(Model model, String action, Integer id) {
		if (!authorized)
			return index(model, "loginUserForm");
		Doctor doctor = new Doctor();
		if ("update".equals(action)) {
			Map<String, Object> response = restTemplate
					.getForObject(Constants.URI + Constants.REQUEST_GET_DOCTOR_BY_ID + "/" + id, Map.class);
			doctor = om.convertValue(response.get("data"), Doctor.class);
		}
		Map<String, Object> response = restTemplate.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_PROBLEMS,
				Map.class);
		List<String> problems = om.convertValue(response.get("data"), new TypeReference<List<String>>() {});
		model.addAttribute("doctor", doctor);
		model.addAttribute("problems", problems);
		return index(model, "addDoctorForm");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addDoctorAction")
	String addDoctor(Model model, Integer id, String doctorName, String password, String email, String telNumber,
			String telHouse, String address, String specialty, String otherSpecialty, Integer targetFromAge,
			Integer targetToAge, Integer targetMale, Integer targetFemale, String preferential, String expert,
			String certification, String lectors, String comments) {
		if (!authorized)
			return index(model, "loginUserForm");
		if (targetFromAge == null)
			targetFromAge = 0;
		if (targetToAge == null || targetToAge == 0)
			targetToAge = 200;
		if (targetMale == null)
			targetMale = 0;
		if (targetFemale == null)
			targetFemale = 0;
		int targetGender = (targetMale + targetFemale) % 3;
		Doctor doctor = new Doctor(address, certification, comments, doctorName, email, expert, lectors, otherSpecialty,
				password, preferential, specialty, targetFromAge, targetGender, targetToAge, telHouse, telNumber);
		if (id != null)
			doctor.setId(id);
		Map<String, Object> response = restTemplate.postForObject(Constants.URI + Constants.REQUEST_CREATE_DOCTOR,
				doctor, Map.class);
		String message = om.convertValue(response.get("result"), String.class);
		model.addAttribute("resAddDoctor", message);
		return index(model, "home");
	}

	@RequestMapping("addSymptomForm")
	public String addSymptomsForm(Model model) {
		if (!authorized)
			return index(model, "loginUserForm");
		return index(model, "addSymptomForm");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addSymptomAction")
	public String addSymptoms(Model model, String symptomStr) {
		if (!authorized)
			return index(model, "loginUserForm");
		Map<String, Object> response = restTemplate.postForObject(Constants.URI + Constants.REQUEST_CREATE_SYMPTOM,
				new Symptom(symptomStr), Map.class);
		model.addAttribute("result", om.convertValue(response.get("result"), String.class));
		return index(model, "addSymptomsForm");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addProblemForm")
	public String addProblemForm(Model model) {
		if (!authorized)
			return index(model, "loginUserForm");
		Map<String, Object> response = restTemplate.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_SYMPTOMS,
				Map.class);
		List<Symptom> symptoms = om.convertValue(response.get("data"), new TypeReference<List<Symptom>>() {
		});
		model.addAttribute("symptoms", symptoms);
		return index(model, "addProblemForm");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addProblemAction")
	String addProblem(Model model, HttpServletRequest request, @RequestParam Map<String, String> params) {
		if (!authorized)
			return index(model, "loginUserForm");
		String problemName = params.get("problemName");
		String description = params.get("description");
		List<Symptom> symptoms = params.entrySet().stream().filter(e -> "on".equals(e.getValue()))
				.map(e -> new Symptom(Integer.parseInt(e.getKey()))).collect(Collectors.toList());
		Problem prob = new Problem(problemName, description, symptoms);
		Map<String, Object> response = restTemplate.postForObject(Constants.URI + Constants.REQUEST_CREATE_PROBLEM,
				prob, Map.class);
		String message = om.convertValue(response.get("result"), String.class);
		model.addAttribute("resAddProbl", message);
		return addProblemForm(model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("getAllDoctorsAction")
	private String getAllDoctors(Model model) throws IOException {
		if (!authorized)
			return index(model, "loginUserForm");
		Map<String, Object> response = restTemplate.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_DOCTOR,
				Map.class);
		List<Doctor> data = om.convertValue(response.get("data"), new TypeReference<List<Doctor>>() {
		});
		model.addAttribute("doctorsList", data);
		return index(model, "viewDoctors");
	}
		
	
//////////////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping("addPatientForm")
	public String addPatientForm(Model model) {
		if (!authorized)
			return index(model, "loginUserForm");
		return index(model, "addPatientForm");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addPatientAction")
	String addPatient(Model model, String id, String name, String telNumber, String ageStr, String description)
			throws ParseException {
		if (!authorized)
			return index(model, "loginUserForm");
		int age = Integer.parseInt(ageStr);
		int patientId = Integer.parseInt(id);
		Patient pat = new Patient(patientId, name, telNumber, age, description);
		Map<String, Object> response = restTemplate.postForObject(Constants.URI + Constants.REQUEST_CREATE_PATIENT, pat,
				Map.class);
		String message = om.convertValue(response.get("result"), String.class);
		return index(model, message);
	}

	@RequestMapping("addTreatmentForm")
	public String addTreatmentForm(Model model) {
		if (!authorized)
			return index(model, "loginUserForm");
		return index(model, "addTreatmentForm");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addTreatmentAction")
	String addTreatment(Model model, boolean isAlternativeDoctor, String infSourse, Date dateMeeting, String patientId,
			String intervalId) throws ParseException {
		if (!authorized)
			return index(model, "loginUserForm");
		/* List<Problems> problems, Doctor doctor, Doctor alternativeDoctor, */
		Date dateApplication = new Date();
		System.out.println("DM " + dateMeeting.toString());
		System.out.println("DA " + dateApplication.toString());
		List<Symptom> symptoms = new LinkedList<Symptom>();
		for (int i = 2; i < 8; i++)
			symptoms.add(new Symptom(i));
		List<Problem> problems = new LinkedList<Problem>();
		Problem probl = new Problem();
		probl.setName("problem2");
		probl.setDescription("descr Probl");
		probl.setSymptoms(symptoms);
		problems.add(probl);
		// System.out.println("prob "+probl.toString());
		Doctor doc = new Doctor();
		doc.setId(1);
		System.out.println("Doc " + doc);
		Treatment treat = new Treatment();
		treat.setIsAlternativeDoctor(isAlternativeDoctor);
		treat.setDateApplication(dateApplication);
		treat.setDateMeeting(dateMeeting);
		treat.setDoctor(doc);
		treat.setInfSourse(infSourse);
		treat.setNameUser(loggedUser.getName());
		treat.setProblems(problems);
		System.out.println("treat " + treat.toString());
		String message = null;
		Map<String, Object> response = restTemplate.postForObject(
				Constants.URI + Constants.REQUEST_CREATE_TREATMENT + "/" + patientId + "/" + intervalId, treat,
				Map.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("errors", message);
		return "home";
		/*
		 * model.addAttribute("errors", "Name no good"); return "home";
		 */
	}

	/**
	 * must open a new page with patients table with option to select each
	 * patient and to inspect his data such as dates of appointments, doctors
	 * info, payments etc...
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getAllPatientsAction")
	private String getAllPatient(Model model) throws IOException {
		if (!authorized)
			return index(model, "loginUserForm");
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_PATIENT,
				Map.class);
		Patient patients[] = om.convertValue(response.get("data"), Patient[].class);
		message = om.convertValue(response.get("result"), String.class);
		model.addAttribute("patients", Utils.getJson(patients));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		System.out.println(response.toString());
		for (Patient p : patients)
			System.out.println(p.toString());
		return index(model, "patients");
	}

	@RequestMapping("getPatientByIdForm")
	public String getPatientByIdForm() {
		return "getPatientByIdForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("getPatientByIdAction")
	private String getPatientById(Model model, String patientId) throws IOException {
		if (!authorized) {
			String page = "start";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response;
		int patId;
		try {
			patId = Integer.parseInt(patientId);
		} catch (NumberFormatException e) {
			model.addAttribute("page", page);
			model.addAttribute("errors", "id not number");
			return "index";
		}
		if (patId > 0) {
			response = restTemplate.getForObject(Constants.URI + Constants.REQUEST_GET_PATIENT_BY_ID + "/" + patId,
					Map.class);
			Patient patient = om.convertValue(response.get("data"), Patient.class);
			message = om.convertValue(response.get("message"), String.class);
			model.addAttribute("patient", Utils.getJson(patient));
			model.addAttribute("page", page);
			model.addAttribute("errors", message);
			return "index";
		}
		model.addAttribute("page", page);
		model.addAttribute("errors", "id not correct");
		return "index";
	}

	@RequestMapping("getPatientByNameForm")
	public String getPatientByNameForm() {
		return "getPatientByNameForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("getPatientByNameAction")
	private String getPatientByName(Model model, String patientName) throws IOException {
		if (!authorized) {
			String page = "start";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		if (patientName != null) {
			Map<String, Object> response = restTemplate
					.getForObject(Constants.URI + Constants.REQUEST_GET_PATIENT_BY_NAME + "/" + patientName, Map.class);
			Patient patients[] = om.convertValue(response.get("data"), Patient[].class);
			message = om.convertValue(response.get("message"), String.class);
			model.addAttribute("patients", Utils.getJson(patients));
			model.addAttribute("page", page);
			model.addAttribute("errors", message);
			return "index";
		}
		model.addAttribute("page", page);
		model.addAttribute("errors", "name not correct");
		return "index";
	}

	@RequestMapping("getDoctorByIdForm")
	public String getDoctorByIdForm() {
		return "getDoctorByIdForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getDoctorByIdAction")
	private String getDoctorById(Model model, String doctorIdStr) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		int doctorId;
		try {
			doctorId = Integer.parseInt(doctorIdStr);
		} catch (NumberFormatException e) {
			model.addAttribute("page", page);
			model.addAttribute("errors", "id not number");
			return "index";
		}
		String message = null;
		Map<String, Object> response = restTemplate
				.getForObject(Constants.URI + Constants.REQUEST_GET_DOCTOR_BY_ID + "/" + doctorId, Map.class);
		Doctor doctors = om.convertValue(response.get("data"), Doctor.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("doctors", Utils.getJson(doctors));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		return "index";
	}

	@RequestMapping("getDoctorByNameForm")
	public String getDoctorByNameForm() {
		return "getDoctorByNameForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getDoctorByNameAction")
	private String getDoctorByName(Model model, String doctorName) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate
				.getForObject(Constants.URI + Constants.REQUEST_GET_DOCTOR_BY_NAME + "/" + doctorName, Map.class);
		Doctor doctors[] = om.convertValue(response.get("data"), Doctor[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("doctors", Utils.getJson(doctors));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		return "index";
	}

	@RequestMapping("getAllDoctorByCityForm")
	public String getAllDoctorByCityForm() {
		return "getAllDoctorByCityForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getAllDoctorByCityAction")
	private String getAllDoctorByCity(Model model, String city) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate
				.getForObject(Constants.URI + Constants.REQUEST_GET_DOCTOR_BY_CITY + "/" + city, Map.class);
		Doctor doctors[] = om.convertValue(response.get("data"), Doctor[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("doctors", Utils.getJson(doctors));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		return "index";
	}

	@RequestMapping("getAllDoctorBySpecializationForm")
	public String getAllDoctorBySpecializationForm() {
		return "getAllDoctorBySpecializationForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getAllDoctorBySpecializationAction")
	private String getAllDoctorBySpecialization(Model model, String specialization) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate.getForObject(
				Constants.URI + Constants.REQUEST_GET_DOCTOR_BY_SPECIALIZATION + "/" + specialization, Map.class);
		Doctor doctors[] = om.convertValue(response.get("data"), Doctor[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("doctors", Utils.getJson(doctors));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		for (Doctor doc : doctors)
			System.out.println(doc.toString());
		return "index";
	}

	@RequestMapping("getPatientNotPaymentForm")
	public String getPatientNotPaymentForm() {
		return "getPatientNotPaymentForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getPatientNotPaymentAction")
	private String getPatientNotPayment(Model model) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate
				.getForObject(Constants.URI + Constants.REQUEST_GET_PATIENT_NOT_PAYMENT, Map.class);
		Patient patients[] = om.convertValue(response.get("data"), Patient[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("patients", Utils.getJson(patients));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		for (Patient pat : patients)
			System.out.println(pat.toString());
		return "index";
	}

	@RequestMapping("getAllSymptomForm")
	public String getAllSymptomForm() {
		return "getAllSymptomForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("getAllSymptomAction")
	private String getAllSymptom(Model model) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_SYMPTOMS,
				Map.class);
		Symptom symptoms[] = om.convertValue(response.get("data"), Symptom[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("symptoms", Utils.getJson(symptoms));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		for (Symptom s : symptoms)
			System.out.println(s.toString());
		return "index";
	}

	@RequestMapping("getPatientByDoctorForm")
	public String getPatientByDoctorForm() {
		return "getPatientByDoctorForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getPatientByDoctorAction")
	private String getPatientByDoctor(Model model, String doctorIdStr) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		int doctorId;
		try {
			doctorId = Integer.parseInt(doctorIdStr);
		} catch (NumberFormatException e) {
			model.addAttribute("page", page);
			model.addAttribute("errors", "id not number");
			return "index";
		}
		String message = null;
		Map<String, Object> response = restTemplate
				.getForObject(Constants.URI + Constants.REQUEST_GET_PATIENT_BY_DOCTOR + "/" + doctorId, Map.class);
		Patient patients[] = om.convertValue(response.get("data"), Patient[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("patients", Utils.getJson(patients));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		for (Patient p : patients)
			System.out.println(p.toString());
		return "index";
	}

	@RequestMapping("getPatientByDoctorNotPaymentForm")
	public String getPatientByDoctorNotPaymentForm() {
		return "getPatientByDoctorNotPaymentForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getPatientByDoctorNotPaymentAction")
	private String getPatientByDoctorNotPayment(Model model, String doctorIdStr) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		int doctorId;
		try {
			doctorId = Integer.parseInt(doctorIdStr);
		} catch (NumberFormatException e) {
			model.addAttribute("page", page);
			model.addAttribute("errors", "id not number");
			return "index";
		}
		String message = null;
		Map<String, Object> response = restTemplate.getForObject(
				Constants.URI + Constants.REQUEST_GET_PATIENT_BY_DOCTOR_NOT_PAYMENT + "/" + doctorId, Map.class);
		Patient patients[] = om.convertValue(response.get("data"), Patient[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("patients", Utils.getJson(patients));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		for (Patient p : patients)
			System.out.println(p.toString());
		return "index";
	}

	@RequestMapping("getSumPatientByPeriodForm")
	public String getSumPatientByPeriodForm() {
		return "getSumPatientByPeriodForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getSumPatientByPeriodAction")
	private String getSumPatientByPeriod(Model model, String startDateStr, String endDateStr) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate.getForObject(
				Constants.URI + Constants.REQUEST_GET_SUM_PATIENT_BY_PERIOD + "/" + startDateStr + "/" + endDateStr,
				Map.class);
		int res = om.convertValue(response.get("data"), Integer.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("sum", res);
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		return "index";
	}

	@RequestMapping("getStatisticBySymptomForm")
	public String getStatisticBySymptomForm() {
		return "getStatisticBySymptomForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getStatisticBySymptomAction")
	private String getStatisticBySymptom(Model model, String nameSymptom) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate.getForObject(
				Constants.URI + Constants.REQUEST_GET_STATISTIC_BY_SYMPTOM + "/" + nameSymptom, Map.class);
		int res = om.convertValue(response.get("data"), Integer.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("sum", res);
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		return "index";
	}

	@RequestMapping("getStatisticBySymptomsForm")
	public String getStatisticBySymptomsForm() {
		return "getStatisticBySymptomsForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getStatisticBySymptomsAction")
	private String getStatisticBySymptoms(Model model, List<Symptom> symptoms) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = restTemplate
				.postForObject(Constants.URI + Constants.REQUEST_GET_STATISTIC_BY_SYMPTOMS, symptoms, Map.class);
		Map<String, Integer> res = om.convertValue(response.get("data"), Map.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("sum", res);
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		return "index";
	}

}
