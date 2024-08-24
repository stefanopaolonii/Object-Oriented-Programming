package evaluation;
@SuppressWarnings("serial")
public class EvaluationsException extends Exception {
    public EvaluationsException () {}
	public EvaluationsException (String reason) {
		super(reason);
	}
}