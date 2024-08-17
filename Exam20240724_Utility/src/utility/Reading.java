package utility;

public class Reading {
    private final String contractcode;
    private final String metercode;
    private final String date;
    private final double value;
    public Reading(String contractcode, String metercode, String date, double value) {
        this.contractcode = contractcode;
        this.metercode = metercode;
        this.date = date;
        this.value = value;
    }
    public String getContractcode() {
        return contractcode;
    }
    public String getMetercode() {
        return metercode;
    }
    public String getDate() {
        return date;
    }
    public double getValue() {
        return value;
    }
}
