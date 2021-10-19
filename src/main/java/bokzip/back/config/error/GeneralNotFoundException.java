package bokzip.back.config.error;

public class GeneralNotFoundException extends CustomException{
    public GeneralNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
