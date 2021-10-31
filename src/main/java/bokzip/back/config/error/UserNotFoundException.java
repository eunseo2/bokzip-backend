package bokzip.back.config.error;

public class UserNotFoundException extends CustomException{
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
