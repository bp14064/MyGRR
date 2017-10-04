package exception;

/**
 * 引数が間違っているときに投げる例外
 * @author AILab08
 *
 */
public class ArgsTypeException extends Exception {
	public ArgsTypeException(String message) {
		super(message);
	}
}
