package Exceptions.Phonebook;

public class ParserInvalidDataFormatException extends ParserException {
    
    public ParserInvalidDataFormatException() {
        super();
    }

    public ParserInvalidDataFormatException(String message) {
        super(message);
    }

    public ParserInvalidDataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserInvalidDataFormatException(Throwable cause) {
        super(cause);
    }
}
