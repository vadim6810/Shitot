package com.shitot.doctor.interfaces;

public interface Constants {
	String URI = "http://localhost:8080/shitotbes";

	String ERROR_ADDRESS = "Address empty";
	String ERROR_DOCTORNAME_OR_PATIENTNAME = "doctor Name or patient Name empty or bad";
	String ERROR_LOGIN = "Username or password is wrong";

	String REQUEST_CREATE_ADDRESS = "/requestCreateAddress";
	String REQUEST_CREATE_CLINIC = "/createClinic";
	String REQUEST_CREATE_DOCTOR = "/requestCreateDoctor";
	String REQUEST_CREATE_PATIENT = "/requestCreatePatient";
	String REQUEST_EDIT_CALENDAR = "/editCalendar";
	String REQUEST_GET_ADD_PATIENT_TO_DOCTOR = "/requestGetAddPatientToDoctor";
	String REQUEST_GET_ALL_ADDRESSES = "/requestGetAllAddresses";
	String REQUEST_GET_ALL_DOCTOR = "/requestGetAllDoctor";
	String REQUEST_GET_ALL_PATIENT = "/requestGetAllPatient";
	String REQUEST_GET_CAL = "/getCal";
	String REQUEST_GET_CALENDAR_BY_DOCTOR_ID = "/requestGetCalendarByDoctorId";
	String REQUEST_GET_DOCTOR_BY_CITY = "/getDoctorByCity";
	String REQUEST_GET_DOCTOR_BY_SPECIALIZATION = "/getDoctorBySpecialization";
	String REQUEST_GET_PATIENT_BY_ID = "/requestPatientById";
	String REQUEST_GET_PATIENT_BY_PERIOD = "/requestGetPatientByPeriod";
	String REQUEST_LOGIN = "/login";
	String REQUEST_LOGIN_DOCTOR = "/loginDoctor";
	String REQUEST_UPDATE_CLINIC = "/updateClinic";
	String REQUEST_UPDATE_PATIENT = "/updatePatient";
}

// String REQUEST_CREATE_USER = "/createUser";
// String REQUEST_GET_CALENDARS = "/getCalendars";
// String REQUEST_ADD_PERSON = "/addPerson";
// String REQUEST_GET_CONTACTS = "/getContacts";
// String REQUEST_REPEAT = "/repeat";
// String REQUEST_REMOVE_CALENDAR = "/removeCalendar";
// String REQUEST_CREATE_CALENDAR = "/createCalendar";
// String REQUEST_GET_WEEK = "/getWeek";
// String REQUEST_EDIT_CALENDAR = "/editCalendar";
// String REQUEST_GET_ALL = "/requestGettAll";
// String SLOT_STATUS_FREE = "free";
// int DAY_OF_THE_WEEK = 7;
// String URI = "http://109.202.2.210:37002/bes/";

// int CALENDAR_TYPE_COLLABORATED = 2;
// String SLOT_STATUS_FREE = "free";
// String STATUS_GREEN = "green";
/*
 * String ERROR_EXISTED_USER = "User with current email already exists"; String
 * ERROR_LOGIN = "Username or password is wrong"; String ERROR_EXISTED_PERSON =
 * "Current person is already in your Address Book"; String ERROR_REPEAT = "";
 * String ERROR_CREATE_CALENDAR = "Error to create new Calendar"; String
 * ERROR_REMOVE_CALENDAR = "Current calendar has not been not found";
 * 
 * String ERROR_GET_WEEK = "Error in getting week"; String ERROR_EDIT_CALENDAR =
 * "Error editing calendar";
 */