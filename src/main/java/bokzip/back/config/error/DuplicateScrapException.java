package bokzip.back.config.error;

public class DuplicateScrapException extends CustomException{
    public DuplicateScrapException(ErrorCode errorCode) {
        super(errorCode);
    }
}
