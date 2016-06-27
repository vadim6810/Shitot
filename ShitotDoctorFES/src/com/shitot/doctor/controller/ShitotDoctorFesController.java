package com.shitot.doctor.controller;

import java.io.IOException;
import java.text.*;
import java.util.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shitot.doctor.interfaces.Constants;
import com.shitot.doctor.json.*;
import com.shitot.doctor.utils.Utils;

@Controller
@RequestMapping("/")
@Scope("session")
public class ShitotDoctorFesController {
	boolean authorized = false;
	boolean authorizeDoctor = false;
	User user;
	Doctor doctor;
	private RestTemplate rest = new RestTemplate();
	private ObjectMapper om = new ObjectMapper();

	@RequestMapping({ "/", "home" })
	String home() {
		return "home";
	}

	@RequestMapping("logout")
	String logOut(Model model) throws IOException {
		authorizeDoctor = false;
		String page = "home";
		// user = new User(0);
		model.addAttribute("page", page);
		return "index";
	}

	// Doctor Page
	@RequestMapping("home")
	String home(Model model) throws IOException {
		String page = "home";
		model.addAttribute("page", page);
		return "index";
	}

	@RequestMapping("loginDoctorForm")
	public String loginDoctorForm() {
		return "loginDoctorForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("loginDoctorAction")
	String loginDoctor(String nameLogin, String password, Model model) throws IOException {
		String page = "loginDoctorForm";
		String message = null;
		if (nameLogin != null && password != null && nameLogin.length() > 0 && password.length() > 0) {
			Doctor requestDoctor = new Doctor();
			requestDoctor.setNameLogin(nameLogin);
			requestDoctor.setPassword(password);
			Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_LOGIN_DOCTOR,
					requestDoctor, Map.class);
			doctor = om.convertValue(response.get("data"), Doctor.class);
			message = om.convertValue(response.get("message"), String.class);
			if (doctor != null) {
				if (doctor.getId() > 0) {
					authorizeDoctor = true;
					page = "home";
				}
			}
			model.addAttribute("page", page);
			model.addAttribute("logError", message);
		}
		model.addAttribute("page", page);
		model.addAttribute("logError", Constants.ERROR_LOGIN);
		return "index";
	}

	@RequestMapping("addClinicForm")
	public String addClinicForm() {
		return "addClinicForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addClinicAction")
	String addClinic(Model model, String city,
			String addressClinic/* , List<Slot> slots */) throws ParseException {
		if (!authorizeDoctor) {
			String page = "home";
			model.addAttribute("page", page);
			return "index";
		}
		String message = null;
		List<Slot> slots = new LinkedList<Slot>();
		Date d1, d2, d3;
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		d1 = format.parse("11-06-2016");
		d2 = format.parse("12-06-2016");
		d3 = format.parse("14-06-2016");
		List<Interval> intervals = new LinkedList<Interval>();
		Interval i1 = new Interval("09.00-10.00");
		Interval i2 = new Interval("11.00-12.00");
		Interval i3 = new Interval("14.00-15.00");
		Interval i4 = new Interval("15.00-16.00");
		intervals.add(i1);
		intervals.add(i2);
		intervals.add(i3);
		intervals.add(i4);
		Slot sl1 = new Slot(d1, intervals);
		Slot sl2 = new Slot(d2, intervals);
		Slot sl3 = new Slot(d3, intervals);
		slots.add(sl1);
		slots.add(sl2);
		slots.add(sl3);

		System.out.println("doc " + this.doctor.toString());
		Clinic clinic = new Clinic(city, addressClinic, slots);
		clinic.setDoctor(this.doctor);
		// clinic.setSlots(slots);
		Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_CREATE_CLINIC, clinic,
				Map.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("errors", message);
		return "home";

		/*
		 * model.addAttribute("errors", "Name or password empty"); return
		 * "home";
		 */
	}

