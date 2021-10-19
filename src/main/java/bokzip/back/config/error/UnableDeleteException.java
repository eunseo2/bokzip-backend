package bokzip.back.config.error;

public class UnableDeleteException extends CustomException{
    public UnableDeleteException(ErrorCode errorCode) {
        super(errorCode);
    }
}
