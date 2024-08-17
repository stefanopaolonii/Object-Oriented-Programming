package emergency;

public class Report {
    private final String id;
    private String professionalid;
    private String fiscalcode;
    private String date;
    private String description;

    public Report(String id, String professionalid, String fiscalcode, String date, String description) {
        this.id = id;
        this.professionalid = professionalid;
        this.fiscalcode = fiscalcode;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getProfessionalId() {
        return professionalid;
    }

    public String getFiscalCode() {
        return fiscalcode;
    }

    public String getDate() {
        return date;
    }


    public String getDescription() {
        return description;
    }
}
