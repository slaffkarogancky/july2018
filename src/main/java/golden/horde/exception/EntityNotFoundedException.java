package golden.horde.exception;

public class EntityNotFoundedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundedException() {
	}

	public EntityNotFoundedException(String message) {
		super(message);
	}

	public EntityNotFoundedException(String message, Throwable cause) {
		super(message, cause);
	}
}
