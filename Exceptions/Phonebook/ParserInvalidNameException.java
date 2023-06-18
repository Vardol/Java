package Exceptions.Phonebook;

public class ParserInvalidNameException extends ParserException{
    public ParserInvalidNameException() {
        super();
    }

    public ParserInvalidNameException(String message) {
        super(message);
    }

    public ParserInvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserInvalidNameException(Throwable cause) {
        super(cause);
    }
}
