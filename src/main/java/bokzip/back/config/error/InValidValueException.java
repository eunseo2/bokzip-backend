package bokzip.back.config.error;

public class InValidValueException extends CustomException {
    public InValidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
