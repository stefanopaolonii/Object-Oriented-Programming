package emergency;

import emergency.EmergencyApp.*;

public class Patient {
    private final String fiscalcode;
    private final String name;
    private final String surname;
    private final String dateofbirth;
    private final String reason;
    private String datetimeaccepted;
    private PatientStatus status;
    
    public Patient(String fiscalcode, String name, String surname, String dateofbirth, String reason,
            String datetimeaccepted) {
        this.fiscalcode = fiscalcode;
        this.name = name;
        this.surname = surname;
        this.dateofbirth = dateofbirth;
        this.reason = reason;
        this.datetimeaccepted = datetimeaccepted;
        this.status=PatientStatus.ADMITTED;
    }

    public String getFiscalCode() {
        return fiscalcode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateofbirth;
    }

    public String getReason() {
        return reason;
    }

    public String getDateTimeAccepted() {
        return datetimeaccepted;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status){
        this.status=status;
    }
}
