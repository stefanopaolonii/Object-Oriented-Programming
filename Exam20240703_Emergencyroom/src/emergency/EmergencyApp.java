package emergency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class EmergencyApp {

    public enum PatientStatus {
        ADMITTED,
        DISCHARGED,
        HOSPITALIZED
    }
    Map<String,Professional> professionalMap= new HashMap<>();
    Map<String,Department> departmentsMap= new HashMap<>();
    Map<String,Patient> patientsMap= new HashMap<>();
    Map<String,Report> reportsMap= new HashMap<>();
    private int reportCounter=0;
    /**
     * Add a professional working in the emergency room
     * 
     * @param id
     * @param name
     * @param surname
     * @param specialization
     * @param period
     * @param workingHours
     */
    public void addProfessional(String id, String name, String surname, String specialization, String period) {
        professionalMap.put(id, new Professional(id, name, surname, period, specialization));
    }

    /**
     * Retrieves a professional utilizing the ID.
     *
     * @param id The id of the professional.
     * @return A Professional.
     * @throws EmergencyException If no professional is found.
     */    
    public Professional getProfessionalById(String id) throws EmergencyException {
        if(!professionalMap.containsKey(id)) throw new EmergencyException();
        return professionalMap.get(id);
    }

    /**
     * Retrieves the list of professional IDs by their specialization.
     *
     * @param specialization The specialization to search for among the professionals.
     * @return A list of professional IDs who match the given specialization.
     * @throws EmergencyException If no professionals are found with the specified specialization.
     */    
    public List<String> getProfessionals(String specialization) throws EmergencyException {
        List<String> searched = professionalMap.values().stream().filter(p->p.getSpecialization().equals(specialization)).map(Professional::getId).collect(Collectors.toList());
        if(searched.isEmpty()) throw new EmergencyException();
        return searched;
    }

    /**
     * Retrieves the list of professional IDs who are specialized and available during a given period.
     *
     * @param specialization The specialization to search for among the professionals.
     * @param period The period during which the professional should be available, formatted as "YYYY-MM-DD to YYYY-MM-DD".
     * @return A list of professional IDs who match the given specialization and are available during the period.
     * @throws EmergencyException If no professionals are found with the specified specialization and period.
     */    
    public List<String> getProfessionalsInService(String specialization, String period) throws EmergencyException {
        List<String> searched= professionalMap.values().stream().filter(p->p.getSpecialization().equals(specialization)).filter(p-> {String[] targetparts=period.split(" to "); String[] professionalparts=p.getPeriod().split(" to "); return professionalparts[0].compareTo(targetparts[0])<=0 && professionalparts[1].compareTo(targetparts[1])>=0;}).map(Professional::getId).collect(Collectors.toList());
        if(searched.isEmpty()) throw new EmergencyException();
        return searched;
    }

    /**
     * Adds a new department to the emergency system if it does not already exist.
     *
     * @param name The name of the department.
     * @param maxPatients The maximum number of patients that the department can handle.
     * @throws EmergencyException If the department already exists.
     */
    public void addDepartment(String name, int maxPatients) {
        departmentsMap.put(name, new Department(name, maxPatients));
    }

    /**
     * Retrieves a list of all department names in the emergency system.
     *
     * @return A list containing the names of all registered departments.
     * @throws EmergencyException If no departments are found.
     */
    public List<String> getDepartments() throws EmergencyException {
        List<String> searched= departmentsMap.keySet().stream().collect(Collectors.toList());
        if(searched.isEmpty()) throw new EmergencyException();
        return searched;
    }

    /**
     * Reads professional data from a CSV file and stores it in the application.
     * Each line of the CSV should contain a professional's ID, name, surname, specialization, period of availability, and working hours.
     * The expected format of each line is: matricola, nome, cognome, specializzazione, period, orari_lavoro
     * 
     * @param reader The reader used to read the CSV file. Must not be null.
     * @return The number of professionals successfully read and stored from the file.
     * @throws IOException If there is an error reading from the file or if the reader is null.
     */
    public int readFromFileProfessionals(Reader reader) throws IOException {
        if(reader==null) throw new IOException();
        BufferedReader bufferedReader= new BufferedReader(reader);
        String line;
        int count=0;
        String firstline=bufferedReader.readLine();
        try{
            while((line=bufferedReader.readLine())!=null){
                String[] lineParts=line.split(",");
                addProfessional(lineParts[0], lineParts[1], lineParts[2], lineParts[3], lineParts[4]);
                count++;
            }
        }catch(IOException e){
            throw new IOException();
        }
        return count;
    }

    /**
     * Reads department data from a CSV file and stores it in the application.
     * Each line of the CSV should contain a department's name and the maximum number of patients it can accommodate.
     * The expected format of each line is: nome_reparto, num_max
     * 
     * @param reader The reader used to read the CSV file. Must not be null.
     * @return The number of departments successfully read and stored from the file.
     * @throws IOException If there is an error reading from the file or if the reader is null.
     */    
    public int readFromFileDepartments(Reader reader) throws IOException {
        if(reader==null) throw new IOException();
        BufferedReader bufferedReader= new BufferedReader(reader);
        String line;
        int count=0;
        String firstline=bufferedReader.readLine();
        try{
            while((line=bufferedReader.readLine())!=null){
                String[] lineParts=line.split(",");
                addDepartment(lineParts[0],Integer.parseInt(lineParts[1]));
                count++;
            }
        }catch(IOException e){
            throw new IOException();
        }
        return count;
    }

    /**
     * Registers a new patient in the emergency system if they do not exist.
     * 
     * @param fiscalCode The fiscal code of the patient, used as a unique identifier.
     * @param name The first name of the patient.
     * @param surname The surname of the patient.
     * @param dateOfBirth The birth date of the patient.
     * @param reason The reason for the patient's visit.
     * @param dateTimeAccepted The date and time the patient was accepted into the emergency system.
     */
    public Patient addPatient(String fiscalCode, String name, String surname, String dateOfBirth, String reason, String dateTimeAccepted) {
        if(patientsMap.containsKey(fiscalCode)) return patientsMap.get(fiscalCode);
        Patient newPatient= new Patient(fiscalCode, name, surname, dateOfBirth, reason, dateTimeAccepted);
        patientsMap.put(fiscalCode, newPatient);
        return newPatient;
    }

    /**
     * Retrieves a patient or patients based on a fiscal code or surname.
     *
     * @param identifier Either the fiscal code or the surname of the patient(s).
     * @return A single patient if a fiscal code is provided, or a list of patients if a surname is provided.
     *         Returns an empty collection if no match is found.
     */    
    public List<Patient> getPatient(String identifier) throws EmergencyException {
        return patientsMap.values().stream().filter(p-> p.getFiscalCode().equals(identifier) || p.getSurname().equals(identifier)).collect(Collectors.toList());
    }

    /**
     * Retrieves the fiscal codes of patients accepted on a specific date, 
     * sorted by acceptance time in descending order.
     *
     * @param date The date of acceptance to filter the patients by, expected in the format "yyyy-MM-dd".
     * @return A list of patient fiscal codes who were accepted on the given date, sorted from the most recent.
     *         Returns an empty list if no patients were accepted on that date.
     */
    public List<String> getPatientsByDate(String date) {
        return patientsMap.values().stream().filter(p->p.getDateTimeAccepted().compareTo(date)==0).sorted(Comparator.comparing(Patient::getSurname).thenComparing(Patient::getName)).map(Patient::getFiscalCode).collect(Collectors.toList());
    }

    /**
     * Assigns a patient to a professional based on the required specialization and checks availability during the request period.
     *
     * @param fiscalCode The fiscal code of the patient.
     * @param specialization The required specialization of the professional.
     * @return The ID of the assigned professional.
     * @throws EmergencyException If the patient does not exist, if no professionals with the required specialization are found, or if none are available during the period of the request.
     */
    public String assignPatientToProfessional(String fiscalCode, String specialization) throws EmergencyException {
        Patient searchedPatient=patientsMap.get(fiscalCode);
        if(searchedPatient==null) throw new EmergencyException();
        Optional<Professional> searchedProfessional=professionalMap.values().stream().filter(p->p.getSpecialization().equals(specialization)).filter(p->{String[] professionalPeriodParts=p.getPeriod().split(" to "); return professionalPeriodParts[0].compareTo(searchedPatient.getDateTimeAccepted())<=0 && professionalPeriodParts[1].compareTo(searchedPatient.getDateTimeAccepted())>=0;}).sorted(Comparator.comparing(Professional::getId)).findFirst();
        if(searchedProfessional.isEmpty()) throw new EmergencyException();
        searchedProfessional.get().addPatient(searchedPatient);
        return searchedProfessional.get().getId();
    }

    public Report saveReport(String professionalId, String fiscalCode, String date, String description) throws EmergencyException {
        if(!professionalMap.containsKey(professionalId)) throw new EmergencyException();
        String code=String.format("%d",++reportCounter);
        Report newReport= new Report(code, professionalId, fiscalCode, date, description);
        reportsMap.put(code, newReport);
        return newReport;
    }

    /**
     * Either discharges a patient or hospitalizes them depending on the availability of space in the requested department.
     * 
     * @param fiscalCode The fiscal code of the patient to be discharged or hospitalized.
     * @param departmentName The name of the department to which the patient might be admitted.
     * @throws EmergencyException If the patient does not exist or if the department does not exist.
     */
    public void dischargeOrHospitalize(String fiscalCode, String departmentName) throws EmergencyException {
        Patient searchedPatient= patientsMap.get(fiscalCode);
        Department searchedDepartment= departmentsMap.get(departmentName);
        if(searchedPatient==null) throw new EmergencyException();
        if(searchedDepartment==null) throw new EmergencyException();
        if(searchedDepartment.isAvailable()){
            searchedPatient.setStatus(PatientStatus.HOSPITALIZED);
            searchedDepartment.addPatient(searchedPatient);
        }
        searchedPatient.setStatus(PatientStatus.DISCHARGED);
    }

    /**
     * Checks if a patient is currently hospitalized in any department.
     *
     * @param fiscalCode The fiscal code of the patient to verify.
     * @return 0 if the patient is currently hospitalized, -1 if not hospitalized or discharged.
     * @throws EmergencyException If no patient is found with the given fiscal code.
     */
    public int verifyPatient(String fiscalCode) throws EmergencyException{
        Patient searchedPatient=patientsMap.get(fiscalCode);
        if(searchedPatient==null) throw new EmergencyException();
        if(searchedPatient.getStatus()==PatientStatus.DISCHARGED) return 0;
        if(searchedPatient.getStatus()==PatientStatus.HOSPITALIZED) return 1;
        return -1;
    }

    /**
     * Returns the number of patients currently being managed in the emergency room.
     *
     * @return The total number of patients in the system.
     */    
    public int getNumberOfPatients() {
        return (int) patientsMap.values().stream().filter(p->p.getStatus()==PatientStatus.ADMITTED).count();
    }

    /**
     * Returns the number of patients admitted on a specified date.
     *
     * @param dateString The date of interest provided as a String (format "yyyy-MM-dd").
     * @return The count of patients admitted on that date.
     */
    public int getNumberOfPatientsByDate(String date) {
        return (int) patientsMap.values().stream().filter(p->p.getDateTimeAccepted().compareTo(date)==0).count();
    }

    public int getNumberOfPatientsHospitalizedByDepartment(String departmentName) throws EmergencyException {
        if(!departmentsMap.containsKey(departmentName)) throw new EmergencyException();
        return (int) departmentsMap.get(departmentName).getPatients().stream().count();
    }

    /**
     * Returns the number of patients who have been discharged from the emergency system.
     *
     * @return The count of discharged patients.
     */
    public int getNumberOfPatientsDischarged() {
        return (int) patientsMap.values().stream().filter(p->p.getStatus()==PatientStatus.DISCHARGED).count();
    }

    /**
     * Returns the number of discharged patients who were treated by professionals of a specific specialization.
     *
     * @param specialization The specialization of the professionals to filter by.
     * @return The count of discharged patients treated by professionals of the given specialization.
     */
    public int getNumberOfPatientsAssignedToProfessionalDischarged(String specialization) {
        return (int) professionalMap.values().stream().filter(p->p.getSpecialization().equals(specialization)).flatMap(p->p.getPatients().stream()).filter(p->p.getStatus()==PatientStatus.DISCHARGED).distinct().count();
    }
}