	@RequestMapping("updateClinicForm")
	public String updateClinicForm() {
		return "updateClinicForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("updateClinicAction")
	String updateClinic(Model model, String clinicIdStr) throws ParseException {
		if (!authorizeDoctor) {
			String page = "home";
			model.addAttribute("page", page);
			return "index";
		}
		String message = null;
		int clinicId;
		try {
			clinicId = Integer.parseInt(clinicIdStr);
		} catch (NumberFormatException e) {
			model.addAttribute("errors", "Id no number");
			return "home";
		}
		List<Slot> slots = new LinkedList<Slot>();
		Date d1, d2, d3;
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		d1 = format.parse("15-06-2016");
		d2 = format.parse("17-06-2016");
		d3 = format.parse("19-06-2016");
		List<Interval> intervals = new LinkedList<Interval>();
		Interval i1 = new Interval("09.00-10.00");
		Interval i2 = new Interval("11.00-12.00");
		Interval i3 = new Interval("14.00-15.00");
		Interval i4 = new Interval("15.00-16.00");
		intervals.add(i1);
		intervals.add(i2);
		intervals.add(i3);
		intervals.add(i4);
		Slot sl1 = new Slot(d1, intervals);
		Slot sl2 = new Slot(d2, intervals);
		Slot sl3 = new Slot(d3, intervals);
		slots.add(sl1);
		slots.add(sl2);
		slots.add(sl3);

		Clinic clinic = new Clinic();
		clinic.setId(clinicId);
		clinic.setSlots(slots);
		// clinic.setDoctor(this.doctor);
		// clinic.setSlots(slots);
		Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_UPDATE_CLINIC, clinic,
				Map.class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("errors", message);
		return "home";

		/*
		 * model.addAttribute("errors", "Name or password empty"); return
		 * "home";
		 */
	}

	@RequestMapping("updatePatientForm")
	public String updatePatientForm() {
		return "updatePatientForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("updatePatientAction")
	String updatePatient(Model model, String treatmentIdStr, String datePaymentStr, String paymentStr,
			String checkNumber, String description) throws ParseException {
		if (!authorizeDoctor) {
			String page = "home";
			model.addAttribute("page", page);
			return "index";
		}
		int payment, treatmentId;
		Date datePayment;
		try {
			payment = Integer.parseInt(paymentStr);
			treatmentId = Integer.parseInt(treatmentIdStr);
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			datePayment = format.parse(datePaymentStr);
		} catch (NumberFormatException e) {
			model.addAttribute("errors", "Payment no number");
			return "home";
		}

		if (treatmentId != 0) {
			String message = null;
			Treatment treat = new Treatment();
			treat.setId(treatmentId);
			treat.setCheckNumber(checkNumber);
			treat.setDatePayment(datePayment);
			treat.setDescription(description);
			treat.setPayment(payment);
			System.out.println("treat " + treat);
			Map<String, Object> response = rest.postForObject(Constants.URI + Constants.REQUEST_UPDATE_PATIENT, treat,
					Map.class);
			message = om.convertValue(response.get("message"), String.class);
			model.addAttribute("errors", message);
			return "home";
		}
		model.addAttribute("errors", "Name no good");
		return "home";
	}

	@RequestMapping("getPatientByPeriodForm")
	public String getPatientByPeriodForm() {
		return "getPatientByPeriodForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("getPatientByPeriodAction")
	String getPatientByPeriod(Model model, String startDateStr, String endDateStr) throws ParseException, IOException {
		if (!authorizeDoctor) {
			String page = "home";
			model.addAttribute("page", page);
			return "index";
		}
		Date startDate, endDate;
		try {
			/*
			 * DateFormat format = new SimpleDateFormat("dd/MM/yyyy"); startDate
			 * = format.parse(startDateStr); endDate = format.parse(endDateStr);
			 */
		} catch (NumberFormatException e) {
			model.addAttribute("errors", "Dates is false");
			return "home";
		}
		String page = "home";
		String message = null;
		int doctorId = this.doctor.getId();
		Map<String, Object> response = rest.getForObject(Constants.URI + Constants.REQUEST_GET_PATIENT_BY_PERIOD + "/"
				+ doctorId + "/" + startDateStr + "/" + endDateStr, Map.class);
		Patient patients[] = om.convertValue(response.get("data"), Patient[].class);
		message = om.convertValue(response.get("message"), String.class);
		model.addAttribute("patients", Utils.getJson(patients));
		model.addAttribute("page", page);
		model.addAttribute("errors", message);
		for (Patient p : patients)
			System.out.println(p.toString());
		return "home";
	}
	/*
	 * @RequestMapping("addPatientForm") public String addPatientForm(){ return
	 * "addPatientForm"; }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping("addPatientAction") String addPatient(Model model, String
	 * name, String telNumber, String ageStr, String doctorName, String
	 * dateApplication, String dateMeeting, String nameProblem, boolean
	 * bBehavior, boolean attention, boolean anxiety, boolean depression,
	 * boolean selfWorth, boolean socialSkills, boolean treatmentPaired, boolean
	 * eating, boolean personality, boolean trauma, boolean parentalGuidance,
	 * boolean pressure, boolean pdd, boolean work) throws ParseException { if
	 * (!authorized) { String page = "home"; model.addAttribute("page", page);
	 * return "index"; } int age; Date dateA, dateM; try { age =
	 * Integer.parseInt(ageStr); DateFormat format = new
	 * SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH); dateA =
	 * format.parse(dateApplication); dateM = format.parse(dateMeeting); } catch
	 * (NumberFormatException e) { model.addAttribute("errors", "Age no number"
	 * ); return "home"; }
	 * 
	 * if (name != null && name.length() > 0) { String message = null; Problems
	 * pobl = new Problems(nameProblem, bBehavior, attention, anxiety,
	 * depression, selfWorth, socialSkills, treatmentPaired, eating,
	 * personality, trauma, parentalGuidance, pressure, pdd, work);
	 * List<Problems> problems = new LinkedList<>(); problems.add(pobl); Patient
	 * pat = new Patient(name, telNumber, age, doctorName, dateA, dateM);
	 * pat.setProblems(problems); Map<String, Object> response =
	 * rest.postForObject(Constants.URI + Constants.REQUEST_CREATE_PATIENT, pat,
	 * Map.class); message = om.convertValue(response.get("message"),
	 * String.class); model.addAttribute("errors", message); return "home"; }
	 * model.addAttribute("errors", "Name no good"); return "home"; }
	 * 
	 * @RequestMapping("getAllPatientForm") public String getAllPatientForm(){
	 * return "getAllPatientForm"; }
	 * 
	 * @SuppressWarnings({ "unchecked" })
	 * 
	 * @RequestMapping("getAllPatientAction") private String getAllPatient(Model
	 * model) throws IOException { if (!authorized) { String page =
	 * "loginUserForm"; model.addAttribute("page", page); return "index"; }
	 * String page = "home"; String message = null; Map<String, Object> response
	 * = rest.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_PATIENT,
	 * Map.class); Patient patients[] = om.convertValue(response.get("data"),
	 * Patient[].class); message = om.convertValue(response.get("message"),
	 * String.class); model.addAttribute("patients", Utils.getJson(patients));
	 * model.addAttribute("page", page); model.addAttribute("errors", message);
	 * System.out.println(response.toString()); for(Patient p: patients)
	 * System.out.println(p.toString()); return "index"; }
	 * 
	 * @RequestMapping("getAllDoctorForm") public String getAllDoctorForm(){
	 * return "getAllDoctorForm"; }
	 * 
	 * 
	 * @SuppressWarnings({ "unchecked" })
	 * 
	 * @RequestMapping("getAllDoctorAction") private String getAllDoctors(Model
	 * model) throws IOException { if (!authorized) { String page =
	 * "loginUserForm"; model.addAttribute("page", page); return "index"; }
	 * String page = "home"; String message = null; Map<String, Object> response
	 * = rest.getForObject(Constants.URI + Constants.REQUEST_GET_ALL_DOCTOR,
	 * Map.class); Doctor doctors[] = om.convertValue(response.get("data"),
	 * Doctor[].class); message = om.convertValue(response.get("message"),
	 * String.class); model.addAttribute("doctors", Utils.getJson(doctors));
	 * model.addAttribute("page", page); model.addAttribute("errors", message);
	 * for(Doctor doc:doctors) System.out.println(doc.toString()); return
	 * "index"; }
	 * 
	 * 
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping("getPatientById") private String getPatientById(Model
	 * model, String patientId) throws IOException { if (!authorized) { String
	 * page = "start"; model.addAttribute("page", page); return "index"; }
	 * String page = "home"; String message = null; Map<String, Object>
	 * response; int patId; try { patId = Integer.parseInt(patientId); } catch
	 * (NumberFormatException e) { model.addAttribute("page", page);
	 * model.addAttribute("errors", "id not number"); return "index"; } if
	 * (patId > 0) { response = rest.getForObject(Constants.URI +
	 * Constants.REQUEST_GET_PATIENT_BY_ID + "/" + patId, Map.class); Patient
	 * patient = om.convertValue(response.get("data"), Patient.class); message =
	 * om.convertValue(response.get("message"), String.class);
	 * model.addAttribute("patient", Utils.getJson(patient));
	 * model.addAttribute("page", page); model.addAttribute("errors", message);
	 * return "index"; } model.addAttribute("page", page);
	 * model.addAttribute("errors", "id not correct"); return "index"; }
	 * 
	 * @RequestMapping("getAllDoctorByCityForm") public String
	 * getAllDoctorByCityForm(){ return "getAllDoctorByCityForm"; }
	 * 
	 * 
	 * @SuppressWarnings({ "unchecked" })
	 * 
	 * @RequestMapping("getAllDoctorByCityAction") private String
	 * getAllDoctorByCity(Model model, String city) throws IOException { if
	 * (!authorized) { String page = "loginUserForm"; model.addAttribute("page",
	 * page); return "index"; } String page = "home"; String message = null;
	 * Map<String, Object> response = rest.getForObject(Constants.URI +
	 * Constants.REQUEST_GET_DOCTOR_BY_CITY+"/"+city, Map.class); Doctor
	 * doctors[] = om.convertValue(response.get("data"), Doctor[].class);
	 * message = om.convertValue(response.get("message"), String.class);
	 * model.addAttribute("doctors", Utils.getJson(doctors));
	 * model.addAttribute("page", page); model.addAttribute("errors", message);
	 * for(Doctor doc:doctors) System.out.println(doc.toString()); return
	 * "index"; }
	 * 
	 * @RequestMapping("getAllDoctorBySpecializationForm") public String
	 * getAllDoctorBySpecializationForm(){ return
	 * "getAllDoctorBySpecializationForm"; }
	 * 
	 * 
	 * @SuppressWarnings({ "unchecked" })
	 * 
	 * @RequestMapping("getAllDoctorBySpecializationAction") private String
	 * getAllDoctorBySpecialization(Model model, String specialization) throws
	 * IOException { if (!authorized) { String page = "loginUserForm";
	 * model.addAttribute("page", page); return "index"; } String page = "home";
	 * String message = null; Map<String, Object> response =
	 * rest.getForObject(Constants.URI +
	 * Constants.REQUEST_GET_DOCTOR_BY_SPECIALIZATION+"/"+specialization,
	 * Map.class); Doctor doctors[] = om.convertValue(response.get("data"),
	 * Doctor[].class); message = om.convertValue(response.get("message"),
	 * String.class); model.addAttribute("doctors", Utils.getJson(doctors));
	 * model.addAttribute("page", page); model.addAttribute("errors", message);
	 * for(Doctor doc:doctors) System.out.println(doc.toString()); return
	 * "index"; }
	 */

}
