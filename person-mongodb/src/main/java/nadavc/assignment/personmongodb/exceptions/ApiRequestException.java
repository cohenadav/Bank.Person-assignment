package nadavc.assignment.personmongodb.exceptions;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String msg)
    {
        super(msg);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
