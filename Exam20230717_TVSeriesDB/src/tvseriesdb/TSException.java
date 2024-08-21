package tvseriesdb;

public class TSException extends Exception {

	private static final long serialVersionUID = 1L;

	public TSException() {super("TV Series Database exception");}
	
	public TSException(String msg) {super(msg);}

}
