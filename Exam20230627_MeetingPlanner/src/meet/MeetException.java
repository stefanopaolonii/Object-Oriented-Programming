package meet;

public class MeetException extends Exception {

	private static final long serialVersionUID = 1L;

	public MeetException() {super("Medical center exception");}
	
	public MeetException(String msg) {super(msg);}

}