package com.shitot.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shitot.interfaces.Constants;
import com.shitot.json.Doctor;
import com.shitot.json.Patient;
import com.shitot.json.Problems;
import com.shitot.json.Symptoms;
import com.shitot.json.Treatment;
import com.shitot.json.User;
import com.shitot.utils.Utils;

@Controller
@RequestMapping("/")
@Scope("session")
public class ShitotFesController {
	boolean authorized = false;
	boolean authorizeDoctor = false;
	User loggerUser;
	Doctor doctor;
	private RestTemplate rest = new RestTemplate();
	private ObjectMapper om = new ObjectMapper();
//	{
//		List<HttpMessageConverter<?>> messageConverters=new ArrayList<>();
//		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//		rest.setMessageConverters(messageConverters);
//	}
	/*
	 * @RequestMapping({ "/", "home" }) String home() { return "home"; }
	 */

	// Customer Page
	@RequestMapping({ "/", "home" })
	String home(Model model) throws IOException {
		String page = "home";
		model.addAttribute("page", page);
		if (loggerUser != null)
			model.addAttribute("loggedUser", loggerUser.getName());
		else
			model.addAttribute("loggedUser", "Not logged in");
		return "index";
	}

	@RequestMapping("loginUserForm")
	public String loginUserForm() {
		return "loginUserForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("loginUserAction")
	String loginUser(String name, String password, Model model) throws IOException {
		User requestUser = new User();
		requestUser.setName(name);
		requestUser.setPassword(password);
		Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_LOGIN, requestUser,
				Map.class);
		this.loggerUser = om.convertValue(response.get("data"), User.class);
		String message = om.convertValue(response.get("result"), String.class);
		if (loggerUser != null) {
			authorized = true;
			return loggedUserHomePage(model, message);
		}
		model.addAttribute("logError", message);
		return "loginUserForm";
	}

	@RequestMapping("logout")
	String logOut(Model model) throws IOException {
		authorized = false;
		String page = "home";
		model.addAttribute("page", page);
		model.addAttribute("loggedUser", "Not logged in");
		return "index";
	}

	@RequestMapping("addUserForm")
	public String addUserForm(Model model) {
		if (!authorized)
			return notAuthorized(model);
		return "addUserForm";
	}

	private String notAuthorized(Model model) {
		model.addAttribute("page", "home");
		model.addAttribute("loggedUser", "Not logged in");
		return "index";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addUserAction")
	String addUser(Model model, String name, String password) {
		if (!authorized)
			return notAuthorized(model);
		User newUser = new User(name, password);
		Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_CREATE_USER, newUser,
				Map.class);
		String message = om.convertValue(response.get("result"), String.class);
		return loggedUserHomePage(model, message);
	}

	private String loggedUserHomePage(Model model, String message) {
		model.addAttribute("logError", message);
		model.addAttribute("page", "home");
		model.addAttribute("loggedUser", loggerUser.getName());
		return "index";
	}

