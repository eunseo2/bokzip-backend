package bokzip.back.config.error;

public class PostNotFoundException extends CustomException{
    public PostNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
