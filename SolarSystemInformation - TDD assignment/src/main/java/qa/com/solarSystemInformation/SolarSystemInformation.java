package qa.com.solarSystemInformation;

public class SolarSystemInformation {

    private String userID;


    public SolarSystemInformation(String userID) {
        this.userID = userID;
    }

    public String getUserID() throws invalidUserInputException {
            if (!userID.contains("0000") && userID.matches("[A-Z]{2}[0-9]{4}")) {
                this.userID = userID;
            } else {
                throw new invalidUserInputException("Invalid userID format entered");
            }
        return userID;
    }
}