	@RequestMapping("addDoctorForm")
	public String addDoctorForm(Model model) {
		if (!authorized)
			return notAuthorized(model);
		return "addDoctorForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addDoctorAction")
	String addDoctor(Model model, String nameLogin, String password, String telNumber, String telHouse, String email,
			String address, String specialty, String targetAudience, String otherSpecialty, String preferential,
			String expert, String certification, String lectors, String comments) {
		if (!authorized)
			return notAuthorized(model);
		Doctor doctor = new Doctor(nameLogin, password, telNumber, telHouse, email, address, specialty, targetAudience,
				otherSpecialty, preferential, expert, certification, lectors, comments);
		Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_CREATE_DOCTOR, doctor,
				Map.class);
		String message = om.convertValue(response.get("result"), String.class);
		return loggedUserHomePage(model, message);
	}

	@RequestMapping("addPatientForm")
	public String addPatientForm(Model model) {
		if (!authorized)
			return notAuthorized(model);
		return "addPatientForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addPatientAction")
	String addPatient(Model model, String id, String name, String telNumber, String ageStr, String description)
			throws ParseException {
		if (!authorized)
			return notAuthorized(model);
		int age = Integer.parseInt(ageStr);
		int patientId = Integer.parseInt(id);
		Patient pat = new Patient(patientId, name, telNumber, age, description);
		Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_CREATE_PATIENT, pat,
				Map.class);
		String message = om.convertValue(response.get("result"), String.class);
		return loggedUserHomePage(model, message);
	}

	@RequestMapping("addSymptomsForm")
	public String addSymptomsForm(Model model){
		if (!authorized)
			return notAuthorized(model);
		return "addSymptomsForm";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("addSymptom")
	public String addSymptoms(Model model, String symptomStr){
		if (!authorized)
			return notAuthorized(model);
		Map<String,Object> response = rest
				.postForObject(Constants.URI+Constants.REQUEST_CREATE_SYMPTOM, new Symptoms(symptomStr), Map.class);
		model.addAttribute("result",om.convertValue(response.get("result"), String.class));
		return "addSymptomsForm";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("addProblemForm")
	public String addProblemForm(Model model) {
		if (!authorized)
			return notAuthorized(model);
		Map<String, Object> response = rest.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_SYMPTOMS, Map.class);
		Object fromValue = response.get("data");
		String json = "";
		try {
			json = om.writeValueAsString(fromValue);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("symptomsList", json);
		return "addProblemForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addProblemAction")
	String addProblem(Model model, String problemName, String description, Integer[] symptoms)
			throws ParseException {
		if (!authorized)
			return notAuthorized(model);
		String message = null;
		List<Symptoms> symptomsList=new ArrayList<>();
		for (int symptomId : symptoms) {
			symptomsList.add(new Symptoms(symptomId));
		}
		Problems prob = new Problems(problemName,description,symptomsList);
		Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_CREATE_PROBLEM, prob,
				Map.class);
		message = om.convertValue(response.get("result"), String.class);
		return loggedUserHomePage(model, message);
	}

	@RequestMapping("addTreatmentForm")
	public String addTreatmentForm(Model model) {
		if (!authorized)
			return notAuthorized(model);
		return "addTreatmentForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addTreatmentAction")
	String addTreatment(Model model, boolean isAlternativeDoctor, String infSourse, Date dateMeeting, String patientId,
			String intervalId) throws ParseException {
		if (!authorized)
			return notAuthorized(model);
		/* List<Problems> problems, Doctor doctor, Doctor alternativeDoctor, */
		Date dateApplication = new Date();
		System.out.println("DM " + dateMeeting.toString());
		System.out.println("DA " + dateApplication.toString());
		List<Symptoms> symptoms = new LinkedList<Symptoms>();
		for (int i = 2; i < 8; i++)
			symptoms.add(new Symptoms(i));
		List<Problems> problems = new LinkedList<Problems>();
		Problems probl = new Problems();
		probl.setNameProblem("problem2");
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
		treat.setNameUser(loggerUser.getName());
		treat.setProblems(problems);
		System.out.println("treat " + treat.toString());
		String message = null;
		Map<String, Object> response = rest.postForObject(
				Constants.URI + Constants.REQUEST_CREATE_TREATMENT + "/" + patientId + "/" + intervalId, treat,
				Map.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("errors", message);
		return "home";
		/*
		 * model.addAttribute("errors", "Name no good"); return "home";
		 */
	}

	@RequestMapping("getAllPatientForm")
	public String getAllPatientForm(Model model) {
		/**
		 * must open a new page with patients table with option to select each
		 * patient and to inspect his data such as dates of appointments,
		 * doctors info, payments etc...
		 */
		if (!authorized)
			return notAuthorized(model);
		return "getAllPatientForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getAllPatientAction")
	private String getAllPatient(Model model) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = rest.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_PATIENT, Map.class);
		Patient patients[] = om.convertValue(response.get("data"), Patient[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("patients", Utils.getJson(patients));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		System.out.println(response.toString());
		for (Patient p : patients)
			System.out.println(p.toString());
		return "index";
	}

	@RequestMapping("getAllDoctorForm")
	public String getAllDoctorForm() {
		return "getAllDoctorForm";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getAllDoctorAction")
	private String getAllDoctors(Model model) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = rest.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_DOCTOR, Map.class);
		Doctor doctors[] = om.convertValue(response.get("data"), Doctor[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("doctors", Utils.getJson(doctors));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		for (Doctor doc : doctors)
			System.out.println(doc.toString());
		return "index";
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
			response = rest.getForObject(Constants.URI + Constants.REQUEST_GET_PATIENT_BY_ID + "/" + patId, Map.class);
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
			Map<String, Object> response = rest
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
		Map<String, Object> response = rest
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
		Map<String, Object> response = rest
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
		Map<String, Object> response = rest
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
		Map<String, Object> response = rest.getForObject(
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
		Map<String, Object> response = rest.getForObject(Constants.URI + Constants.REQUEST_GET_PATIENT_NOT_PAYMENT,
				Map.class);
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

	@SuppressWarnings({ "unchecked" })
	@RequestMapping("getAllSymptomAction")
	private String getAllSymptom(Model model) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = rest.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_SYMPTOMS, Map.class);
		Symptoms symptoms[] = om.convertValue(response.get("data"), Symptoms[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("symptoms", Utils.getJson(symptoms));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		for (Symptoms s : symptoms)
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
		Map<String, Object> response = rest
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
		Map<String, Object> response = rest.getForObject(
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
		Map<String, Object> response = rest.getForObject(
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
		Map<String, Object> response = rest.getForObject(
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
	private String getStatisticBySymptoms(Model model, List<Symptoms> symptoms) throws IOException {
		if (!authorized) {
			String page = "loginUserForm";
			model.addAttribute("page", page);
			return "index";
		}
		String page = "home";
		String message = null;
		Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_GET_STATISTIC_BY_SYMPTOMS,
				symptoms, Map.class);
		Map<String, Integer> res = om.convertValue(response.get("data"), Map.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("sum", res);
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		return "index";
	}

}
