package qa.com.solarSystemInformation;

public class invalidUserInputException extends Exception {

    public invalidUserInputException(String errorMessage) {
        super(errorMessage);
    }
}
