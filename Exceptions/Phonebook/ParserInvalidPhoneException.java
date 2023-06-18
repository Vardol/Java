package Exceptions.Phonebook;

public class ParserInvalidPhoneException extends ParserException {
    public ParserInvalidPhoneException() {
        super();
    }

    public ParserInvalidPhoneException(String message) {
        super(message);
    }

    public ParserInvalidPhoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserInvalidPhoneException(Throwable cause) {
        super(cause);
    }
}
