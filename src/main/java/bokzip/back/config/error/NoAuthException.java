package bokzip.back.config.error;

public class NoAuthException extends CustomException{
    public NoAuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
