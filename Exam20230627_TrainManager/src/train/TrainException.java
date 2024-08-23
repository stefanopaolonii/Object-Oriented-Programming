package train;

public class TrainException extends Exception {

	private static final long serialVersionUID = 1L;

	public TrainException() {super("Medical center exception");}
	
	public TrainException(String msg) {super(msg);}

}